import { Component, OnInit } from '@angular/core';
import { Cliente } from '../cliente.model';
import { ClienteService } from '../cliente.service';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})


export class CadastroComponent implements OnInit {

  cliente: Cliente = new Cliente;

  constructor(private clienteService: ClienteService) { }

  ngOnInit(): void {
  }

  cadastro(){
    this.clienteService.adicionar(this.cliente).toPromise().then(pessoa => {
      console.log("OK!")
    })
    console.log(this.cliente)
  }

}
