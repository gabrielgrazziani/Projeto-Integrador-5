import { Component, OnInit } from '@angular/core';
import { OrdemServicoService } from '../ordem-servico.service';
import { Pedido } from '../pedido.model';

@Component({
  selector: 'app-lista-pedidos',
  templateUrl: './lista-pedidos.component.html',
  styleUrls: ['./lista-pedidos.component.css']
})
export class ListaPedidosComponent implements OnInit {

  constructor(private listaPedidoService: OrdemServicoService) { }


  listaPedidos: Pedido[]

  ngOnInit(): void {
    this.consulta();

  }

  consulta(){
    this.listaPedidoService.listarTodasOrdensServicos().toPromise().then(response => {
      console.log(response)
      this.listaPedidos = response;
    });


    
  }

}
