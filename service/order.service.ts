import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) {
    this.http = http ;
  }

  setUserOrder(data): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.post('http://localhost:9191/setUserOrder' , data , {headers}) ;
  }
  getShippingAddressByUser(data): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.post('http://localhost:9191/getShippingAddressByUser' , data , {headers}) ;
  }
  getAllShippingAddressByUser(data): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.post('http://localhost:9191/getAllShippingAddressByUser' , data , {headers}) ;
  }
  getAllUserOrderByUser(data): Observable<any> {
    const headers = new HttpHeaders({'Access-Control-Allow-Origin': '*'}) ;
    return this.http.post('http://localhost:9191/getAllUserOrderByUser' , data , {headers}) ;
  }
}
