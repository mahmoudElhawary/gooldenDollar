import { Injectable, OnInit, Input } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
    this.http = http ;
  }

  signup(user): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin' : '*'}) ;
    return this.http.post('http://localhost:9191/signup' , user , {headers}) ;
  }
  update(user): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin' : '*'}) ;
    return this.http.post('http://localhost:9191/update' , user , {headers}) ;
  }
  loginUsers(user): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.post('http://localhost:9191/login' , user , {headers}) ;
  }
  getUserAddress(user): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.post('http://localhost:9191/getUserAddress' , user , {headers}) ;
  }
  getUsers(token: string): Observable<any> {
    const headers = new HttpHeaders({'Authorization': 'Bearer ' + token}) ;
    return this.http.get('http://localhost:9191/getUsers' , {headers}) ;
  }
  getUser(token: string): Observable<any> {
    const headers = new HttpHeaders({'Authorization': 'Bearer ' + token}) ;
    return this.http.get('http://localhost:9191/getUser' , {headers}) ;
  }
  getUserById(id): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.get('http://localhost:9191/findUserById/' + id , {headers}) ;
  }
  getProduct(id): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.get('http://localhost:9191/getUserProduct/' + id  , {headers}) ;
  }
  deleteProduct(id): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.delete('http://localhost:9191/deleteUserProduct/' + id  , {headers}) ;
  }
  createProducts(productDate): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.post('http://localhost:9191/saveUserProduct' , productDate , {headers}) ;
  }
  updateProducts(productDate): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.post('http://localhost:9191/updateProducts' , productDate , {headers}) ;
  }
  getProducts(): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.get('http://localhost:9191/allUserProducts' , {headers} ) ;
  }
  getProductsByUser(user): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.post('http://localhost:9191/getUserProductsByUser' , user , {headers} ) ;
  }
  getProductByUserId(id) {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.get('http://localhost:9191/getProductsByUserId/' + id , {headers} ) ;
  }
  allProductsByMainCategory(mainName): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.post('http://localhost:9191/allProductsByMainCategory' , mainName , {headers}) ;
  }
  allProductsBySubCategory(subName): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.post('http://localhost:9191/allProductsBySubCategory' , subName , {headers}) ;
  }
  maxRatingProducts(): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.get('http://localhost:9191/maxRatingUserProducts' , {headers} ) ;
  }
  maxSellingProducts(): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.get('http://localhost:9191/maxSellingUserProducts' , {headers} ) ;
  }
  maxViewProducts(): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.get('http://localhost:9191/maxViewUserProducts' , {headers} ) ;
  }
  maxLikesUserProducts(): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.get('http://localhost:9191/maxLikesUserProducts' , {headers} ) ;
  }
  newestProducts(): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.get('http://localhost:9191/newestUserProducts' , {headers} ) ;
  }
}
