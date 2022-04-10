import { Component } from '@angular/core';
import {User} from "./models/user";
import {AccountService} from "./services/AccountService";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'Dietary app';
  user: User | undefined;

  constructor(private accountService: AccountService, private router: Router,private route: ActivatedRoute) {
    this.accountService.user.subscribe(x => this.user = x);
  }

}
