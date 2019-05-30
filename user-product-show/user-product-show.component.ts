import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductsService } from 'src/app/service/products.service';
import { Location } from '@angular/common';
import { Subscription } from 'rxjs';
import { LoginService } from 'src/app/service/login.service';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';
import { CartService } from 'src/app/service/cart.service';
import { ToastrService } from 'ngx-toastr';
import { UserService } from 'src/app/service/user.service';
import { FormControl, Validators, FormGroup } from '@angular/forms';
@Component({
  selector: 'app-user-product-show',
  templateUrl: './user-product-show.component.html',
  styleUrls: ['./user-product-show.component.css']
})
export class UserProductShowComponent implements OnInit {
  currentRate;
  currentLikes;
  rating = new FormControl('', Validators.required);
  likes = new FormControl('', Validators.required);
  categoryName: string;
  id: number;
  loginUser: any = {};
  subscribtion: Subscription;
  product: any = {};
  subCategories$;
  commentForm: FormGroup;
  constructor(
    private route: ActivatedRoute,
    private productService: ProductsService,
    private location: Location,
    private router: Router,
    private loginService: LoginService,
    private configRate: NgbRatingConfig,
    private cartService: CartService,
    private toastr: ToastrService,
    private userService: UserService
  ) {
    this.loginService.isLoggedIn();
    this.loginUser = JSON.parse(localStorage.getItem('currentUser'));
    this.commentForm = new FormGroup({
      commentCotents: new FormControl('')
    });
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.categoryName = params.get('categoryName');
    });
    this.id = +this.route.snapshot.paramMap.get('id');
    console.log(this.id);
    this.getProduct();
  }

  getProduct() {
    if (this.id != null) {
      this.userService.getProduct(this.id).subscribe(
        data => {
          this.product = data;
          this.currentRate = this.product.rating;
          this.currentLikes = this.product.likes;
          this.toastr.info(
            'You Are Selected this Product ' + this.product.productName
          );
        },
        err => {
          this.toastr.error('you have an error !!', 'oopps !');
        }
      );
    } else {
      this.toastr.warning('this product is not founded');
    }
  }
  updateProducts(product) {
    if (this.loginUser.user != null && product != null) {
      const productData = product.value;
      const pcommentData = this.onSubmit();
      productData.rating = this.rating.value ;
      productData.productData = JSON.stringify(pcommentData);
      const formData = new FormData();
        formData.append('product', JSON.stringify(productData));
        formData.append('user', JSON.stringify(this.loginUser.user));
        this.userService.updateProducts(formData).subscribe(
          data => {
            console.log(data);
            this.toastr.success('You are create an product!', 'Success!');
          },
          err => {
            console.log(err);
            this.toastr.error('you have an error', 'Oops!');
          }
        );
    }
  }
  onSubmit() {
    return this.commentForm.value ;
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
    this.router.navigate(['userProducts/' + product]);
    location.reload();
    this.toastr.info('You Are show the products you selected ');
  }
  addToCart(item) {
    if (item != null) {
      const formData = new FormData();
      formData.append('product', JSON.stringify(item));
      formData.append('user', JSON.stringify(this.loginUser.user));
      this.cartService.saveUserProductsCart(formData).subscribe(
        response => {
          this.toastr.success(
            'you are added this product to your cart successfully'
          );
          this.router.navigate(['/cart']);
        },
        err => {
          this.toastr.error('you have an error' + err);
        }
      );
    } else {
      this.toastr.warning('this product is empty');
    }
  }
  back() {
    this.location.back();
    this.toastr.info('You Are leaving this page ');
  }

  toggle() {
    if (this.rating.disabled) {
      this.rating.enable();
    } else {
      this.rating.disable();
    }
  }
}
