package com.example.envelhecimentosaudavelfsj.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.envelhecimentosaudavelfsj.Model.Atendimento;

import java.util.List;

//Criado por Yan Vitor 06/06/2019

@Dao
public abstract class AtendimentoDao {

    @Insert
    public abstract Long insertAtendimento(Atendimento atendimento);

    @Query("SELECT * FROM atendimento WHERE cpfPaciente = :idPaciente")
    public abstract List<Atendimento> getByPaciente (long idPaciente);

}
