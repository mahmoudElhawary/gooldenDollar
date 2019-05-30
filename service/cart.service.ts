import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) {
    this.http = http ;
  }

  getAllCarts(): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.get('http://localhost:9191/getAllCarts' , {headers} ) ;
  }
  saveCart(data): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.post('http://localhost:9191/saveCart', data , {headers} ) ;
  }
  saveUserProductsCart(data): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.post('http://localhost:9191/saveUserProductsCart', data , {headers} ) ;
  }
  getAllUserCarts(User): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.post('http://localhost:9191/getAllUserCarts', User , {headers} ) ;
  }
  getUserCart(User): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.post('http://localhost:9191/getUserCart', User , {headers} ) ;
  }
  deleteCartByProduct(product): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.post('http://localhost:9191/deleteCartByProduct', product , {headers} ) ;
  }
}
