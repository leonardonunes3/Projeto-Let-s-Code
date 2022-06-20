import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import br.letscode.models.*;
import br.letscode.ui.EntradaDadoMenu;
import br.letscode.ui.Menu;
import br.letscode.ui.OpcaoMenu;

public class Aplicacao {

    private final HashMap<String, Menu> MENUS = new HashMap<>(Map.ofEntries(
        Map.entry("Menu Principal", new Menu(
            "Menu Principal",
            Arrays.asList()
        )),
        Map.entry("Menu Login", new Menu(
            "Login",
            Arrays.asList(
                new OpcaoMenu("Entrar", this::entraConta),
                new OpcaoMenu("Criar Conta", () -> this.mostraMenu("Menu Acesso Pessoa"))
            )
        )),
        Map.entry("Menu Acesso Pessoa", new Menu(
            "Já possui cadastro?",
            Arrays.asList(
                    new OpcaoMenu("Sim", this::acessaCadastro),
                    new OpcaoMenu("Não", () -> this.mostraMenu("Menu Cria Pessoa")),
                    new OpcaoMenu("Voltar", () -> this.mostraMenu("Menu Login"))
            )
        )),
        Map.entry("Menu Cria Pessoa", new Menu(
            "Escolha a opção mais adequada à conta que você deseja criar: ",
            Arrays.asList(
                new OpcaoMenu("Pessoa Física", this::criaPessoaFisica),
                new OpcaoMenu("Pessoa Jurídica", this::criaPessoaJuridica),
                new OpcaoMenu("Voltar", () -> this.mostraMenu("Menu Acesso Pessoa"))// como MENUS ainda não terminou de ser criado, não se pode usar Method Reference aqui
            )
        )),
        Map.entry("Menu Cria Conta", new Menu(
            "Que tipo de conta você deseja criar?",
            Arrays.asList(
                new OpcaoMenu("Conta Corrente", this::criaContaCorrente),
                new OpcaoMenu("Conta Investimento", this::criaContaInvestimento),
                new OpcaoMenu("Conta Poupança", this::criaContaPoupanca)
            ).subList(0, this.clienteAtual instanceof PessoaJuridica ? 2 : 3)
        ))
    ));
    private Pessoa clienteAtual = null;
    private Conta contaAtual = null;
    private HashSet<Conta> contas = new HashSet<>();
    private HashSet<Pessoa> pessoas = new HashSet<>();

    public void mostraMenu(String nome){
        this.MENUS.get(nome).mostraMenu();
    }

    public void setaAtributosComunsPessoa(Pessoa pessoa){
        EntradaDadoMenu<String> entradaNome = new EntradaDadoMenu<>(
        "Digite seu nome: ",
            (s) -> true,
            (s) -> s
        );
        pessoa.setNome(entradaNome.pedeEntrada());

        EntradaDadoMenu<String> entradaTelefone = new EntradaDadoMenu<>(
            "Digite seu telefone: ",
            (s) -> true,
            (s) -> s
        );
        pessoa.setTelefone(entradaTelefone.pedeEntrada());

        EntradaDadoMenu<String> entradaEmail = new EntradaDadoMenu<>(
            "Digite seu e-mail: ",
            (s) -> true,
            (s) -> s
        );
        pessoa.setEmail(entradaEmail.pedeEntrada());
    }
    public void criaPessoaFisica(){
        this.clienteAtual = new PessoaFisica();
        this.setaAtributosComunsPessoa(this.clienteAtual);

        boolean flag = false;
        while(!flag) {
            EntradaDadoMenu<String> entradaCpf = new EntradaDadoMenu<>(
                    "Digite o CPF: ",
                    (s) -> true,
                    (s) -> s
            );
            flag = ((PessoaFisica)this.clienteAtual).setCpf(entradaCpf.pedeEntrada());
        }
        this.mostraMenu("Menu Cria Conta");
    }
    public void criaPessoaJuridica(){
        this.clienteAtual = new PessoaJuridica();
        this.setaAtributosComunsPessoa(this.clienteAtual);

        boolean flag = false;
        while(!flag) {
            EntradaDadoMenu<String> entradaCnpj = new EntradaDadoMenu<>(
                    "Digite o CNPJ: ",
                    (s) -> true,
                    (s) -> s
            );
            flag = ((PessoaJuridica) this.clienteAtual).setCnpj(entradaCnpj.pedeEntrada());
        }
        this.mostraMenu("Menu Cria Conta");
    }

