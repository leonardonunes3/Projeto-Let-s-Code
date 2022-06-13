package br.letscode.models;

public class PessoaFisica extends Pessoa {

    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public boolean setCpf(String cpf) { //TODO: Validacao de CPF
        this.cpf = cpf;
        return true;
    }
}
