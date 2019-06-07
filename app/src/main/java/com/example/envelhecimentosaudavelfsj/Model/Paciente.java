package com.example.envelhecimentosaudavelfsj.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Raphael Rodrigues on 04/06/2019.
 */
@Entity(tableName = "paciente")
public class Paciente {
    //@Fields

    @PrimaryKey
    private Long cpf;
    @ColumnInfo(name = "servidorID")
    private Long servidorId;
    @ColumnInfo(name = "nome")
    private String nome;
    @ColumnInfo(name = "endereco")
    private Endereco endereco;
    @ColumnInfo(name = "sexo")
    private String sexo;
    @ColumnInfo(name = "nascimento")
    private Date dataNascimento;
    @ColumnInfo(name = "idade")
    private Integer idade;


    public List<Atendimento> getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(List<Atendimento> atendimento) {
        this.atendimento = atendimento;
    }

    //Lista de atendimentos
    @Ignore
    List<Atendimento> atendimento = new ArrayList<>();

    public Paciente() {
    }

    public Paciente(Long cpf, String nome, Endereco endereco, String sexo, Date dataNascimento, Integer idade, Double altura) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
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
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

}