import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class ServicesService {

  constructor(private http: HttpClient) { }

  login(usuario: string, senha: string): Promise<void> {
    const headers = new HttpHeaders()
    .append('Content-Type', 'application/x-www-form-urlencoded')
    .append('Authorization', `Basic ${usuario}:${senha}`);
    
    return this.http.get("http://localhost:8080/cliente/ordem_servico", { headers })
    .toPromise()
    .then(response => {
        console.log(response);
    })
    .catch(response => {
        console.log(response);
    })

}

}
