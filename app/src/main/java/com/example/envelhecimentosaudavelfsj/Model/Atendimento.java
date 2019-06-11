package com.example.envelhecimentosaudavelfsj.Model;


import java.util.Date;

/**
 * Created by Raphael Rodrigues on 04/06/2019.
 */
@SuppressWarnings("ALL")
public class Atendimento {

    private Long atendimentoId;
    private Long servidorId;
    private Long cpfPaciente;
    private Date dataEHoraAtendimento;
    private Double peso;
    private Double altura;
    private String pressaoArterial;
    private String PApreTeste;
    private String PAposTeste;
    private String frequenciaCardiaca;
    private String distanciaTesteErg;
    private String VOobtidoTesteErg;
    private String OximetriaPre;
    private String OximetriaPos;
    private Double dobrasCutaneas;
    private String IMC;
    private String RCQ;

    public Atendimento() {}

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

    public void setPressaoArterial(String pressaoSis, String pressaoDias) {
        this.pressaoArterial = pressaoSis + "X" + pressaoDias + " mmHg";
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

    public void setPApreTeste(String pressaoSis, String pressaoDias) {
        this.PApreTeste = pressaoSis + "X" + pressaoDias + " mmHg";
    }

    public String getPAposTeste() {
        return PAposTeste;
    }

    public void setPAposTeste(String pressaoSis, String pressaoDias) {
        this.PAposTeste = pressaoSis + "X" + pressaoDias + " mmHg";
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

    public String getRCQ() {
        return RCQ;
    }

    public void setRCQ(String RCQ) {
        this.RCQ = RCQ;
    }
}