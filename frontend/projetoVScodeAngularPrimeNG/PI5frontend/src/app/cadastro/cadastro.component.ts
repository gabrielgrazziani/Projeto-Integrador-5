import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styles: [`
        :host ::ng-deep .p-password input {
            width: 15rem
        }
    `]
})
export class CadastroComponent implements OnInit {


  value2: string
  value3: string
  value: boolean

  checked: boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

}
