package com.example.envelhecimentosaudavelfsj.Util;

import java.util.Locale;

public class Util {

    public String IMC(Double peso, Double altura) {

        Double imc =  peso / (altura * altura);
        String situacao;

        if (imc < 18.5) {
            situacao = "Baixo Peso";
        } else if (imc < 25) {
            situacao = "Peso Adequado";
        } else if (imc < 30) {
            situacao = "Sobrepeso";
        } else {
            situacao = "Obesidade";
        }
        return String.format(new Locale("pt-BR"),"%.2f", imc) + " - " + situacao;
    }
}
