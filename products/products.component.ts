import { Component, OnInit } from '@angular/core';
import { ProductsService } from 'src/app/service/products.service';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/service/login.service';
import { UserService } from 'src/app/service/user.service';
import { CategoryService } from 'src/app/service/category.service';
import { CartService } from 'src/app/service/cart.service';
import { FormControl } from '@angular/forms';
import { Location } from '@angular/common';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  page = 1;
  products$;
  mainCategories$;
  loginUser: any = {};
  products: any = [];
  selected = new FormControl();
  labelName ;
  constructor(
    private productService: ProductsService,
    private router: Router,
    private loginService: LoginService,
    private userService: UserService,
    private categoryService: CategoryService,
    private cartService: CartService,
    private location: Location ,
    private toastr: ToastrService
  ) {
    this.loginService.isLoggedIn();
    this.loginUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    this.getMainCategories();
    this.getProducts();
  }
  selectedTabValue(event) {
    this.labelName = event.tab.textLabel;
    this.products$ = this.productService.allProductsByMainCategory(this.labelName);
    this.toastr.info('Just loaded some products for you.');
  }

  getProducts() {
    if (this.labelName != null) {
      this.products$ = this.productService.allProductsByMainCategory(this.labelName);
      this.toastr.info('Just loaded some products for you.');
    } else {
      this.toastr.warning('please select an category!', 'Oops!');
    }
  }

  getMainCategories() {
    this.mainCategories$ = this.categoryService.getMainCategories();
  }
  view(product) {
    this.router.navigate(['products/' + product.productId]);
    this.toastr.info('Just loaded some products for you.');
  }
  addToCart(item) {
    if (item != null) {
      const formData = new FormData();
      formData.append('product', JSON.stringify(item));
      formData.append('user', JSON.stringify(this.loginUser.user));
      this.cartService.saveCart(formData).subscribe(response => {
        this.toastr.success('You are successfully added this product to your cart', 'Success!');
        this.router.navigate(['/cart']) ;
      } , err => {
        this.toastr.error('there is an error happend!', 'Oops!');
      });
    } else {
      this.toastr.error('this cart is empty!', 'Oops!');
    }
  }
  back() {
    this.location.back() ;
    this.toastr.warning('You are leaving this page.', 'Alert!');
  }
}
