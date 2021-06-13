import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { OrdemServico } from './ordem-servico.model';
import { AuthService } from './auth.service';

const url = 'http://localhost:8080/cliente/ordem_servico'
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class OrdemServicoService {

  constructor(private http: HttpClient, private auth: AuthService) { }

  
  adicionarOrdemServico(ordem: OrdemServico): Observable<OrdemServico>{
    const headers = new HttpHeaders().append('Authorization', 'Bearer ' + this.auth.retornarToken());
    return this.http.post<OrdemServico>(url, ordem, {headers});
  }
}
