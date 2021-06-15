import { Cliente } from "./cliente.model";
import { Funcionario } from "./funcionario.model";
import { Item } from "./item.model";

export class Pedidodetalhe {

    cliente: Cliente
    dataEmissao: string
    dataFechamento: string
    descricao: string
    funcionario: Funcionario
    id: number
    items: Item[]
    status: string
}
