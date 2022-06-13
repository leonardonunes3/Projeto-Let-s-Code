package br.letscode.models;

import java.math.BigDecimal;
import java.time.Period;

public class ContaCorrente extends ContaSaldo {

    public ContaCorrente(int numero, Pessoa cliente) {
        super(numero, cliente);
    }

    @Override
    public void sacar(BigDecimal valor) {
        super.sacar(valor);// TODO: adicionar taxa de serviço?
    }

    @Override
    public void depositar(BigDecimal valor) {
        super.depositar(valor);// TODO: adicionar taxa de serviço?
    }

    @Override
    public void processaRendimentos(Period periodo) {}
}
