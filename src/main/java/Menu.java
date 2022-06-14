import java.util.Scanner;

public class Menu {
    private String[] opcoes;
    private String titulo;

    Menu(String titulo, String[] opcoes) {
        this.titulo = titulo;
        this.opcoes = opcoes;
    }

    public void mostraMenu() {
        System.out.println("==================");
        System.out.println(titulo);
        for (int i = 1; i <= opcoes.length; i++) {
            System.out.println(i + " - " + opcoes[i - 1]);
        }
        System.out.println("==================");
    }

    public int pegaResultado() {
        Scanner scanner = new Scanner(System.in);
        int resultado = scanner.nextInt();
        scanner.close();
        return resultado;
    }
}
