import { Component, Input, OnInit } from '@angular/core';
import { ProdutoService } from '../produto.service';
import { Produto } from '../produto.model';

@Component({
  selector: 'app-produtos',
  templateUrl: './produtos.component.html',
  styleUrls: ['./produtos.component.css']
})
export class ProdutosComponent implements OnInit {

  constructor(private produtoService: ProdutoService) { }


  cart: Produto[] = []
  listaProdutos: Produto[]

  ngOnInit(): void {
    this.consulta();

  }

  consulta(){
    this.produtoService.consultar().toPromise().then(response => {
      console.log(response)
      this.listaProdutos = response;
    });
    
  }

  adicionarCarrinho(produto: Produto){
    this.cart.push(produto);
    console.log(this.cart)
  }

  get produtosLista() {
    return this.cart;
  }

}
