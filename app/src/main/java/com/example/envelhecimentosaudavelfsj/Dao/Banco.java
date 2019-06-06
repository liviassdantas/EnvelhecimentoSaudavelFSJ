package com.example.envelhecimentosaudavelfsj.Dao;

import android.arch.persistence.room.Room;
import android.content.Context;

//Criado por Yan Vitor 06/06/2019

public class Banco {

    private static AtendimentoDatabase servicoDatabase;
    private static final Object LOCK = new Object();

    public synchronized static AtendimentoDatabase getAtendimentoDatabase(Context context){
        if(servicoDatabase == null) {
            synchronized (LOCK) {
                if (servicoDatabase == null) {
                    servicoDatabase = Room.databaseBuilder(context,AtendimentoDatabase.class, "atentimento BD").build();
                }
            }
        }
        return servicoDatabase;
    }

}
