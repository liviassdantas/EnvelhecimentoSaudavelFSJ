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
    private String RCQ;
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
                       String oximetriaPos, Double dobrasCutaneas, String IMC, String RCQ, Double altura) {

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

    //pressão arterial
    public String PressaoArterial() {
        return (this.getPressaoSis() + "X" + this.getPressaoDias() + "mmHg");
    }

    //pressão arterial pré teste
    public String PressaoArterialPreTeste() {
        return (this.getPressaoSis() + "X" + this.getPressaoDias() + "mmHg");
    }

    //pressão arterial pós Teste
    public String PressaoArterialPosTeste() {
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

    public String getRCQ() {
        return RCQ;
    }

    public void setRCQ(String RCQ) {
        this.RCQ = RCQ;
    }
}