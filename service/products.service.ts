import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  url = 'http://localhost:9191/' ;
  constructor(private http: HttpClient) {
    this.http = http ;
  }
  sliderProducts(): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.get(this.url + 'sliderProducts' , {headers} ) ;
  }
  getProducts(): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.get(this.url + 'allProducts' , {headers} ) ;
  }
  allProductsByMainCategory(mainName): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.post(this.url + 'allProductsByMainCategory' , mainName , {headers}) ;
  }
  allProductsBySubCategory(subName): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.post(this.url + 'allProductsBySubCategory' , subName , {headers}) ;
  }
  allProductsByCategory(categoryName): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.post(this.url + 'allProductsByCategory' , categoryName , {headers}) ;
  }
  getProductsImages(): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.get(this.url + 'getProductsImages' , {headers} ) ;
  }
  getProduct(id): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.get(this.url + 'getProduct/' + id  , {headers}) ;
  }
  deleteProduct(id): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.delete(this.url + 'deleteProduct/' + id  , {headers}) ;
  }
  findAllByRating(): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.get(this.url + 'findAllByRating', {headers}) ;
  }
  findByMaxRating(): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.get(this.url + 'findByMaxRating', {headers}) ;
  }
  maxRatingProducts(): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.get(this.url + 'maxRatingProducts' , {headers} ) ;
  }
  maxSellingProducts(): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.get(this.url + 'maxSellingProducts' , {headers} ) ;
  }
  maxViewProducts(): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.get(this.url + 'maxViewProducts' , {headers} ) ;
  }
  newestProducts(): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.get(this.url + 'newestProducts' , {headers} ) ;
  }
}
