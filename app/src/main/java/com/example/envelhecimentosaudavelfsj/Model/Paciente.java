package com.example.envelhecimentosaudavelfsj.Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Raphael Rodrigues on 04/06/2019.
 */
public class Paciente {

    private Long cpf;
    private Long servidorId;
    private String nome;
    private Endereco endereco;
    private String sexo;
    private Date dataNascimento;
    private Integer idade;
    private String telefone;
    private List<Atendimento> atendimentos = new ArrayList<>();

    public Paciente() {}

    public Paciente(Long cpf, String nome, Endereco endereco, String sexo, Date dataNascimento, Integer idade) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
    }

    public List<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public void setAtendimentos(Atendimento atendimento) {
        this.atendimentos.add(atendimento);
    }

    public void setAtendimentos(List<Atendimento> atendimentos) {
        this.atendimentos = atendimentos;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Long getServidorId() {
        return servidorId;
    }

    public void setServidorId(Long servidorId) {
        this.servidorId = servidorId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getIdade() {
        if (this.dataNascimento != null) {
            Calendar dataAtual = Calendar.getInstance();
            dataAtual.getTime();

            Calendar dataNascimento = Calendar.getInstance();
            dataNascimento.setTime(this.dataNascimento);

            this.idade = dataAtual.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);

            if (dataAtual.get(Calendar.MONTH) < dataNascimento.get(Calendar.MONTH))
                this.idade--;
            else if (dataAtual.get(Calendar.MONTH) == dataNascimento.get(Calendar.MONTH)
                    && dataAtual.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH))
                this.idade--;

        }
        return this.idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}