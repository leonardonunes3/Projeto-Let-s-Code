import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import br.letscode.models.*;
import br.letscode.ui.EntradaDadoMenu;
import br.letscode.ui.Menu;
import br.letscode.ui.OpcaoMenu;

public class Aplicacao {

    private final HashMap<String, Menu> MENUS = new HashMap<>(Map.ofEntries(
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
        )),
        Map.entry("Menu Home Saldo", new Menu(
            "Qual operação você deseja realizar?",
            Arrays.asList(
                new OpcaoMenu("Sacar", this::sacar),
                new OpcaoMenu("Depositar", this::depositar),
                new OpcaoMenu("Transferir", this::transferir),
                new OpcaoMenu("Sair", this::sair)
            )
        )),
        Map.entry("Menu Home Investimento", new Menu(
            "Qual operação você deseja realizar?",
            Arrays.asList(
                new OpcaoMenu("Investir", this::investir),
                new OpcaoMenu("Retirar", this::retirar),
                new OpcaoMenu("Sair", this::sair)
            )
        ))
    ));
    private Pessoa clienteAtual = null;
    private Conta contaAtual = null;
    private HashMap<Integer, Conta> contas = new HashMap<>();
    private HashMap<String, Pessoa> pessoas = new HashMap<>();
    private HashMap<String, Investimento> investimentos = new HashMap<>(Map.ofEntries(
        Map.entry("Investimento 1", new Investimento("Investimento 1")),
        Map.entry("Investimento 2", new Investimento("Investimento 2")),
        Map.entry("Investimento 3", new Investimento("Investimento 3"))
    ));

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
        this.mostraMenu("Menu Home Corrente");
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
        EntradaDadoMenu<String> entradaIdentificador = new EntradaDadoMenu<>(
            "Insira seu CPF ou CNPJ",
            "A entrada inserida não é nem um CPF válido nem um CNPJ válido",
                (s) -> {
                    PessoaFisica pf = new PessoaFisica();
                    PessoaJuridica pj = new PessoaJuridica();
                    return pf.setCpf(s) || pj.setCnpj(s);
                },
                (s) -> s
        );
        String identificador = entradaIdentificador.pedeEntrada();

        if(this.pessoas.containsKey(identificador)){
            this.clienteAtual = this.pessoas.get(identificador);
            this.mostraMenu("Menu Cria Conta");
        } else{
            System.out.println("Cliente não cadastrado no sistema.");
            this.sair();
        }
    }

    public void entraConta(){
        EntradaDadoMenu<Integer> entradaNumeroConta = new EntradaDadoMenu<>(
            "Insira o número da conta",
            "A entrada inserida não pôde ser entendida como um número",
            (s) -> {
                try{
                    Integer.parseInt(s);
                    return true;
                } catch(NumberFormatException e){
                    return false;
                }
            },
            Integer::parseInt
        );
        int numeroConta = entradaNumeroConta.pedeEntrada();

        if(this.contas.containsKey(numeroConta)){
            this.contaAtual = this.contas.get(numeroConta);
            String identificadorCliente = "";
            if(this.contaAtual.getCliente() instanceof PessoaFisica){
                identificadorCliente = ((PessoaFisica)this.contaAtual.getCliente()).getCpf();
            } else if(this.contaAtual.getCliente() instanceof PessoaJuridica){
                identificadorCliente = ((PessoaJuridica)this.contaAtual.getCliente()).getCnpj();
            } else{
                throw new IllegalStateException("Cliente cadastrado para esta conta é inválido");
            }
            if(this.pessoas.containsKey(identificadorCliente)){
                this.clienteAtual = this.pessoas.get(identificadorCliente);
                if(this.contaAtual instanceof ContaSaldo){
                    this.mostraMenu("Menu Home Saldo");
                } else if(this.contaAtual instanceof ContaInvestimento){
                    this.mostraMenu("Menu Home Investimento");
                } else{
                    throw new IllegalStateException("Tipo de conta não suportada por esta aplicação.");
                }
            } else{
                // Em tese não deveria acontecer, mas no caso de acontecer...
                System.out.println("Cliente não cadastrado no sistema");
                this.sair();
            }
        } else{
            System.out.println("Conta não cadastrada no sistema.");
            this.sair();
        }
    }

    public void sacar(){
        EntradaDadoMenu<BigDecimal> entradaValor = new EntradaDadoMenu<>(
            "Insira o valor a ser sacado",
            "A entrada inserida não pôde ser entendida como um número",
            (s) -> {
                try{
                    new BigDecimal(s);
                    return true;
                } catch(NumberFormatException e){
                    return false;
                }
            },
            BigDecimal::new
        );
        BigDecimal valor = entradaValor.pedeEntrada();

        ContaSaldo contaSaldo = (ContaSaldo) this.contaAtual;
        try{
            contaSaldo.sacar(valor);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void depositar(){
        EntradaDadoMenu<BigDecimal> entradaValor = new EntradaDadoMenu<>(
            "Insira o valor a ser depositado",
            "A entrada inserida não pôde ser entendida como um número",
            (s) -> {
                try{
                    new BigDecimal(s);
                    return true;
                } catch(NumberFormatException e){
                    return false;
                }
            },
            BigDecimal::new
        );
        BigDecimal valor = entradaValor.pedeEntrada();

        ContaSaldo contaSaldo = (ContaSaldo) this.contaAtual;
        try{
            contaSaldo.depositar(valor);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void transferir(){
        EntradaDadoMenu<Integer> entradaNumeroConta = new EntradaDadoMenu<>(
            "Insira o número da conta de destino",
            "A entrada inserida não pôde ser entendida como um número",
            (s) -> {
                try{
                    Integer.parseInt(s);
                    return true;
                } catch(NumberFormatException e){
                    return false;
                }
            },
            Integer::parseInt
        );
        int numeroConta = entradaNumeroConta.pedeEntrada();

        if(!this.contas.containsKey(numeroConta)){
            System.out.println("Conta não encontrada");
        } else{
            EntradaDadoMenu<BigDecimal> entradaValor = new EntradaDadoMenu<>(
                "Insira o valor a ser transferido",
                "A entrada inserida não pôde ser entendida como um número",
                (s) -> {
                    try{
                        new BigDecimal(s);
                        return true;
                    } catch(NumberFormatException e){
                        return false;
                    }
                },
                BigDecimal::new
            );
            BigDecimal valor = entradaValor.pedeEntrada();

            ContaSaldo contaSaldo = (ContaSaldo) this.contaAtual;
            try{
                contaSaldo.sacar(valor);
            } catch(Exception e){
                System.out.println(e.getMessage());
            }

            ContaSaldo contaDestino = (ContaSaldo) this.contas.get(numeroConta);
            try{
                contaDestino.depositar(valor);
            } catch(Exception e){
                // não deveria ser possível, mas no caso de acontecer...
                System.out.println(e.getMessage());
                throw new IllegalStateException("Erro de sistema: transferência parcialmente concluída.");
            }
        }
        this.mostraMenu("Menu Home Saldo");
    }

    public void investir(){
        EntradaDadoMenu<Integer> entradaNumeroConta = new EntradaDadoMenu<>(
            "Insira o número da conta de origem",
            "A entrada inserida não pôde ser entendida como um número",
            (s) -> {
                try{
                    Integer.parseInt(s);
                    return true;
                } catch(NumberFormatException e){
                    return false;
                }
            },
            Integer::parseInt
        );
        int numeroConta = entradaNumeroConta.pedeEntrada();

        if(this.contas.containsKey(numeroConta)){
            Conta conta = this.contas.get(numeroConta);
            if(conta instanceof ContaSaldo){
                EntradaDadoMenu<String> entradaInvestimento = new EntradaDadoMenu<>(
                    "Insira o nome do investimento escolhido",
                    (s) -> true,
                    (s) -> s
                );
                String nomeInvestimento = entradaInvestimento.pedeEntrada();

                if(this.investimentos.containsKey(nomeInvestimento)){
                    EntradaDadoMenu<BigDecimal> entradaValor = new EntradaDadoMenu<>(
                            "Insira o valor a ser investido",
                            "A entrada inserida não pôde ser entendida como um número",
                            (s) -> {
                                try{
                                    new BigDecimal(s);
                                    return true;
                                } catch(NumberFormatException e){
                                    return false;
                                }
                            },
                            BigDecimal::new
                    );
                    BigDecimal valor = entradaValor.pedeEntrada();

                    ContaSaldo contaSaldo = (ContaSaldo) conta;
                    try{
                        contaSaldo.sacar(valor);
                        this.investimentos.get(nomeInvestimento).recebeSaldo(valor);
                    } catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                } else{
                    System.out.println("Investimento não cadastrado no sistema.");
                }
            } else{
                System.out.println("Conta informada não é do tipo ContaSaldo");
            }
        } else{
            System.out.println("Conta não cadastrada no sistema.");
        }
        this.mostraMenu("Menu Home Investimento");
    }

    public void retirar(){
        EntradaDadoMenu<Integer> entradaNumeroConta = new EntradaDadoMenu<>(
                "Insira o número da conta de destino",
                "A entrada inserida não pôde ser entendida como um número",
                (s) -> {
                    try{
                        Integer.parseInt(s);
                        return true;
                    } catch(NumberFormatException e){
                        return false;
                    }
                },
                Integer::parseInt
        );
        int numeroConta = entradaNumeroConta.pedeEntrada();

        if(this.contas.containsKey(numeroConta)){
            Conta conta = this.contas.get(numeroConta);
            if(conta instanceof ContaSaldo){
                EntradaDadoMenu<String> entradaInvestimento = new EntradaDadoMenu<>(
                        "Insira o nome do investimento escolhido",
                        (s) -> true,
                        (s) -> s
                );
                String nomeInvestimento = entradaInvestimento.pedeEntrada();

                if(this.investimentos.containsKey(nomeInvestimento)){
                    EntradaDadoMenu<BigDecimal> entradaValor = new EntradaDadoMenu<>(
                            "Insira o valor a ser retirado",
                            "A entrada inserida não pôde ser entendida como um número",
                            (s) -> {
                                try{
                                    new BigDecimal(s);
                                    return true;
                                } catch(NumberFormatException e){
                                    return false;
                                }
                            },
                            BigDecimal::new
                    );
                    BigDecimal valor = entradaValor.pedeEntrada();

                    ContaSaldo contaSaldo = (ContaSaldo) conta;
                    try{
                        contaSaldo.depositar(valor);
                        this.investimentos.get(nomeInvestimento).retiraSaldo(valor);
                    } catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                } else{
                    System.out.println("Investimento não cadastrado no sistema.");
                }
            } else{
                System.out.println("Conta informada não é do tipo ContaSaldo");
            }
        } else{
            System.out.println("Conta não cadastrada no sistema.");
        }
        this.mostraMenu("Menu Home Investimento");
    }

    public void sair(){
        this.clienteAtual = null;
        this.contaAtual = null;
        this.mostraMenu("Menu Login");
    }

    public static void main(String[] args) {
        Aplicacao aplicacao = new Aplicacao();
        aplicacao.mostraMenu("Menu Login");
    }

}
