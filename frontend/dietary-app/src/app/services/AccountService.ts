import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { environment } from "../../environments/environment";
import {User} from "../models/user";


@Injectable({ providedIn: 'root' })
export class AccountService {
  private userSubject: BehaviorSubject<User>;
  public user: Observable<User>;

  constructor(
    private router: Router,
    private http: HttpClient
  ) {
    this.userSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('user')!));
    this.user = this.userSubject.asObservable();
  }

  public get userValue(): User {
    return this.userSubject.value;
  }

  login(username: any, password: any) {
    let headers = new HttpHeaders();
    headers = headers.append('Content-Type', 'application/json');
    headers = headers.append('Accept', 'application/json');
    return this.http.post<User>(`${environment.apiUrl}/login`, JSON.stringify({ username, password }), {headers: headers})
      .pipe(map(user => {
        localStorage.setItem('user', JSON.stringify(user));
        this.userSubject.next(user);
        console.log(user);
        return user;
      }));
  }

  logout() {
    this.http.post(`${environment.apiUrl}/perform_logout`,"");
    localStorage.removeItem('user');
    this.userSubject.next(null as any);
    this.router.navigate(['/home']);
  }

  register(user: { username: any; email: any; password: any; birthdate: any; weight: any; height: any; sex: any; id: any }) {
    console.log(user)
    return this.http.post(`${environment.apiUrl}/register`, user);
  }

  update(id: string, user: User) {
    let tokenJWT = user.token
    let passRes = user.password;
    // @ts-ignore
    delete user.token;
    // @ts-ignore
    delete user.password;
    let headers = new HttpHeaders();
    headers = headers.set('Content-Type', 'application/json');
    headers = headers.set('Accept', 'application/json');
    headers = headers.set('Authorization', 'Bearer ' + tokenJWT);
    console.log(headers);
    return this.http.put(`${environment.apiUrl}/users/update/${id}`, user, {headers: headers})
      .pipe(map(x => {
        if (id == this.userValue.id) {
          user.token = tokenJWT;
          user.password = passRes;
          localStorage.setItem('user', JSON.stringify(user));
          this.userSubject.next(user);
        }
        return x;
      }));
  }
}
