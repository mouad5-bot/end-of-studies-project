import {Component, OnInit} from '@angular/core';
import {UserClass} from "../../models/entities/user";
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";
import {authUtils} from "../../authUtils";
declare var $: any;

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{
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
