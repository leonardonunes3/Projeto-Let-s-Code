package br.letscode.models;

public class PessoaFisica extends Pessoa {

    private String cpf;

    public String getCpf() {

        return cpf;
    }

    public boolean setCpf(String cpf) {
        int digitoVerificador1, digitoVerificador2, soma = 0, restoDivisao;

        cpf = cpf.trim().replace(".", "").replace("-", "");

        if ( cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
         || cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
         || cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888")
         || cpf.equals("99999999999") || ( cpf.length() != 11 ) ) {

            return false;
        }

        digitoVerificador1 = (int) (cpf.charAt(9) - 48);
        digitoVerificador2 = (int) (cpf.charAt(10) - 48);

        for (int i = 0; i < 9; i++) {
            int numeroAuxiliar = (int) (cpf.charAt(i) - 48);
            soma += (numeroAuxiliar * ( 10-i ) );
        }

        restoDivisao = 11 - (soma % 11);
        if ( restoDivisao == 10 || restoDivisao == 11 ) {
            restoDivisao = 0;
        }

        if ( digitoVerificador1 != restoDivisao ) {
            return false;
        }

        soma = 0;

        for (int i = 0; i < 10; i++) {
            int numeroAuxiliar = (int) (cpf.charAt(i) - 48);
            soma += (numeroAuxiliar * ( 11-i ) );
        }

        restoDivisao = 11 - (soma % 11);
        if ( restoDivisao == 10 || restoDivisao == 11 ) {
            restoDivisao = 0;
        }

        if ( digitoVerificador2 != restoDivisao ) {
            return false;
        }


        this.cpf = cpf;
        return true;
    }
}
