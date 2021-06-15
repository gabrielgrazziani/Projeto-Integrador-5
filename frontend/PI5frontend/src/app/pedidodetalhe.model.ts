import { Cliente } from "./cliente.model";
import { Funcionario } from "./funcionario.model";
import { Itemdetalhado } from "./itemdetalhado.model";

export class Pedidodetalhe {

    cliente: Cliente
    dataEmissao: string
    dataFechamento: string
    descricao: string
    funcionario: Funcionario
    id: number
    items: Itemdetalhado[]
    total: number
    status: string
}
