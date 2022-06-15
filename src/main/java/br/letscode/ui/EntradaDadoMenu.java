package br.letscode.ui;

import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

public class EntradaDadoMenu<T> {

    public static final String TEXTO_ERRO_PADRAO = "Entrada inválida, tente novamente.";

    private String texto;
    private String textoErro;
    private Predicate<String> metodoTeste;
    private Function<String, T> metodoRetorno;

    public EntradaDadoMenu(String texto, String textoErro, Predicate<String> metodoTeste, Function<String, T> metodoExecucao) {
        this.texto = texto;
        this.textoErro = textoErro;
        this.metodoTeste = metodoTeste;
        this.metodoRetorno = metodoExecucao;
    }

    public EntradaDadoMenu(String texto, Predicate<String> metodoTeste, Function<String, T> metodoRetorno) {
        this(texto, TEXTO_ERRO_PADRAO, metodoTeste, metodoRetorno);
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTextoErro() {
        return textoErro;
    }

    public void setTextoErro(String textoErro) {
        this.textoErro = textoErro;
    }

    public Predicate<String> getMetodoTeste() {
        return metodoTeste;
    }

    public void setMetodoTeste(Predicate<String> metodoTeste) {
        this.metodoTeste = metodoTeste;
    }

    public Function<String, T> getMetodoRetorno() {
        return metodoRetorno;
    }

    public void setMetodoRetorno(Function<String, T> metodoRetorno) {
        this.metodoRetorno = metodoRetorno;
    }

    public T pedeEntrada(){
        System.out.println(this.texto);
        Scanner scan = new Scanner(System.in);
        String entrada = "";
        boolean entradaValida = false;
        while(!entradaValida){
            entrada = scan.nextLine();
            entradaValida = this.metodoTeste.test(entrada);
            if(!entradaValida){
                System.out.println(this.textoErro);
            }
        }
        return this.metodoRetorno.apply(entrada);
    }
}
