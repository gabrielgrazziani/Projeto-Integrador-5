import { Cliente } from "./cliente.model";
import { Funcionario } from "./funcionario.model";

export class Pedido {

    id: number
    cliente: Cliente
    dataEmissao: string
    dataFechamento: string
    descricao: string
    funcionario: Funcionario
    status: string
}
