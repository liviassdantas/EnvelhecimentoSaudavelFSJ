package com.example.envelhecimentosaudavelfsj.Dao;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.example.envelhecimentosaudavelfsj.Model.Atendimento;
import com.example.envelhecimentosaudavelfsj.Model.Paciente;

import java.util.List;

public class PacienteAtendimento {
    @Embedded
    private Paciente paciente;

    @Relation(parentColumn = "cpf", entityColumn = "cpfPaciente")
    public List<Atendimento> atendimento;

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<Atendimento> getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(List<Atendimento> atendimento) {
        this.atendimento = atendimento;
    }
}
