import { Component, OnInit } from '@angular/core';
declare var $: any; // jQuery symbol
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  ariaLabel: string = 'Toggle navigation';
  isNavbarCollapsed = true;
  toggleNavbar() {
    this.isNavbarCollapsed = !this.isNavbarCollapsed;
  }

  ngOnInit() {
    $('.navbar-toggler').click(function() {
      console.log('Toggler clicked!');
    });
  }
}
