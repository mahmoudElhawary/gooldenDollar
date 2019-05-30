import { Component, OnInit, OnDestroy } from '@angular/core';
import { LoginService } from 'src/app/service/login.service';
import { CartService } from 'src/app/service/cart.service';
import { Location } from '@angular/common';
import { Unsubscribable, Subscription } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  Total = 0 ;
  Subtotal = 1 ;
  loginUser: any = {};
  AllData: object[] ;
  carts: object[];
  cartItems: object[];
  products = new Array();
  quantity = 1 ;
  cart: any = {} ;
  constructor(
    private loginService: LoginService,
    private cartService: CartService ,
    private location: Location,
    private toastr: ToastrService,
    private router: Router
    ) {
    this.loginService.isLoggedIn();
    this.loginUser = JSON.parse(localStorage.getItem('currentUser'));
    }

  ngOnInit() {
    if (this.loginUser.user != null) {
      this.getCartByUser() ;
    }
  }

  getCartByUser() {
    let price = 0;
    if (this.loginUser.user != null) {
      this.cartService.getAllUserCarts(this.loginUser.user).subscribe(response => {
        this.AllData = response as object[];
        this.AllData.map(cartss => {
          this.carts = cartss['cartItems'] ;
          this.carts.forEach(items => {
            this.cartItems = items['product'] ;
            price = this.cartItems['productPrice'] ;
            this.Subtotal = price * this.quantity ;
            console.log(this.quantity) ;
            this.Total += this.Subtotal;
            console.log(+this.Subtotal) ;
            this.products.push(this.cartItems) ;
            this.toastr.success('Your cart is loaded!', 'Success!');
          }) ;
          console.log(+this.Total) ;
        });
      }, err => {
        this.toastr.error('there is an error happend', 'Oops!');
      }) ;

    }
  }
  back() {
    this.location.back() ;
    this.toastr.warning('You are leaving this page', 'Alert!');
  }
  delete(item) {
    if (item != null) {
      if (confirm('Are you sure want to delete this cart ?')) {
        this.cartService.deleteCartByProduct(item).subscribe(response => {
          this.toastr.warning('You are deleting your cart', 'Alert!');
          location.reload() ;
        }, err => {
          this.toastr.error('you have an error!', 'Oops!');
        });
      }
    }
  }
}
