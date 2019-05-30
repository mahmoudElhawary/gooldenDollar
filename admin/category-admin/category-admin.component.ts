import { Component, OnInit, OnDestroy } from '@angular/core';
import {
  FormControl,
  Validators,
  FormGroup,
  FormBuilder,
  FormArray
} from '@angular/forms';
import { LoginService } from 'src/app/service/login.service';
import { AdminService } from 'src/app/service/admin.service';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-category-admin',
  templateUrl: './category-admin.component.html',
  styleUrls: ['./category-admin.component.css']
})
export class CategoryAdminComponent implements OnInit {
  categoryForm: FormGroup;
  categories$;
  selected;
  maninCategories$;
  subCategories$;
  loginUser: any = {};
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private loginService: LoginService,
    private adminService: AdminService,
    private toastr: ToastrService
  ) {
    this.loginService.isLoggedIn();
    this.loginUser = JSON.parse(localStorage.getItem('currentUser'));

    // category Form
    this.categoryForm = this.fb.group({
      mainCategoryName: new FormControl('', [
        Validators.required,
        Validators.minLength(3)
      ]),
      subCategoryName: new FormControl('', [
        Validators.required,
        Validators.minLength(3)
      ])
    });
  }

  ngOnInit() {
    this.getAllCategories();
    this.getMainCategories();
    console.log(this.selected);
  }

  getAllCategories() {
    this.categories$ = this.adminService.getCategories(this.loginUser.token);
  }
  getMainCategories() {
    this.maninCategories$ = this.adminService.getMainCategories(
      this.loginUser.token
    );
  }
  createCategory(categ: FormGroup) {
    console.log(categ.value);
    const categoryData = categ.value;
    if (categ.valid) {
      this.adminService.createCategories(categoryData).subscribe(
        data => {
          console.log(data);
          this.toastr.success('You are create a Ctegory', 'Success!');
        },
        err => {
          console.log(err);
          this.toastr.error('This is an error' + err, 'Oops!');
        }
      );
    }
  }
  delete(category) {
    if (category != null) {
      if (confirm('are you sure want to delete this product ?')) {
        this.adminService
          .deleteCategories(category.categoryId)
          .subscribe(response => {
            this.categories$.splice(category);
            this.toastr.success('You are awesome!', 'Success!');
            console.log(response);
          } , err => {
            this.toastr.error('This is an error' + err, 'Oops!');
          });
      }
    }
  }
}
