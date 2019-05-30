import { Component, OnInit, OnDestroy } from '@angular/core';
import {
  FormControl,
  Validators,
  FormBuilder,
  FormGroup
} from '@angular/forms';
import { LoginService } from 'src/app/service/login.service';
import { UserService } from 'src/app/service/user.service';
import { AdminService } from 'src/app/service/admin.service';
import { Category } from 'src/app/config/category';
import { CategoryService } from 'src/app/service/category.service';
import { Subscription } from 'rxjs';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-products-admin',
  templateUrl: './products-admin.component.html',
  styleUrls: ['./products-admin.component.css']
})
export class ProductsAdminComponent implements OnInit {
  users$;
  productForm: FormGroup;
  loginUser: any = {};
  products$;
  categories$;
  subCategories$;
  mainCategories$;
  selectedOption;
  productFile: any = File;
  allFiles: any = [];
  constructor(
    private loginService: LoginService,
    private fb: FormBuilder,
    private userService: UserService,
    private adminService: AdminService,
    private categoryService: CategoryService,
    private toastr: ToastrService
  ) {
    this.loginService.isLoggedIn();
    this.loginUser = JSON.parse(localStorage.getItem('currentUser'));
    // product Form
    this.productForm = this.fb.group({
      productName: new FormControl('', [Validators.required]),
      color: new FormControl('', [Validators.required]),
      size: new FormControl('', [Validators.required]),
      coupon: new FormControl('', [Validators.required]),
      slider: new FormControl('', [Validators.required]),
      defaultSize: new FormControl('', [Validators.required]),
      productSummary: new FormControl('', [
        Validators.required,
        Validators.minLength(3)
      ]),
      productDescription: new FormControl('', [
        Validators.required,
        Validators.minLength(3)
      ]),
      productDate: new FormControl('', [Validators.required]),
      // productViews: new FormControl('', [Validators.required]),
      productPrice: new FormControl('', [Validators.required]),
      productCondition: new FormControl('', [Validators.required]),
      quantity: new FormControl('', [Validators.required]),
      productCategory: new FormGroup({
        mainCategoryName: new FormControl('', [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(21)
        ]),
        subCategoryName: new FormControl('', [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(21)
        ])
      })
    });
  }

  ngOnInit() {
    this.getAllProducts();
    this.getCategories();
    this.getMainCategories();
    if (this.selectedOption != null) {
      this.getSubCategories(this.selectedOption);
    }
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
    const formData = new FormData();
    if (product.valid) {
      formData.append('product', JSON.stringify(productData));
      formData.append('productFile', this.productFile);
      this.adminService.createProducts(formData).subscribe(
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
  deleteProduct(product) {
    if (product != null) {
      if (confirm('are you sure want to delete this product ?')) {
        this.categories$ = this.adminService.deleteCategories(
          product.productId
        );
        this.toastr.warning('You are deleteing a product.', 'Alert!');
      }
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
  getAllProducts() {
    this.products$ = this.adminService.getProducts(this.loginUser.token);
  }
  getAllCategories() {
    this.categories$ = this.adminService.getCategories(this.loginUser.token);
  }
}
