import { Injectable } from '@angular/core';
import { Cliente } from './cliente.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Pessoa } from './pessoa.model';
import { AuthService } from './auth.service';


const url = 'http://localhost:8080/cliente'
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor(private http: HttpClient, private auth: AuthService) { }

  consultar (): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(url);
  }

  adicionar (Cliente: Cliente): Observable<Cliente>{
    return this.http.post<Cliente>(url, Cliente, httpOptions);
  }

  buscaPessoaLogada ():Observable<Pessoa> {
    const headers = new HttpHeaders().append('Authorization', 'Bearer ' + this.auth.retornarToken());
    return this.http.get<Pessoa>('http://localhost:8080/pessoa', {headers});
  }
}
