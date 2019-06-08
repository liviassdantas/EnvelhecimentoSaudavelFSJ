package com.example.envelhecimentosaudavelfsj.Util;

import java.util.Locale;

public class Util {

    public String IMC(Double peso, Double altura) {

        Double imc = peso / (altura * altura);
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
        return String.format(new Locale("pt-BR"), "%.2f", imc) + " - " + situacao;
    }

    public String RCQ(double circunferenciaCintura, double circunferenciaQuadril, String sexo, int idade) {
        Double restul = circunferenciaCintura / circunferenciaQuadril;
        String RCQtipo = "";

        if (sexo.toLowerCase().trim().equals("masculino")) {
            if (idade <= 29) {
                if (restul < 0.83) {
                    RCQtipo = "Baixo";
                } else if (restul >= 0.83 && restul <= 0.88) {
                    RCQtipo = "Moderado";
                } else if (restul >= 0.89 && restul <= 0.94) {
                    RCQtipo = "Alto";
                } else if (restul > 0.94) {
                    RCQtipo = "Muito alto";
                }

            } else if (idade <= 39) {
                if (restul < 0.84) {
                    RCQtipo = "Baixo";
                } else if (restul >= 0.84 && restul <= 0.91) {
                    RCQtipo = "Moderado";
                } else if (restul >= 0.92 && restul <= 0.96) {
                    RCQtipo = "Alto";
                } else if (restul > 0.96) {
                    RCQtipo = "Muito alto";
                }
            } else if (idade <= 49) {
                if (restul < 0.88) {
                    RCQtipo = "Baixo";
                } else if (restul >= 0.88 && restul <= 0.95) {
                    RCQtipo = "Moderado";
                } else if (restul >= 0.96 && restul <= 1) {
                    RCQtipo = "Alto";
                } else if (restul > 1) {
                    RCQtipo = "Muito alto";
                }
            } else if (idade <= 59) {
                if (restul < 0.90) {
                    RCQtipo = "Baixo";
                } else if (restul >= 0.90 && restul <= 0.96) {
                    RCQtipo = "Moderado";
                } else if (restul >= 0.97 && restul <= 1.02) {
                    RCQtipo = "Alto";
                } else if (restul > 1.02) {
                    RCQtipo = "Muito alto";
                }
            } else {
                if (restul < 0.91) {
                    RCQtipo = "Baixo";
                } else if (restul >= 0.91 && restul <= 0.98) {
                    RCQtipo = "Moderado";
                } else if (restul >= 0.99 && restul <= 1.03) {
                    RCQtipo = "Alto";
                } else if (restul > 1.03) {
                    RCQtipo = "Muito alto";
                }
            }
        }

        if (sexo.toLowerCase().trim().equals("feminino")) {
            if (idade <= 29) {
                if (restul < 0.71) {
                    RCQtipo = "Baixo";
                } else if (restul >= 0.71 && restul <= 0.77) {
                    RCQtipo = "Moderado";
                } else if (restul >= 0.78 && restul <= 0.82) {
                    RCQtipo = "Alto";
                } else if (restul > 0.82) {
                    RCQtipo = "Muito alto";
                }

            } else if (idade <= 39) {
                if (restul < 0.72) {
                    RCQtipo = "Baixo";
                } else if (restul >= 0.72 && restul <= 0.78) {
                    RCQtipo = "Moderado";
                } else if (restul >= 0.79 && restul <= 0.84) {
                    RCQtipo = "Alto";
                } else if (restul > 0.84) {
                    RCQtipo = "Muito alto";
                }
            } else if (idade <= 49) {
                if (restul < 0.73) {
                    RCQtipo = "Baixo";
                } else if (restul >= 0.73 && restul <= 0.79) {
                    RCQtipo = "Moderado";
                } else if (restul >= 0.80 && restul <= 0.87) {
                    RCQtipo = "Alto";
                } else if (restul > 0.87) {
                    RCQtipo = "Muito alto";
                }
            } else if (idade <= 59) {
                if (restul < 0.74) {
                    RCQtipo = "Baixo";
                } else if (restul >= 0.74 && restul <= 0.81) {
                    RCQtipo = "Moderado";
                } else if (restul >= 0.82 && restul <= 0.88) {
                    RCQtipo = "Alto";
                } else if (restul > 0.88) {
                    RCQtipo = "Muito alto";
                }
            } else {
                if (restul < 0.76) {
                    RCQtipo = "Baixo";
                } else if (restul >= 0.76 && restul <= 0.83) {
                    RCQtipo = "Moderado";
                } else if (restul >= 0.84 && restul <= 0.90) {
                    RCQtipo = "Alto";
                } else if (restul > 0.90) {
                    RCQtipo = "Muito alto";
                }
            }
        }

        return String.format(new Locale("pt-BR"), "%.2f", restul) + " - " + RCQtipo;
    }
}
