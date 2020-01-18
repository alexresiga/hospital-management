import {Component, OnInit} from '@angular/core';
import {User} from "../../../shared/model/User";
import {UserService} from "../../auth/shared/user.service";

@Component({
    selector: 'app-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.css', '../common.css'],
})
export class NavbarComponent implements OnInit{
    private isNavbarOpened: boolean;
    private user: User;

    toggleNavbar() {
        this.isNavbarOpened = !this.isNavbarOpened;
    }
    constructor(private userService: UserService) {
    }

    getCurrentUser(): void {
        this.userService.getCurrentUser().subscribe(user => this.user = user);
    }

    ngOnInit(): void {
        this.getCurrentUser();
    }
}
