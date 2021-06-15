import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Produto } from './produto.model';
import { AuthService } from './auth.service';

const url = 'http://localhost:8080/servico_produto'
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {

  constructor(private http: HttpClient, private auth: AuthService) { }

  consultar (): Observable<Produto[]> {
    return this.http.get<Produto[]>(url);
  }

  adicionarProduto(produto: Produto): Observable<Produto> {
    const headers = new HttpHeaders().append('Authorization', 'Bearer ' + this.auth.retornarToken());
    return this.http.post<Produto>(url, produto, {headers});
  }
}
