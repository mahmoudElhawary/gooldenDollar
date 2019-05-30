import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductsService } from 'src/app/service/products.service';
import { Location } from '@angular/common';
import { LoginService } from 'src/app/service/login.service';
import { UserService } from 'src/app/service/user.service';
import { CartService } from 'src/app/service/cart.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-products-details',
  templateUrl: './products-details.component.html',
  styleUrls: ['./products-details.component.css']
})
export class ProductsDetailsComponent implements OnInit {
  products: any[] ;
  products$ ;
  page = 1 ;
  product: any = {};
  categoryName: string;
  id: number;
  loginUser: any = {};
  constructor(
    private route: ActivatedRoute,
    private productService: ProductsService,
    private router: Router,
    private location: Location,
    private loginService: LoginService,
    private cartService: CartService ,
    private userService: UserService ,
    private toastr: ToastrService
  ) {
    this.loginService.isLoggedIn();
    this.loginUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    this.categoryName = this.route.snapshot.paramMap.get('categoryName');
    this.getProductsByMain();
    this.getProductsBySub();
  }

  getProductsByMain() {
    this.products$ = this.productService
      .allProductsByMainCategory(this.categoryName);
  }
  getProductsBySub() {
    this.products$ = this.productService
      .allProductsBySubCategory(this.categoryName);
  }
  back() {
    this.location.back();
    this.toastr.warning('You are leaving this page.', 'Alert!');
  }
  view(product) {
    this.router.navigate(['product/' + product]);
    this.toastr.info('Just loaded your selected product for you.');
  }
  viewByMainCategory(CategoryName) {
    this.router.navigate(['products/' + CategoryName]);
    this.toastr.info('Just loaded some products for you.');
  }
  viewBySubCategory(subCategoryName) {
    this.router.navigate(['products/' + subCategoryName]);
    this.toastr.info('Just loaded some products for you.');
  }
  addToCart(item) {
    if ( (item != null) && (this.loginUser.user != null) ) {
    const formData = new FormData();
    formData.append('product', JSON.stringify(item));
    formData.append('user', JSON.stringify(this.loginUser.user));
    console.log(this.loginUser.user.email) ;
    this.cartService.saveCart(formData).subscribe((response) => {
      this.toastr.success('You are successfully added this product to your cart', 'Success!');
        this.router.navigate(['/cart']) ;
      } , err => {
        this.toastr.error('there is an error happend!', 'Oops!');
      });
    } else {
      this.toastr.error('this cart is empty!', 'Oops!');
    }
  }
}
