import { Component, OnInit } from '@angular/core';
import {UserClass} from "../../models/entities/user";
import {Router} from "@angular/router";
import {authUtils} from "../../authUtils";
import {AuthService} from "../../services/auth.service";
declare var $: any; // jQuery symbol
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  ariaLabel: string = 'Toggle navigation';
  isNavbarCollapsed = true;
  user : UserClass | null | undefined;
  constructor(private router: Router , private authService: AuthService) {
  }
  toggleNavbar() {
    this.isNavbarCollapsed = !this.isNavbarCollapsed;
  }

  ngOnInit() {

    this.user = authUtils.getAuthenticatedUser();

    $('.navbar-toggler').click(function() {
      console.log('Toggler clicked!');
    });

  }
  logout() {
    authUtils.logout();
    this.router.navigate(['/login']);
  };
}
