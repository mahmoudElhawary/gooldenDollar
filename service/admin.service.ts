import { Injectable } from '@angular/core';
import { LoginService } from './login.service';
import { UserService } from './user.service';
import { Observable } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  loginUser: any = {};
  private _user: any;
  public get user(): any {
    return this._user;
  }
  constructor(
    private loginService: LoginService,
    private userService: UserService ,
    private http: HttpClient
  ) {
    this.http = http;
    this.loginService.isLoggedIn();
    this.loginUser = JSON.parse(localStorage.getItem('currentUser'));
    if (this.loginUser != null) {
      this.userService.getUser(this.loginUser.token).subscribe(
        (userData) => {
          this._user = userData.role;
          console.log(this.user) ;
        },
        err => {
          console.log(err);
        }
      );
    }
    console.log(this.user) ;
  }

  contactUsData(messageData): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.post('http://localhost:9191/setMessages' , messageData , {headers}) ;
  }
  getMessagesData(token: string): Observable<any> {
    const headers = new HttpHeaders({'Authorization': 'Bearer ' + token}) ;
    return this.http.get('http://localhost:9191/getMessages', {headers}) ;
  }
  createCategories(categoryDate): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.post('http://localhost:9191/saveCategoryMain' , categoryDate , {headers}) ;
  }
  getCategories(token: string): Observable<any> {
    const headers = new HttpHeaders({'Authorization': 'Bearer ' + token}) ;
    return this.http.get('http://localhost:9191/allCategoriesMain', {headers}) ;
  }

  getCategory(categoryId): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.get('http://localhost:9191/getCategoryMain/' + categoryId, {headers}) ;
  }
  getMainCategories(token: string): Observable<any> {
    const headers = new HttpHeaders({'Authorization': 'Bearer ' + token}) ;
    return this.http.get('http://localhost:9191/allMainCategoriesMain', {headers}) ;
  }
  getSubCategories(token: string): Observable<any> {
    const headers = new HttpHeaders({'Authorization': 'Bearer ' + token}) ;
    return this.http.get('http://localhost:9191/allSubCategoriesMain', {headers}) ;
  }
  // getSubCategoriesByMainName(token: string): Observable<any> {
  //   const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
  //   return this.http.get('http://localhost:9191/SubCategoriesByMainName', token , {headers}) ;
  // }

  deleteCategories(categoryId): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.delete('http://localhost:9191/deleteCategoryMain/' + categoryId, {headers}) ;
  }
  createProducts(productDate): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.post('http://localhost:9191/saveProduct' , productDate , {headers}) ;
  }
  getProducts(token: string): Observable<any> {
    const headers = new HttpHeaders({'Authorization': 'Bearer ' + token}) ;
    return this.http.get('http://localhost:9191/allProducts', {headers}) ;
  }
  deleteProducts(productId): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.delete('http://localhost:9191/deleteProduct/' + productId, {headers}) ;
  }
  getImages(): Observable<any> {
    return this.http.get('http://localhost:9191/getimage');
  }
}
