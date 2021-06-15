import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OrdemServicoService } from '../ordem-servico.service';
import { Pedidodetalhe } from '../pedidodetalhe.model';

@Component({
  selector: 'app-pedido',
  templateUrl: './pedido.component.html',
  styleUrls: ['./pedido.component.css']
})
export class PedidoComponent implements OnInit {

  constructor(private pedidoService: OrdemServicoService, private route: ActivatedRoute) { }

  pedido: Pedidodetalhe = new Pedidodetalhe();

  ngOnInit(): void {
    this.route.params.subscribe( parametros => {
      if (parametros['id']){
        const id: number = parametros['id']
        this.consultaComId(id);
      }else {
        console.log("Erro !")
      }
    })
  }

  consultaComId(id: number){
    this.pedidoService.buscarOrdemServicoPorId(id).toPromise().then(response => {
      console.log(response)
      this.pedido = response;
    });
  }  

}
