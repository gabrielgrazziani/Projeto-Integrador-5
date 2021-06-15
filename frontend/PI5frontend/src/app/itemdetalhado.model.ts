import { Produto } from "./produto.model"

export class Itemdetalhado {

    id: number
    idOrdemServico: number
    quantidade: number
    servicoProduto: Produto
    soma: number
    valorUnidade: number
}
