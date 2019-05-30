import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http: HttpClient) {
    this.http = http ;
  }

  getRequiredProduct(data): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.post('http://localhost:9191/getRequiredProduct' , data , {headers}) ;
  }
  getRequiredProductsName(data): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.post('http://localhost:9191/getRequiredProductsName' , data , {headers}) ;
  }
  searchAllProductsByMainCategory(categoryName): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.post('http://localhost:9191/searchAllProductsByMainCategory' , categoryName , {headers}) ;
  }
  SearchAllProductsBySubCategory(categoryName): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.post('http://localhost:9191/SearchAllProductsBySubCategory' , categoryName , {headers}) ;
  }
}
