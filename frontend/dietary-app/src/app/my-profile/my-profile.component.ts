import {Component, OnInit} from '@angular/core';
import {User} from "../models/user";
import {AccountService} from "../services/AccountService";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {first} from "rxjs/operators";

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {

  user: User;
  userEmail = JSON.parse(<string>localStorage.getItem('user')).email;
  userWeight = JSON.parse(<string>localStorage.getItem('user')).weight;
  userHeight = JSON.parse(<string>localStorage.getItem('user')).height;

  form: FormGroup = this.formBuilder.group({
    email: ['', Validators.minLength(6)],
    weight: [''],
    height: ['']
  });


  constructor(private formBuilder: FormBuilder, private accountService: AccountService) {
    this.user = JSON.parse(<string>localStorage.getItem('user'));
  }

  ngOnInit() {
    this.form = this.formBuilder.group({
      email: ['', Validators.minLength(6)],
      weight: [''],
      height: ['']
    });
  }

  calcBmi() {
    let bmi = Number(this.user.weight) / (Math.pow(Number(this.user.height)/100,2));
    return Math.round(bmi * 100) / 100;
  }

  get f() { return this.form.controls; }

  onSubmit() {
    let user = this.accountService.userValue;
    user.email = this.f.email.value;
    user.weight = this.f.weight.value;
    user.height = this.f.height.value

    let id = JSON.parse(<string>localStorage.getItem('user')).id;
    console.log(id);

    this.accountService.update(id, user)
      .pipe(first())
      .subscribe({
        next: () => {
          alert("Aktualizacja poprawna!");
        },
        error: error => {
          alert("Złe dane!")
        }
      });
  }

  getAge() {
    let timeDiff = Math.abs(Date.now() - new Date(this.user.birthdate).getTime());
    return Math.floor(timeDiff / (1000 * 3600 * 24) / 365.25);
  }

  kalories() {
    if(this.user.sex === "M") {
      return Math.round(66 + 13.7 * this.user.weight + 5 * this.user.height - 6.8 * this.getAge());
    }
    else if(this.user.sex === "F") {
      return Math.round(655 + 9.6 * this.user.weight + 1.8 * this.user.height - 6.8 * this.getAge());
    }
    else { return "Podaj informacje o wieku!" };
  }

}
