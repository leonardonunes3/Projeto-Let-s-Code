package br.letscode.models;

import java.math.BigDecimal;
import java.util.Random;

public class Investimento {

    private static final double MEDIA_RENDIMENTO_CHAO_ABERTO_PADRAO = 0.001;
    private static final double MEDIA_RENDIMENTO_TETO_FECHADO_PADRAO = 0.101;
    private static final double DESVIO_PADRAO_RENDIMENTO_CHAO_ABERTO_PADRAO = 0;
    private static final double DESVIO_PADRAO_RENDIMENTO_TETO_FECHADO_PADRAO = 0.15;

    private String nome;
    private BigDecimal saldo;
    private final double MEDIA_RENDIMENTO;
    private final double DESVIO_PADRAO_RENDIMENTO;

    public Investimento(String nome) {
        this.nome = nome;
        Random random = new Random();
        this.MEDIA_RENDIMENTO = (
                MEDIA_RENDIMENTO_TETO_FECHADO_PADRAO -
                random.nextDouble() * (
                        MEDIA_RENDIMENTO_TETO_FECHADO_PADRAO -
                        MEDIA_RENDIMENTO_CHAO_ABERTO_PADRAO
                )
        );
        this.DESVIO_PADRAO_RENDIMENTO = (
                DESVIO_PADRAO_RENDIMENTO_TETO_FECHADO_PADRAO -
                random.nextDouble() * (
                    DESVIO_PADRAO_RENDIMENTO_TETO_FECHADO_PADRAO -
                    DESVIO_PADRAO_RENDIMENTO_CHAO_ABERTO_PADRAO
                )
        );
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void atualizaSaldo(){
        this.saldo = this.saldo.multiply(BigDecimal.ONE.add(BigDecimal.valueOf(
                new Random().nextGaussian()*this.DESVIO_PADRAO_RENDIMENTO + this.MEDIA_RENDIMENTO
        )));
    }

    public void recebeSaldo(BigDecimal valor){
        this.saldo = this.saldo.add(valor);
    }

    public void retiraSaldo(BigDecimal valor){
        this.saldo = this.saldo.subtract(valor);
    }

}
