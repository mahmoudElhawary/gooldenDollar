import { Component, OnInit } from '@angular/core';
import { NgbCarouselConfig, NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';
import { LoginService } from 'src/app/service/login.service';
import { UserService } from 'src/app/service/user.service';
import { CategoryService } from 'src/app/service/category.service';
import { ProductsService } from 'src/app/service/products.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {
  loginUser: any = {};
  user: any = {};
  users$;
  page = 1 ;
  constructor(
    configSlider: NgbCarouselConfig,
    private configRate: NgbRatingConfig,
    private loginService: LoginService,
    private userService: UserService,
    private categoryService: CategoryService,
    private productService: ProductsService,
    private router: Router,
    private toastr: ToastrService
  ) {
    this.loginService.isLoggedIn();
    this.loginUser = JSON.parse(localStorage.getItem('currentUser'));
    // customize default values of ratings used by this component tree
    configRate.readonly = true;
  }

  ngOnInit() {
    this.getUsers();
  }

  show(user) {
    if (user != null) {
      this.router.navigate(['users/' + user]) ;
    } else {
      this.toastr.warning('this user is not founded !') ; ;
    }
  }
  getUsers() {
    this.users$ = this.userService.getUsers(this.loginUser.token);
  }
}
