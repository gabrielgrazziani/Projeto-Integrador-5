import { Component, OnInit } from '@angular/core'; 
import { ServicesService } from '../service/services.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styles: [`
        :host ::ng-deep .p-password input {
            width: 15rem
        }
    `]
})


export class LoginComponent implements OnInit {

  login: string

  senha: string

  disabled: boolean = true;
  
  constructor(private auth: ServicesService) { }

  ngOnInit(): void {
  }

  loginPage(){
    this.auth.login(this.login, this.senha)
  }


}
