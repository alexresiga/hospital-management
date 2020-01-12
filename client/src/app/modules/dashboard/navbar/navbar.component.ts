import { Component } from '@angular/core';

@Component({
    selector: 'app-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.css', '../common.css'],
})
export class NavbarComponent {
    private isNavbarOpened: boolean;

    toggleNavbar() {
        this.isNavbarOpened = !this.isNavbarOpened;
    }
}
