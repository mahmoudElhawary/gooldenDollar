import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductsService } from 'src/app/service/products.service';
import { Location } from '@angular/common';
import { Subscription } from 'rxjs';
import { LoginService } from 'src/app/service/login.service';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';
import { CartService } from 'src/app/service/cart.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-one-product-details',
  templateUrl: './one-product-details.component.html',
  styleUrls: ['./one-product-details.component.css']
})
export class OneProductDetailsComponent implements OnInit, OnDestroy {
  categoryName: string;
  id: number;
  loginUser: any = {};
  subscribtion: Subscription;
  product: any = {};
  subCategories$;
  constructor(
    private route: ActivatedRoute,
    private productService: ProductsService,
    private location: Location,
    private router: Router,
    private loginService: LoginService,
    private configRate: NgbRatingConfig,
    private cartService: CartService,
    private toastr: ToastrService
  ) {
    this.loginService.isLoggedIn();
    this.loginUser = JSON.parse(localStorage.getItem('currentUser'));
    configRate.readonly = true ;
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.categoryName = params.get('categoryName');
    });
    this.id = +this.route.snapshot.paramMap.get('id') ;
    console.log(this.id);
    this.getProduct();
  }

  getProduct() {
    this.subscribtion = this.productService
      .getProduct(this.id)
      .subscribe(data => {
        this.product = data;
        console.log(data);
      });
  }
  viewBySubCategory(subCategoryName) {
    this.router.navigate(['products/' + subCategoryName]);
    this.toastr.info('You Are show the products by SubCategory');
  }
  getProducts(subName) {
    this.subCategories$ = this.productService.allProductsBySubCategory(subName);
    this.toastr.info('You Are show our products ');
  }
  view(product) {
    this.router.navigate(['product/' + product]);
    location.reload();
    this.toastr.info('You Are show the products you selected ');
  }
  addToCart(item) {
    if (item != null) {
      const formData = new FormData();
      formData.append('product', JSON.stringify(item));
      formData.append('user', JSON.stringify(this.loginUser.user));
      this.cartService.saveCart(formData).subscribe(response => {
        this.toastr.success('You are added to your cart successfully!', 'Success!');
        this.router.navigate(['/cart']) ;
      });
    }
  }
  back() {
    this.location.back();
    this.toastr.info('You Are leaving this page ');
  }
  ngOnDestroy(): void {
    this.subscribtion.unsubscribe();
  }
}
