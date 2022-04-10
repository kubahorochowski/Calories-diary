import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Product} from "../models/product";
import {Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class ProductService {
  headers = new HttpHeaders();


  constructor(
    private http: HttpClient
  ) {
    this.headers = this.headers.append('Content-Type', 'application/json');
    this.headers = this.headers.append('Accept', 'application/json');
    let token = JSON.parse(<string>localStorage.getItem('user')).token;
    this.headers = this.headers.append('Authorization', token);
  }

  getAllProducts() {
    let token = JSON.parse(<string>localStorage.getItem('user')).token;
    this.headers = this.headers.set('Authorization', token);
    return this.http.get<Product[]>(`${environment.apiUrl}/products/all`, {headers: this.headers});
  }

  addProduct(newProd: string) {
    let token = JSON.parse(<string>localStorage.getItem('user')).token;
    this.headers = this.headers.set('Authorization', token);
    return this.http.post(`${environment.apiUrl}/products/add`, newProd, {headers: this.headers})
      .subscribe( {
        next: data => {
          console.log(data)
          alert("Produkt dodany")
        },
        error: error => {
          alert("Nieprawidłowe dane, lub nie masz uprawnień administratora!");
        }
      });
  }

  getProductByName(name: string): Observable<Product[]> {
    let token = JSON.parse(<string>localStorage.getItem('user')).token;
    this.headers = this.headers.set('Authorization', token);
    return this.http.get<Product[]>(`${environment.apiUrl}/products/name/${name}`, {headers: this.headers});
  }

  updateProduct(product: string, id: any) {
    let token = JSON.parse(<string>localStorage.getItem('user')).token;
    this.headers = this.headers.set('Authorization', token);
    console.log(this.headers.get('Authorization'));
    return this.http.put(`${environment.apiUrl}/products/update/${id}`, product, {headers: this.headers})
      .subscribe(() => console.log("Updated"));
  }

}
