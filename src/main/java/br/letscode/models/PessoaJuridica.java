package br.letscode.models;

public class PessoaJuridica extends Pessoa {

    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public boolean setCnpj(String cnpj) { //TODO: Validacao de CNPJ
        this.cnpj = cnpj;

        return true;
    }
}
