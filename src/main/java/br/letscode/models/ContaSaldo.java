package br.letscode.models;

import java.math.BigDecimal;

public abstract class ContaSaldo extends Conta {

    private BigDecimal saldo;

    public ContaSaldo(int numero, Pessoa cliente) {
        super(numero, cliente);
        this.saldo = BigDecimal.ZERO;
    }

    public void sacar(BigDecimal valor){
        if(valor.signum() == -1) throw new IllegalArgumentException("Valor de saque não pode ser negativo");
        if(valor.compareTo(this.saldo) > 0) throw  new IllegalStateException("Saldo insuficiente para realizar a operação.");
        this.saldo = this.saldo.subtract(valor);
    }

    public void depositar(BigDecimal valor){
        if(valor.signum() == -1) throw new IllegalArgumentException("Valor de depósito não pode ser negativo");
        this.saldo = this.saldo.add(valor);
    }

    public void transferir(ContaSaldo contaDestino, BigDecimal valor){
        this.sacar(valor);
        contaDestino.depositar(valor);
    }
}
