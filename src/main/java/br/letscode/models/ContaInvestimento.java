package br.letscode.models;

import java.math.BigDecimal;
import java.time.Period;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ContaInvestimento extends Conta {

    public ContaInvestimento(int numero, Pessoa cliente) {
        super(numero, cliente);
        investimentos = new HashSet<>();
    }

    private Set<Investimento> investimentos;

    @Override
    public void processaRendimentos(Period periodo) {
        Iterator<Investimento> it = investimentos.iterator();
        while(it.hasNext()) {
            Investimento investimento = (Investimento)it.next();
            if (this.getCliente() instanceof PessoaJuridica) {
                investimento.atualizaSaldo(periodo, BigDecimal.valueOf(1.02));
            } else {
                investimento.atualizaSaldo(periodo, BigDecimal.valueOf(1.00));
            }
        }
    }

    public void investir(String nome, BigDecimal saldo) {
        Investimento investimento = new Investimento(nome);
        investimento.recebeSaldo(saldo);
        investimentos.add(investimento);
    }

    public void retirar(String nome, BigDecimal saldo) {
        Iterator<Investimento> it = investimentos.iterator();
        while(it.hasNext()) {
            Investimento investimento = (Investimento)it.next();
            if(investimento.getNome().equalsIgnoreCase(nome)) {
                investimento.retiraSaldo(saldo);
            }
        }
    }

    public Set<Investimento> consultarInvestimentos() {

        return investimentos;
    }

}
