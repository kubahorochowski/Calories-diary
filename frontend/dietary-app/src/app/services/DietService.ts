import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Diet} from "../models/diet";
import {Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class DietService {

  token = JSON.parse(<string>localStorage.getItem('user')).token;
  headers = new HttpHeaders();

  constructor(
    private http: HttpClient
  ) {
    this.headers = this.headers.append('Content-Type', 'application/json');
    this.headers = this.headers.append('Accept', 'application/json');
    this.headers = this.headers.append('Authorization', 'Bearer ' + this.token);
  }

  getForUserForDate(id: string, date: string): Observable<Diet[]> {
    return this.http.get<Diet[]>(`${environment.apiUrl}/diets/user/${id}/date/${date}`, {headers: this.headers});
  }

  addDiet(diet: string) {
    return this.http.post(`${environment.apiUrl}/diets/add`, diet, {headers: this.headers})
      .subscribe( {
        next: data => {
          console.log(data)
        },
        error: error => {
          alert("coś poszło nie tak");
        }
      })
  }

  deleteDiet(id: number) {
    return this.http.delete(`${environment.apiUrl}/diets/delete/${id}`, {headers: this.headers})
      .subscribe(() => console.log("Deleted"));
  }
}
