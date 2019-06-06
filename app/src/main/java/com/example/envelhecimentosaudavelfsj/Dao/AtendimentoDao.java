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
public interface AtendimentoDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    Long Inserir(Atendimento atendimento);

    @Update
    int Atualizar(Atendimento atendimento);

    @Delete
    int Deletar(Atendimento atendimento);

    @Query("SELECT * FROM Atendimento")
    List<Atendimento> getAllAtendimentos();

    @Query("SELECT * FROM Atendimento WHERE cpfPaciente = :CPF")
    List<Atendimento> getAtendimentosByCpf(String CPF);

}
