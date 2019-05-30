import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/service/login.service';
import { ActivatedRoute, Router } from '@angular/router';
import { SearchService } from 'src/app/service/search.service';
import { ToastrService } from 'ngx-toastr';
import { Location } from '@angular/common';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  loginUser: any = {};
  searchValue: string;
  products$;
  constructor(
    private location: Location ,
    private toastr: ToastrService,
    private searchService: SearchService,
    private router: Router,
    private route: ActivatedRoute,
    private loginService: LoginService,
  ) {
    this.loginService.isLoggedIn();
    this.loginUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnInit() {
    this.searchValue = this.route.snapshot.paramMap.get('searchValue') ;
    this.search();
  }

  search() {
    if (this.searchValue != null) {
      this.products$ = this.searchService.getRequiredProduct(this.searchValue);
      this.toastr.info('Just loaded some information for you.');
    } else {
      this.toastr.warning('didnt found any products  .', 'Alert!');
    }
  }
  back() {
    this.location.back();
    this.toastr.warning('you are leaving this page .', 'Alert!');
  }
  view(product) {
    this.router.navigate(['product/' + product]);
    this.toastr.info('Just loaded your product for you.');
  }
}
