import { Component, Input, OnInit } from '@angular/core';
import { ProdutosComponent } from '../produtos/produtos.component';
import { Produto } from '../produto.model';
import { OrdemServico } from '../ordem-servico.model';
import { Item } from '../item.model';
import { OrdemServicoService } from '../ordem-servico.service';


@Component({
  selector: 'app-servicos',
  templateUrl: './servicos.component.html',
  styleUrls: ['./servicos.component.css']
})
export class ServicosComponent implements OnInit {

  @Input() cart: Produto[];

  descricao: string;

  constructor(private ordemServicoService: OrdemServicoService) { }

  ngOnInit(): void {

    console.log(this.cart);

  }

  valorTotal(){
    var total: number = 0;
    for(let i of this.cart){
      total = total + i.valorComercial;
    }
    return total;
  }

  conversor(){
    var itens: Item[];
    itens = [];
    for(var i = 0; i < this.cart.length; i++ ){

      const item = new Item();
      item.idServicoProduto = this.cart[i].id;
      item.quantidade = 1;
      itens.push(item)

    }
    const ordemServico = new OrdemServico();
    ordemServico.items = itens;
    ordemServico.descricao = this.descricao;
    return ordemServico;
  }

  comprar(){
    console.log(this.conversor());
    var v = this.conversor();
    this.ordemServicoService.adicionarOrdemServico(v).toPromise().then(retorno => {
      console.log(retorno);
    });
  }


}
