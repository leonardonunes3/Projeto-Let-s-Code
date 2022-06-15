package br.letscode.ui;

public class OpcaoMenu {

    private String titulo;
    private Execucao metodoExecucao;

    // TODO?: adicionar algo opcional para facilitar o redirecionamento entre menus (talvez uma String?)

    public OpcaoMenu(String titulo, Execucao metodoExecucao) {
        this.titulo = titulo;
        this.metodoExecucao = metodoExecucao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Execucao getMetodoExecucao() {
        return metodoExecucao;
    }

    public void setMetodoExecucao(Execucao metodoExecucao) {
        this.metodoExecucao = metodoExecucao;
    }

    public void escolher(){
        this.metodoExecucao.executa();
    }
}
