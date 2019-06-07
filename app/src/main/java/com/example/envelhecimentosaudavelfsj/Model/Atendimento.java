package com.example.envelhecimentosaudavelfsj.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by Raphael Rodrigues on 04/06/2019.
 */
@SuppressWarnings("ALL")
@Entity(tableName = "atendimento", foreignKeys = {@ForeignKey(
        entity = Paciente.class, parentColumns = "cpf", childColumns = "cpfPaciente")})
public class Atendimento {
    @PrimaryKey(autoGenerate = true)
    private Long atendimentoId;
    @ColumnInfo(name = "servidorID")
    private Long servidorId;
    @ColumnInfo(name = "cpfPaciente")
    private Long cpfPaciente;
    @ColumnInfo(name = "dataAtendimento")
    private Date dataEHoraAtendimento;
    @ColumnInfo(name = "peso")
    private Double peso;
    @ColumnInfo(name = "pressao")
    private String pressaoArterial;
    @ColumnInfo(name = "PApreTeste")
    private String PApreTeste;
    @ColumnInfo(name = "PAposTeste")
    private String PAposTeste;
    @ColumnInfo(name = "freqCardiaca")
    private String frequenciaCardiaca;
    @ColumnInfo(name = "distanciaTeste")
    private String distanciaTesteErg;
    @ColumnInfo(name = "VOobtido")
    private String VOobtidoTesteErg;
    @ColumnInfo(name = "oximetriaPre")
    private String OximetriaPre;
    @ColumnInfo(name = "oximetriaPos")
    private String OximetriaPos;
    @ColumnInfo(name = "dobrasResult")
    private Double dobrasCutaneas;
    @ColumnInfo(name = "imc")
    private String IMC;
    @ColumnInfo(name = "rcq")
    private Double RCQ;
    @ColumnInfo(name = "altura")
    private Double altura;

    private String pressaoSis;
    private String pressaoDias;


    public Atendimento() {
    }

    public Atendimento(Long CPF, Date hora)  //Construtor de teste , deve ser removido na versão final
    {
        this.cpfPaciente = CPF;
        this.dataEHoraAtendimento = hora;
    }


    public Atendimento(Long atendimentoId, Long cpfPaciente, Date dataEHoraAtendimento, Double peso,
                       String pressaoArterial, String PApreTeste, String PAposTeste, String frequenciaCardiaca,
                       String distanciaTesteErg, String VOobtidoTesteErg, String oximetriaPre,
                       String oximetriaPos, Double dobrasCutaneas, String IMC, Double RCQ, Double altura) {

        this.atendimentoId = atendimentoId;
        this.cpfPaciente = cpfPaciente;
        this.dataEHoraAtendimento = dataEHoraAtendimento;
        this.peso = peso;
        this.pressaoArterial = PressaoArterial();
        this.PApreTeste = PressaoArterialPreTeste();
        this.PAposTeste = PressaoArterialPosTeste();
        this.frequenciaCardiaca = frequenciaCardiaca;
        this.distanciaTesteErg = distanciaTesteErg;
        this.VOobtidoTesteErg = VOobtidoTesteErg;
        this.OximetriaPre = oximetriaPre;
        this.OximetriaPos = oximetriaPos;
        this.dobrasCutaneas = dobrasCutaneas;
        this.IMC = IMC;
        this.RCQ = RCQ;
        this.altura = altura;
    }

    public String RCQ(double circunferenciaCintura, double circunferenciaQuadril, String sexo, int idade) {
        Double restul = circunferenciaCintura / circunferenciaQuadril;
        String RCQtipo = "";

        if (sexo.equals("Masculino")) {
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

        if (sexo.equals("Feminino")) {
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

        this.RCQ = restul;
        return restul.toString() + " - " + RCQtipo;
    }

    //pressão arterial
    public String PressaoArterial() {
        return (this.getPressaoSis() + "X" + this.getPressaoDias() + "mmHg");
    }

    //pressão arterial pré teste
    private String PressaoArterialPreTeste() {
        return (this.getPressaoSis() + "X" + this.getPressaoDias() + "mmHg");
    }

    //pressão arterial pós Teste
    private String PressaoArterialPosTeste() {
        return (this.getPressaoSis() + "X" + this.getPressaoDias() + "mmHg");
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Long getAtendimentoId() {
        return atendimentoId;
    }

    public void setAtendimentoId(Long atendimentoId) {
        this.atendimentoId = atendimentoId;
    }

    public Long getServidorId() {
        return servidorId;
    }

    public void setServidorId(Long servidorId) {
        this.servidorId = servidorId;
    }

    public Long getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(Long cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    public Date getDataEHoraAtendimento() {
        return dataEHoraAtendimento;
    }

    public void setDataEHoraAtendimento(Date dataEHoraAtendimento) {
        this.dataEHoraAtendimento = dataEHoraAtendimento;
    }

    public String getPressaoSis() {
        return pressaoSis;
    }

    public void setPressaoSis(String pressaoSis) {
        this.pressaoSis = pressaoSis;
    }

    public String getPressaoDias() {
        return pressaoDias;
    }

    public void setPressaoDias(String pressaoDias) {
        this.pressaoDias = pressaoDias;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getFrequenciaCardiaca() {
        return frequenciaCardiaca;
    }

    public void setFrequenciaCardiaca(String frequenciaCardiaca) {
        this.frequenciaCardiaca = frequenciaCardiaca;
    }

    public String getDistanciaTesteErg() {
        return distanciaTesteErg;
    }

    public void setDistanciaTesteErg(String distanciaTesteErg) {
        this.distanciaTesteErg = distanciaTesteErg;
    }

    public String getVOobtidoTesteErg() {
        return VOobtidoTesteErg;
    }

    public void setVOobtidoTesteErg(String VOobtidoTesteErg) {
        this.VOobtidoTesteErg = VOobtidoTesteErg;
    }

    public String getOximetriaPre() {
        return OximetriaPre;
    }

    public void setOximetriaPre(String oximetriaPre) {
        OximetriaPre = oximetriaPre;
    }

    public String getOximetriaPos() {
        return OximetriaPos;
    }

    public void setOximetriaPos(String oximetriaPos) {
        OximetriaPos = oximetriaPos;
    }

    public String getPressaoArterial() {
        return pressaoArterial;
    }

    public void setPressaoArterial(String pressaoArterial) {
        this.pressaoArterial = pressaoArterial;
    }

    public String getPApreTeste() {
        return PApreTeste;
    }

    public void setPApreTeste(String PApreTeste) {
        this.PApreTeste = PApreTeste;
    }

    public String getPAposTeste() {
        return PAposTeste;
    }

    public void setPAposTeste(String PAposTeste) {
        this.PAposTeste = PAposTeste;
    }

    public Double getDobrasCutaneas() {
        return dobrasCutaneas;
    }

    public void setDobrasCutaneas(Double dobrasCutaneas) {
        this.dobrasCutaneas = dobrasCutaneas;
    }

    public String getIMC() {
        return IMC;
    }

    public void setIMC(String IMC) {
        this.IMC = IMC;
    }

    public Double getRCQ() {
        return RCQ;
    }

    public void setRCQ(Double RCQ) {
        this.RCQ = RCQ;
    }
}