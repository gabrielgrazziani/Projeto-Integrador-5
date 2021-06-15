import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  oauthTokenUrl = 'http://localhost:8080/oauth/token';

  constructor(private http: HttpClient) { }


  login(login: string, senha: string): Promise<void> {
    const headers = new HttpHeaders()
      .append('Content-Type', 'application/x-www-form-urlencoded')
      .append('Authorization', 'Basic YW5ndWxhcjpzZW5oYTEyMw==');

    const body = `username=${login}&password=${senha}&grant_type=password`;

    return this.http.post(this.oauthTokenUrl, body, { headers, withCredentials: true }).toPromise().then((response: any) => {
      this.armazenarToken(response['access_token']);
      console.log(response);
      
    }).catch(response => {
      console.log('Gerou exceção!');
      console.log(JSON.stringify(response));
      if (response.status === 400) {
        if (response.error === 'invalid_grant') {
          console.log('Usuário ou senha inválida!');
          return Promise.reject('Usuário ou senha inválida!');
        }
      }

      return Promise.reject(response);
    });

    

  }

  retornarToken() {
    return localStorage.getItem('token');
  }

  private armazenarToken(token: string) {
    localStorage.setItem('token', token);
  }

  private carregarToken() {
    const token = localStorage.getItem('token');

    if (token) {
      this.armazenarToken(token);
    }
  }

}




