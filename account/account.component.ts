import { Component, OnInit, OnDestroy } from '@angular/core';
import {
  FormGroup,
  Validators,
  FormControl,
  FormBuilder
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from 'src/app/service/login.service';
import { UserService } from 'src/app/service/user.service';
import { NgbTabsetConfig } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { CategoryService } from 'src/app/service/category.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  productForm: FormGroup;
  loginUser: any = {};
  products$;
  categories$;
  subCategories$;
  mainCategories$;
  selectedOption;
  productFile: any = File;
  allFiles: any = [];
  user: any = {};
  page = 1 ;
  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private loginService: LoginService,
    private router: Router,
    private route: ActivatedRoute,
    private config: NgbTabsetConfig,
    private toastr: ToastrService ,
    private categoryService: CategoryService) {
      this.config.justify = 'center' ;
      config.type = 'pills';
    this.loginService.isLoggedIn();
    this.loginUser = JSON.parse(localStorage.getItem('currentUser'));
    // product Form
    this.productForm = this.fb.group({
      productName: new FormControl('', [Validators.required]),
      color: new FormControl(''),
      size: new FormControl(''),
      coupon: new FormControl('', [Validators.required]),
      defaultSize: new FormControl(''),
      productSummary: new FormControl('', [
        Validators.minLength(3)
      ]),
      productDescription: new FormControl('', [
        Validators.required,
        Validators.minLength(3)
      ]),
      // productViews: new FormControl('', [Validators.required]),
      productPrice: new FormControl('', [Validators.required]),
      productCondition: new FormControl('', [Validators.required]),
      quantity: new FormControl('', [Validators.required]),
      productCategory: new FormGroup({
        mainCategoryName: new FormControl('', [
          Validators.required,
        ]),
        subCategoryName: new FormControl('', [
          Validators.required,
        ])
      })
    });
  }

  ngOnInit() {
    if (this.loginUser.token) {
      this.userService.getUser(this.loginUser.token).subscribe(userData => {
        this.user = userData;
      });
    }
    this.getCategories();
    this.getMainCategories();
    if (this.selectedOption != null) {
      this.getSubCategories(this.selectedOption);
    }
    this.getProducts() ;
  }
  getProducts() {
    if (this.loginUser != null) {
      this.products$ = this.userService.getProductsByUser(this.loginUser.user) ;
    } else {
      this.toastr.warning('you must login first') ;
    }
  }
  deleteProduct(id) {
    if (id != null) {
      if (confirm('Are you sure want to delete this Product !?')) {
        this.userService.deleteProduct(id).subscribe(res => {
          this.toastr.success('you Are Successfully Deleted this product') ;
          this.products$ = this.userService.getProductsByUser(this.loginUser.user) ;
        }, err => {
          this.toastr.error('you are having an error') ;
        }) ;
      } else {
        this.toastr.info('ohh !! this product canceled to delete') ;
      }
    } else {
      this.toastr.error('you are having an error') ;
    }
  }
  getSelectedOptionText(event) {
    this.selectedOption = event.target.options[event.target.selectedIndex].text;
    this.getSubCategories(this.selectedOption);
  }
  getCategories() {
    this.categories$ = this.categoryService.getAllCategories();
  }
  getMainCategories() {
    this.mainCategories$ = this.categoryService.getMainCategories();
  }
  getSubCategories(mainName) {
    this.subCategories$ = this.categoryService.getSubCategoriesByMainName(
      mainName
    );
  }
  onSelectFile(event) {
    // profile image
    const file = event.target.files[0];
    // multiple file
    const files = event.target.files;
    for (let index = 0; index < files.length; index++) {
      this.allFiles.push(files[index]);
      // console.log(files[index]) ;
    }
    this.productFile = file;
    this.toastr.info('product photo is loaded successfully');
    // console.log(file);
  }
  saveProduct(product: FormGroup) {
    console.log(product.value);
    const productData = product.value;
    productData.user = this.loginUser.user ;
    const formData = new FormData();
    if (product.valid) {
      formData.append('product', JSON.stringify(productData));
      formData.append('user', JSON.stringify(this.loginUser.user));
      formData.append('productFile', this.productFile);
      this.userService.createProducts(formData).subscribe(
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
}
