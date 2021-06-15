import { Component, OnInit } from '@angular/core';
import { Produto } from '../produto.model';
import { ProdutoService } from '../produto.service';

@Component({
  selector: 'app-criar-produto',
  templateUrl: './criar-produto.component.html',
  styleUrls: ['./criar-produto.component.css']
})
export class CriarProdutoComponent implements OnInit {

  produto: Produto = new Produto;

  constructor(private prdutoService: ProdutoService) { }

  ngOnInit(): void {
  }

  inserirProduto(){
    this.prdutoService.adicionarProduto(this.produto).toPromise().then(produto => {
      console.log("OK");
      console.log(this.produto);
      this.produto = new Produto();
    }).catch(() => {
      alert('Dados Incorretos');
    })
  }

}