    public void menuHome(Conta conta) {
        // TODO: passar a lógica comentada para a nova estrutura
//        br.letscode.ui.Menu menuHome = new br.letscode.ui.Menu("Escolha uma das opções: ",
//                new String[] { "Sacar", "Depositar", "Transferir", "Sair" });
//        menuHome.mostraMenu();
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Digite o valor desejado: ");
//        BigDecimal valor = scanner.nextBigDecimal();
//        switch (menuHome.pegaResultado()) {
//            case 1:
//                // conta.sacar(valor);
//            case 2:
//                // conta.depositar(valor);
//            case 3:
//                // System.out.println("Qual o número da conta para a qual você deseja
//                // transferir? ");
//                // conta.transferir();
//            case 4:
//                main(null);
//            default:
//                main(null);
//        }
//        scanner.close();
    }


    public void criaContaCorrente(){
        EntradaDadoMenu<String> entradaNumero = new EntradaDadoMenu<>(
                "Digite o numero da conta: ",
                (s) -> true,
                (s) -> s
        );
        this.contaAtual = new ContaCorrente(Integer.parseInt(entradaNumero.pedeEntrada()), clienteAtual);

        System.out.println("Criando conta corrente");
    }

    public void criaContaInvestimento(){
        EntradaDadoMenu<String> entradaNumero = new EntradaDadoMenu<>(
                "Digite o numero da conta: ",
                (s) -> true,
                (s) -> s
        );
        this.contaAtual = new ContaInvestimento(Integer.parseInt(entradaNumero.pedeEntrada()), clienteAtual);

        System.out.println("Criando conta investimento");
    }

    public void criaContaPoupanca(){
        EntradaDadoMenu<String> entradaNumero = new EntradaDadoMenu<>(
                "Digite o numero da conta: ",
                (s) -> true,
                (s) -> s
        );
        this.contaAtual = new ContaPoupanca(Integer.parseInt(entradaNumero.pedeEntrada()), clienteAtual);

        System.out.println("Criando conta poupança");
    }

    public void acessaCadastro(){
        // TODO: implementar menu de acesso ao cadastro de pessoa, que pede um cpf ou cnpj e redireciona para a criação de conta
        System.out.println("Acessando um cadastro existente");
        // Menu menuVerificacaoPessoa = new Menu("Você já tem cadastro no nosso banco?", new String[] { "Sim", "Não" });
        // switch (menuVerificacaoPessoa.pegaResultado()) {
        //     case 1:
        //         Menu menuPessoa = new Menu("Qual o seu CPF/CNPJ? ", new String[] { "Sair" });
        //         int entradaUsuario = menuPessoa.pegaResultado();
        //         for (Pessoa pessoa : pessoas) {
        //             if (pessoa instanceof PessoaFisica) {
        //                 if (((PessoaFisica) pessoa).getCpf() == String.valueOf(entradaUsuario)) {
        //                     return pessoa;
        //                 }
        //             } else {
        //                 if (((PessoaJuridica) pessoa).getCnpj() == String.valueOf(entradaUsuario)) {
        //                     return pessoa;
        //                 }
        //             }
        //         }
        //     case 2:
        //         return criaPessoa();
        //     default:
        //         verificaSePessoaExiste();
        // }
        // return null;
        this.mostraMenu("Menu Cria Conta");
    }

    public void entraConta(){
        // TODO: implementar menu de login em conta, que pede o número da conta e redireciona para o menu principal
        System.out.println("Entrando em uma conta existente");
    }

    public static void main(String[] args) {
        Aplicacao aplicacao = new Aplicacao();
        aplicacao.mostraMenu("Menu Login");
    }

}
