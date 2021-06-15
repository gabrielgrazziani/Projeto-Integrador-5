import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { ClienteService } from '../cliente.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginn: string;
  senha: string;

  constructor(private auth: AuthService, private router: Router, private pessoa: ClienteService) { }

  login(){
     this.auth.login(this.loginn, this.senha).then(() => {
      this.pessoa.buscaPessoaLogada().toPromise().then(p => {
        if(p.perfil === 'CLIENTE'){
          this.router.navigate(['/produtos']);          
        }else{
          this.router.navigate(['/criaproduto']);
        }
      }) 
     }).catch(()=>{
       alert("dados incorretos");
     });
  }
}
