import {Component, OnInit} from '@angular/core';
import {AccountService} from "../services/AccountService";
import {ActivatedRoute, Router} from "@angular/router";
import {User} from "../models/user";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  collapsed = true;
  isLogged = false;
  user: User;

  constructor(private accountService: AccountService, private router: Router,private route: ActivatedRoute) {
    this.user = JSON.parse(<string>localStorage.getItem('user'));
  }

  ngOnInit(): void {
  }

  ngDoCheck() {
    // @ts-ignore
    if (localStorage.getItem('user') == null) {
      this.isLogged = false
    } else {
      this.isLogged = true;
    }
  }

  onLogOut() {
    this.isLogged = false;
    window.confirm("Poprawnie wylogowano")
    this.accountService.logout()
    this.router.navigate(['../'], { relativeTo: this.route });

  }
}
