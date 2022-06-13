package br.letscode.models;

import java.time.Period;

public class ContaInvestimento extends Conta {

    public ContaInvestimento(int numero, Pessoa cliente) {
        super(numero, cliente);
    }

    @Override
    public void processaRendimentos(Period periodo) {
        
    }
}
