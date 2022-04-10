import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AccountService} from "../services/AccountService";
import {first} from "rxjs/operators";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  form: FormGroup = this.formBuilder.group({
    username: ['', Validators.required],
    password: ['', Validators.required, Validators.minLength(6)],
    confirmPassword: ['', Validators.required, Validators.minLength(6)],
    sex: ['', Validators.required],
    email: ['', Validators.required, Validators.minLength(6)],
    birthday: ['', Validators.required],
    weight: ['', Validators.required],
    height: ['', Validators.required]
  });

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private accountService: AccountService,
  ) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(1)]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', [Validators.required, Validators.minLength(6)]],
      sex: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      birthday: ['', Validators.required],
      weight: ['', Validators.required],
      height: ['', Validators.required]
    });
  }

  get f() { return this.form.controls; }

  onSubmit() {

    if (this.form.invalid) {
      console.log(this.f.username.value);
      console.log(this.f.email.value);
      console.log(this.f.password.value);
      console.log(this.f.birthday.value);
      console.log(this.f.weight.value);
      console.log(this.f.height.value);
      console.log(this.f.sex.value);
    }

    if (this.f.password.value != this.f.confirmPassword.value) {
      alert("Hasła nie są jednakowe!")
      return;
    }

    let user = {
      username: this.f.username.value,
      email: this.f.email.value,
      password: this.f.password.value,
      birthdate: this.f.birthday.value,
      weight: this.f.weight.value,
      height: this.f.height.value,
      sex: this.f.sex.value,
      id: Date.now(),
      enabled: 'Y'
    }

    this.accountService.register(user)
      .pipe(first())
      .subscribe({
        next: () => {
          alert("Rejestracja poprawna");
          this.router.navigate(['../login'], { relativeTo: this.route });
        },
        error: error => {
          alert("Złe dane!")
        }
      });
  }

}
