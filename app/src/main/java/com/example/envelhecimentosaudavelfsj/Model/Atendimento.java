package com.example.envelhecimentosaudavelfsj.Model;
import java.util.Date;

/**
 * Created by Raphael Rodrigues on 04/06/2019.
 */
public class Atendimento
{
    private Long atendimentoId;
    private Long servidorId;
    private String cpfPaciente;
    private Date dataEHoraAtendimento;
    private Double peso;
    private String frequenciaCardiaca;
    private String pressaoArterial;
    private String distanciaTesteErg;
    private String VOobtidoTesteErg;
    private String PAposTeste;
    private String OximetriaPre;
    private String OximetriaPos;
    private Double tricipial;
    private Double suprailica;
    private Double coxa;
    private Double abdomen;
    private Double peitoral;
    private Double IMC;
    private Double RCQ;

    public Atendimento() {
    }

    public Double IMC(Double peso, Double altura) {
        return peso / (altura * altura);
    }

    //TODO método RCQ

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

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) {
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

    public String getPressaoArterial() {
        return pressaoArterial;
    }

    public void setPressaoArterial(String pressaoArterial) {
        this.pressaoArterial = pressaoArterial;
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

    public String getPAposTeste() {
        return PAposTeste;
    }

    public void setPAposTeste(String PAposTeste) {
        this.PAposTeste = PAposTeste;
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

    public Double getTricipial() {
        return tricipial;
    }

    public void setTricipial(Double tricipial) {
        this.tricipial = tricipial;
    }

    public Double getSuprailica() {
        return suprailica;
    }

    public void setSuprailica(Double suprailica) {
        this.suprailica = suprailica;
    }

    public Double getCoxa() {
        return coxa;
    }

    public void setCoxa(Double coxa) {
        this.coxa = coxa;
    }

    public Double getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(Double abdomen) {
        this.abdomen = abdomen;
    }

    public Double getPeitoral() {
        return peitoral;
    }

    public void setPeitoral(Double peitoral) {
        this.peitoral = peitoral;
    }

    public Double getIMC() {
        return IMC;
    }

    public void setIMC(Double IMC) {
        this.IMC = IMC;
    }

    public Double getRCQ() {
        return RCQ;
    }

    public void setRCQ(Double RCQ) {
        this.RCQ = RCQ;
    }
}