package com.example.envelhecimentosaudavelfsj.Dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.example.envelhecimentosaudavelfsj.Model.Atendimento;
import com.example.envelhecimentosaudavelfsj.Util.Conversor;

//Criado por Yan Vitor 06/06/2019

@TypeConverters(Conversor.class)
@Database(entities = {Atendimento.class}, version = 1)
public abstract class AtendimentoDatabase extends RoomDatabase {

    public abstract AtendimentoDao servicoDatabase();

}
