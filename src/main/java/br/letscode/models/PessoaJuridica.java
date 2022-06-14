package br.letscode.models;

public class PessoaJuridica extends Pessoa {

    private String cnpj;

    public String getCnpj() {

        return cnpj;
    }

    public boolean setCnpj(String cnpj) {

        int digitoVerificador1, digitoVerificador2, soma = 0, restoDivisao;
        cnpj = cnpj.trim().replace(".", "").replace("-", "").replace("/", "");

        if ( cnpj.equals("00000000000000") || cnpj.equals("11111111111111") || cnpj.equals("22222222222222")
          || cnpj.equals("33333333333333") || cnpj.equals("44444444444444") || cnpj.equals("55555555555555")
          || cnpj.equals("66666666666666") || cnpj.equals("77777777777777") || cnpj.equals("88888888888888")
          || cnpj.equals("99999999999999") || ( cnpj.length() != 14 ) ) {

            return false;
        }

        digitoVerificador1 = (int) (cnpj.charAt(12) - 48);
        digitoVerificador2 = (int) (cnpj.charAt(13) - 48);
        int peso = 2;

        for (int i = 11; i >= 0; i--) {
            int numeroAuxiliar = (int) (cnpj.charAt(i) - 48);
            soma += (numeroAuxiliar * ( peso ) );
            peso++;
            if ( peso >= 10 ) {
                peso = 2;
            }
        }

        restoDivisao = 11 - (soma % 11);

        if ( restoDivisao == 10 || restoDivisao == 11 ) {
            restoDivisao = 0;
        }

        if ( digitoVerificador1 != restoDivisao ) {
            return false;
        }

        soma = 0;
        peso = 2;

        for (int i = 12; i >= 0; i--) {
            int numeroAuxiliar = (int) (cnpj.charAt(i) - 48);
            soma += (numeroAuxiliar * ( peso ) );
            peso++;
            if ( peso >= 10 ) {
                peso = 2;
            }
        }

        restoDivisao = 11 - (soma % 11);
        if ( restoDivisao == 10 || restoDivisao == 11 ) {
            restoDivisao = 0;
        }

        if ( digitoVerificador2 != restoDivisao ) {
            return false;
        }

        this.cnpj = cnpj;

        return true;
    }
}
