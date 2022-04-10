import {Component, OnInit, TemplateRef} from '@angular/core';
import {User} from "../models/user";
import {DatePipe} from "@angular/common";
import {FormBuilder, FormGroup, Validators, ReactiveFormsModule } from "@angular/forms";
import {DietService} from "../services/DietService";
import {ProductService} from "../services/ProductService";
import {Diet} from "../models/diet";
import {Product} from "../models/product";
import {Observable} from "rxjs";
import {BsModalRef, BsModalService} from "ngx-bootstrap/modal";


@Component({
  selector: 'app-my-diet',
  templateUrl: './my-diet.component.html',
  styleUrls: ['./my-diet.component.css']
})
export class MyDietComponent implements OnInit {

  user: User;
  todayDate = Date.now();
  date = this.datePipe.transform(this.todayDate, "YYYY-MM-dd");
  dietElements: Observable<Diet[]>;

  form: FormGroup = this.formBuilder.group({
    date: ['', Validators.required],
  });


  products: Observable<Product[]>;
  chosenProd: Product = {
    "product_id": 0,
    "product_name": "",
    "carbohydrates": 0,
    "proteins": 0,
    "fat": 0,
    "kcal": 0
  };
  chosenWeight = {weight: 0};
  dietSum = {
    "carbohydrates": 0,
    "proteins": 0,
    "fat": 0,
    "weight": 0,
    "kcal": 0
  };

  productForm: FormGroup = this.formBuilder.group({
    productName: ['', Validators.required],
  });

  valueForm: FormGroup = this.formBuilder.group({
    weight: ['', Validators.required],
  });

constructor(private formBuilder: FormBuilder, private datePipe: DatePipe, private dietService: DietService, private productService: ProductService, private modalService: BsModalService) {
    this.productForm.valueChanges
      .subscribe(data => {
        this.products = this.onProductSearch(data.productName);
      })

  this.valueForm.valueChanges
    .subscribe(data => {
      this.chosenWeight = data;
    })
  }

  ngOnInit(): void {
    this.user = JSON.parse(<string>localStorage.getItem('user'));
    this.onSearchDiet();
  }

  onSearchDiet() {
    this.dietElements = this.searchDiet();
    this.dietSum = {
      "carbohydrates": 0,
      "proteins": 0,
      "fat": 0,
      "weight": 0,
      "kcal": 0
    };
    this.dietElements.forEach( i => i.forEach( i => {
      this.dietSum.carbohydrates += Math.round(i.product.carbohydrates*i.weight/100);
      this.dietSum.proteins += Math.round(i.product.proteins*i.weight/100);
      this.dietSum.fat += Math.round(i.product.fat*i.weight/100);
      this.dietSum.weight += i.weight;
      this.dietSum.kcal += Math.round(i.product.kcal*i.weight/100);
    }));
  }

  searchDiet() {
    let id = this.user.id;
    let date = this.date + "";
    return this.dietService.getForUserForDate(id, date);
  }

  onProductSearch(name: string) {
    return this.productService.getProductByName(name);
  }

  setVar(product: Product) {
    this.chosenProd = product;
  }

  addDiet() {
    let dietEl = {
      user_id: this.user.id,
      product: this.chosenProd,
      date: this.datePipe.transform(Date.now(), "YYYY-MM-dd"),
      weight: this.chosenWeight.weight
    }
    this.dietService.addDiet(JSON.stringify(dietEl));
    window.location.reload();
  }

  getAge() {
    let timeDiff = Math.abs(Date.now() - new Date(this.user.birthdate).getTime());
    return Math.floor(timeDiff / (1000 * 3600 * 24) / 365.25);
  }

  kalories() {
    if(this.user.sex === "M") {
      return Math.round(66 + 13.7 * this.user.weight + 5 * this.user.height - 6.8 * this.getAge())-this.dietSum.kcal;
    }
    else if(this.user.sex === "F") {
      return Math.round(655 + 9.6 * this.user.weight + 1.8 * this.user.height - 6.8 * this.getAge())-this.dietSum.kcal;
    }
    else { return "Podaj informacje o wieku!" };
  }

}
