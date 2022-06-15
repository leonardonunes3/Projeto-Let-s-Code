import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Scanner;

import br.letscode.models.*;

public class Aplicacao {
    private static HashSet<Conta> contas = new HashSet<>();
    private static HashSet<Pessoa> pessoas = new HashSet<>();

    private static Pessoa criaPessoa() {
        Menu menuPessoa = new Menu("Escolha a opção mais adequada à conta que você deseja criar: ",
                new String[] { "Pessoa Física", "Pessoa Jurídica", "Voltar" });
        menuPessoa.mostraMenu();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Digite o nome: ");
            String nome = scanner.nextLine();
            System.out.println("Digite o telefone: ");
            String telefone = scanner.nextLine();
            System.out.println("Digite o e-mail: ");
            String email = scanner.nextLine();
            switch (menuPessoa.pegaResultado()) {
                case 1:
                    System.out.println("Digite o CPF: ");
                    String cpf = scanner.nextLine();
                    PessoaFisica pessoaFisica = new PessoaFisica();
                    pessoaFisica.setNome(nome);
                    pessoaFisica.setTelefone(telefone);
                    pessoaFisica.setEmail(email);
                    pessoaFisica.setCpf(cpf);
                    return pessoaFisica;
                case 2:
                    System.out.println("Digite o CNPJ: ");
                    String cnpj = scanner.nextLine();
                    PessoaJuridica pessoaJuridica = new PessoaJuridica();
                    pessoaJuridica.setNome(nome);
                    pessoaJuridica.setTelefone(telefone);
                    pessoaJuridica.setEmail(email);
                    pessoaJuridica.setCnpj(cnpj);
                    return pessoaJuridica;
                case 3:
                    main(null);
                default:
                    menuPessoa.mostraMenu();
            }
            scanner.close();
        }
    }

    private static void menuHome(Conta conta) {
        Menu menuHome = new Menu("Escolha uma das opções: ",
                new String[] { "Sacar", "Depositar", "Transferir", "Sair" });
        menuHome.mostraMenu();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o valor desejado: ");
        BigDecimal valor = scanner.nextBigDecimal();
        switch (menuHome.pegaResultado()) {
            case 1:
                // conta.sacar(valor);
            case 2:
                // conta.depositar(valor);
            case 3:
                // System.out.println("Qual o número da conta para a qual você deseja
                // transferir? ");
                // conta.transferir();
            case 4:
                main(null);
            default:
                main(null);
        }
        scanner.close();
    }

    private static Pessoa verificaSePessoaExiste() {
        Menu menuVerificacaoPessoa = new Menu("Você já tem cadastro no nosso banco?", new String[] { "Sim", "Não" });
        switch (menuVerificacaoPessoa.pegaResultado()) {
            case 1:
                Menu menuPessoa = new Menu("Qual o seu CPF/CNPJ? ", new String[] { "Sair" });
                int entradaUsuario = menuPessoa.pegaResultado();
                for (Pessoa pessoa : pessoas) {
                    if (pessoa instanceof PessoaFisica) {
                        if (((PessoaFisica) pessoa).getCpf() == String.valueOf(entradaUsuario)) {
                            return pessoa;
                        }
                    } else {
                        if (((PessoaJuridica) pessoa).getCnpj() == String.valueOf(entradaUsuario)) {
                            return pessoa;
                        }
                    }
                }
            case 2:
                return criaPessoa();
            default:
                verificaSePessoaExiste();
        }
        return null;
    }

    private static Conta criaConta() {
        Pessoa pessoa = verificaSePessoaExiste();
        Menu menuTipoConta = new Menu("Escolha uma opção de conta: ",
                new String[] { "Criar conta corrente", "Criar conta poupança", "Criar conta investimento" });
        menuTipoConta.mostraMenu();
        while (true) {
            switch (menuTipoConta.pegaResultado()) {
                case 1:
                    // return new ContaCorrente();
                case 2:
                    // return new ContaPoupanca();
                case 3:
                    // return new ContaInvestimento();
                default:
                    menuTipoConta.mostraMenu();
            }
        }
    }

    private static void menuLogin() {
        boolean contaEncontrada = false;
        while (!contaEncontrada) {
            Menu menuLogin = new Menu("Digite o número da conta:", new String[] { "Sair" });
            menuLogin.mostraMenu();
            int entradaUsuario = menuLogin.pegaResultado();
            switch (entradaUsuario) {
                case 1:
                    main(null);

                default:
                    for (Conta conta : contas) {
                        if (conta.getNumero() == entradaUsuario) {
                            contaEncontrada = true;
                            menuHome(conta);
                        }
                    }
            }
        }

    }

    public static void main(String[] args) {
        Menu menuInicial = new Menu("Escolha uma opção: ", new String[] { "Entrar em uma conta", "Criar Conta" });
        menuInicial.mostraMenu();
        switch (menuInicial.pegaResultado()) {
            case 1:
                menuLogin();
            case 2:
                criaConta();
        }
    }

}
