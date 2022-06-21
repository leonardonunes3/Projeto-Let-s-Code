package br.letscode.models;

import java.math.BigDecimal;
import java.time.Period;
import java.util.*;

public class ContaInvestimento extends Conta {

    public ContaInvestimento(int numero, Pessoa cliente) {
        super(numero, cliente);
        investimentos = new HashMap<>();
    }

    private Map<String, Investimento> investimentos;

    @Override
    public void processaRendimentos(Period periodo) {
        for (Investimento investimento : investimentos.values()) {
            if (this.getCliente() instanceof PessoaJuridica) {
                investimento.atualizaSaldo(periodo, BigDecimal.valueOf(1.02));
            } else {
                investimento.atualizaSaldo(periodo, BigDecimal.valueOf(1.00));
            }
        }
    }

    public void investir(String nome, BigDecimal saldo) {
        if(!this.investimentos.containsKey(nome)){
            Investimento investimento = new Investimento(nome);
            this.investimentos.put(nome, investimento);
        }
        this.investimentos.get(nome).recebeSaldo(saldo);
    }
    public void investir(Investimento investimento, BigDecimal saldo){
        if(!this.investimentos.containsKey(investimento.getNome())){
            this.investimentos.put(investimento.getNome(), investimento);
        }
        this.investimentos.get(investimento.getNome()).recebeSaldo(saldo);
    }

    public void retirar(String nome, BigDecimal saldo) {
        for (Investimento investimento : investimentos.values()) {
            if (investimento.getNome().equalsIgnoreCase(nome)) {
                investimento.retiraSaldo(saldo);
            }
        }
    }

    public Map<String, Investimento> consultarInvestimentos() {

        return investimentos;
    }

}
