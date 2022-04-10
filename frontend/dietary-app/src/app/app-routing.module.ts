import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistrationComponent } from "./registration/registration.component";
import { LoginComponent } from "./login/login.component";
import { MyDietComponent } from "./my-diet/my-diet.component";
import { MyProfileComponent } from "./my-profile/my-profile.component";
import { HomeComponent } from "./home/home.component";
import { ProductsComponent } from "./products/products.component";

const routes: Routes = [
  {path : 'login', component: LoginComponent},
  {path : 'registration', component: RegistrationComponent},
  {path : 'diets', component: MyDietComponent},
  {path : 'profile', component: MyProfileComponent},
  {path : 'home', component: HomeComponent},
  {path : 'products', component: ProductsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
