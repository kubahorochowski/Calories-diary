import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProductService} from "../services/ProductService";
import {Product} from "../models/product";
import {Observable} from "rxjs";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  allProducts: Observable<Product[]>;
  updatedProduct: Product = {
    product_id: 0,
    product_name: "",
    carbohydrates: 0,
    proteins: 0,
    fat: 0,
    kcal: 0
  }

  form: FormGroup = this.formBuilder.group({
    productName: ['', [Validators.required, Validators.minLength(2)]],
    carbo: ['', [Validators.required, Validators.maxLength(4)]],
    proteins: ['', [Validators.required, Validators.maxLength(4)]],
    fat: ['', [Validators.required, Validators.maxLength(4)]],
    kcal: ['', [Validators.required, Validators.maxLength(5)]]
  });

  changeForm: FormGroup = this.formBuilder.group({
    productName: ['', [Validators.required, Validators.minLength(2)]],
    carbo: ['', [Validators.required, Validators.maxLength(4)]],
    proteins: ['', [Validators.required, Validators.maxLength(4)]],
    fat: ['', [Validators.required, Validators.maxLength(4)]],
    kcal: ['', [Validators.required, Validators.maxLength(5)]]
  });

  constructor(
    private formBuilder: FormBuilder,
    private productService: ProductService
  ) { }

  ngOnInit(): void {
  }

  get f() { return this.form.controls; }

  onSubmit() {
    let newProd = {
      product_name: this.f.productName.value,
      carbohydrates: this.f.carbo.value,
      proteins: this.f.proteins.value,
      fat: this.f.fat.value,
      kcal: this.f.kcal.value
    }
    console.log(JSON.stringify(newProd));
    this.productService.addProduct(JSON.stringify(newProd));
    // window.location.reload();
  }

  displayProducts() {
    this.allProducts = this.productService.getAllProducts()
  }

  changeProd(product: Product){
    this.updatedProduct = product;
  }

  updateProduct(){
    this.productService.updateProduct(JSON.stringify(this.updatedProduct), this.updatedProduct.product_id);
  }
}
