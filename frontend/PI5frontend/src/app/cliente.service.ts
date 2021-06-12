import { Injectable } from '@angular/core';
import { Cliente } from './cliente.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


const url = 'http://localhost:8080/cliente'
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor(private http: HttpClient) { }

  consultar (): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(url);
  }

  adicionar (Cliente: Cliente): Observable<Cliente>{
    return this.http.post<Cliente>(url, Cliente, httpOptions);
  }
}
