package br.letscode.ui;

import java.util.List;

// TODO?: fazer br.letscode.ui.Menu e br.letscode.ui.OpcaoMenu implementarem a mesma interface e usar uma lista dessa interface, para permitir submenus mais facilmente
public class Menu {

    private final EntradaDadoMenu<Integer> ESCOLHA_OPCAO = new EntradaDadoMenu<>(
        "",
            "",
            (s) -> {
                try{
                    int resultado = Integer.parseInt(s);
                    if(resultado < 1 || resultado > this.opcoes.size()){
                        throw new IllegalArgumentException();
                    }
                    return true;
                } catch(NumberFormatException e){
                    this.ESCOLHA_OPCAO.setTextoErro("A entrada não pôde ser entendida como um número, tente novamente.");
                } catch(IllegalArgumentException e){
                    this.ESCOLHA_OPCAO.setTextoErro("O número inserido não está na lista, tente novamente.");
                }
                return false;
            },
            (s) -> Integer.parseInt(s) - 1
    );
    private String titulo;
    private List<OpcaoMenu> opcoes;

    public Menu(String titulo, List<OpcaoMenu> opcoes) {
        this.titulo = titulo;
        this.opcoes = opcoes;

        StringBuilder textoBuilder = new StringBuilder();
        textoBuilder.append("==================").append('\n');
        textoBuilder.append(this.titulo).append('\n');
        for (int i = 1; i <= opcoes.size(); i++) {
            textoBuilder.append(i);
            textoBuilder.append(" - ");
            textoBuilder.append(opcoes.get(i - 1).getTitulo());
            textoBuilder.append('\n');
        }
        textoBuilder.append("==================");
        this.ESCOLHA_OPCAO.setTexto(textoBuilder.toString());
    }

    public void mostraMenu() {
        this.opcoes.get(this.ESCOLHA_OPCAO.pedeEntrada()).escolher();
    }
}
