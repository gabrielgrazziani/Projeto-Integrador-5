import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginn: string;
  senha: string;

  constructor(private auth: AuthService) { }

  login(){
     this.auth.login(this.loginn, this.senha);
  }
}
