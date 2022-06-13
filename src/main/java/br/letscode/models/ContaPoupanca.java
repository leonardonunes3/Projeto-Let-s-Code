package br.letscode.models;

import java.math.BigDecimal;
import java.time.Period;

public class ContaPoupanca extends ContaSaldo {

    public static BigDecimal RENDIMENTO_DIARIO = new BigDecimal("0.0002");

    public ContaPoupanca(int numero, Pessoa cliente) {
        super(numero, cliente);
    }

    @Override
    public void processaRendimentos(Period periodo) {
        if(periodo.isNegative()) throw new IllegalArgumentException("Periodo não pode ser negativo");
        this.depositar(RENDIMENTO_DIARIO.add(BigDecimal.ONE).pow(periodo.getDays()).subtract(BigDecimal.ONE));
    }
}
