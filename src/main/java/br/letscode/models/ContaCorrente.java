package br.letscode.models;

import java.math.BigDecimal;
import java.time.Period;

public class ContaCorrente extends ContaSaldo {

    public ContaCorrente(int numero, Pessoa cliente) {
        super(numero, cliente);
    }

    @Override
    public void sacar(BigDecimal valor) {
        if (this.getCliente() instanceof PessoaJuridica) {
            valor = valor.multiply(BigDecimal.valueOf(1.005));
        }
        super.sacar(valor);
    }

    @Override
    public void transferir(ContaSaldo contaDestino, BigDecimal valor) {
        super.transferir(contaDestino, valor);
    }

    @Override
    public void processaRendimentos(Period periodo) {}
}
