import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/service/login.service';
import { CartService } from 'src/app/service/cart.service';
import { Location } from '@angular/common';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { OrderService } from 'src/app/service/order.service';
import { UserService } from 'src/app/service/user.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  badPassword = false ;
  returnUrl: string;
  loginForm: FormGroup;
  ShippingForm: FormGroup ;
  Total: number ;
  Subtotal: number ;
  loginUser: any = {};
  AllData: object[] ;
  carts: object[];
  cartItems: object[];
  products = new Array();
  cart: any = {} ;
  addresses$ ;
  constructor(
    private userService: UserService,
    private loginService: LoginService,
    private cartService: CartService ,
    private location: Location ,
    private fb: FormBuilder ,
    private router: Router,
    private orderService: OrderService ,
    private toastr: ToastrService) {
    this.loginService.isLoggedIn();
    this.loginUser = JSON.parse(localStorage.getItem('currentUser'));

    this.ShippingForm = this.fb.group({
      fullName: new FormControl('', Validators.required),
      phoneNumber: new FormControl('', Validators.required),
      address: new FormControl('', Validators.required),
      city: new FormControl('', Validators.required),
      state: new FormControl('', Validators.required),
      country: new FormControl('', Validators.required),
      zipCode: new FormControl('', Validators.required),
      specialNotes: new FormControl(''),
    });

    this.loginForm = this.fb.group({
      email: new FormControl('', [
        Validators.required,
        Validators.minLength(5),
        Validators.maxLength(31),
        Validators.email
      ]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(5),
        Validators.maxLength(21)
      ])
    });
    }

  ngOnInit() {
    if (this.loginUser != null) {
      this.getCartByUser() ;
      this.getAllShippingAddressByUser() ;
      this.getShippingAddressByUser() ;
      this.getAllUserOrderByUser() ;
    }
  }

  getCartByUser() {
    let price = 0;
    if (this.loginUser != null) {
      this.cartService.getAllUserCarts(this.loginUser.user).subscribe(response => {
        this.AllData = response as object[];
        this.AllData.map(cartss => {
          this.carts = cartss['cartItems'] ;
          this.carts.forEach(items => {
            this.cartItems = items['product'] ;
            price = this.cartItems['productPrice'] ;
            this.products.push(this.cartItems) ;
          }) ;
        });
        this.toastr.success('You are successfully loaded your carts', 'Success!');
      }, err => {
        this.toastr.error('there is an error happend!', 'Oops!');
      }) ;
    }
  }
  back() {
    this.location.back() ;
    this.toastr.warning('You are leaving this page .', 'Alert!');
  }
  setUserOrder(ShippingForm: FormGroup) {
    const shippingData = ShippingForm.value ;
    const formData = new FormData() ;
    if (ShippingForm.valid && this.loginUser != null) {
      shippingData.isDefault = true ;
      formData.append('user' , JSON.stringify(this.loginUser.user)) ;
      formData.append('shippingAddress' , JSON.stringify(shippingData)) ;
      this.orderService.setUserOrder(formData).subscribe((response) => {
        this.toastr.success('You are successfully', 'Success!');
      }, err => {
        this.toastr.error('there is an error happend!', 'Oops!');
      }) ;
    }
    this.ShippingForm.reset() ;
  }
  login(loginForm: FormGroup) {
    const UserData = loginForm.value;
    if (loginForm.valid) {
      this.userService.loginUsers(UserData).subscribe(
        response => {
          if (response) {
            if (response.token) {
              localStorage.setItem('currentUser', JSON.stringify(response));
              if (response.user.role === 'ADMIN') {
                this.toastr.success('You are successfully login as an admin', 'Success!');
                this.router.navigate(['/adminpage']);
              }
              if (response.user.role === 'USER') {
                this.toastr.success('You are successfully login', 'Success!');
                this.router.navigate([this.returnUrl]);
              }
            }
          }
        }, err => {
          this.toastr.error('there is an error happend!', 'Oops!');
          if (err.status === 403) {
            this.badPassword = true ;
            this.toastr.error('username and password didnt matched', 'Oops!');
          }
        }
      );
      this.loginForm.reset();
    }
    this.loginForm.reset() ;
  }
  getShippingAddressByUser() {
    this.orderService.getShippingAddressByUser(this.loginUser.user).subscribe(res => {
      this.toastr.success('You are successfully', 'Success!');
    }, err => {
      this.toastr.error('there is an error happend!', 'Oops!');
    }) ;
  }
  getAllShippingAddressByUser() {
    this.orderService.getAllShippingAddressByUser(this.loginUser.user).subscribe(res => {
      this.toastr.success('You are successfully', 'Success!');
    }, err => {
      this.toastr.error('there is an error happend!', 'Oops!');
    }) ;
  }
  getAllUserOrderByUser() {
    this.orderService.getAllUserOrderByUser(this.loginUser.user).subscribe(res => {
      this.toastr.success('You are successfully', 'Success!');
    }, err => {
      this.toastr.error('there is an error happend!', 'Oops!');
    }) ;
  }
}
