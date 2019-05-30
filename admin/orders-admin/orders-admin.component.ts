import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/service/login.service';
import { UserService } from 'src/app/service/user.service';
import { AdminService } from 'src/app/service/admin.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-orders-admin',
  templateUrl: './orders-admin.component.html',
  styleUrls: ['./orders-admin.component.css']
})
export class OrdersAdminComponent implements OnInit {
  loginUser: any = {};
  constructor(
    private loginService: LoginService,
    private userService: UserService,
    private adminService: AdminService ,
    private toastr: ToastrService
  ) {
    this.loginService.isLoggedIn();
    this.loginUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {}
}
