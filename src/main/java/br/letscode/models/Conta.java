package br.letscode.models;

import java.time.Period;

public abstract class Conta {

    private int numero;
    private Pessoa cliente;

    public Conta(int numero, Pessoa cliente) {
        this.numero = numero;
        this.cliente = cliente;
    }

    public abstract void processaRendimentos(Period periodo);
}
