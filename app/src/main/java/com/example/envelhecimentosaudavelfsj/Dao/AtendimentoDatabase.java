//package com.example.envelhecimentosaudavelfsj.Dao;
//
//import android.arch.persistence.room.Database;
//import android.arch.persistence.room.Room;
//import android.arch.persistence.room.RoomDatabase;
//import android.arch.persistence.room.TypeConverters;
//import android.content.Context;
//
//import com.example.envelhecimentosaudavelfsj.Model.Atendimento;
//import com.example.envelhecimentosaudavelfsj.Model.Paciente;
//import com.example.envelhecimentosaudavelfsj.Util.Conversor;
//
////Criado por Yan Vitor 06/06/2019
//
//@TypeConverters(Conversor.class)
//@Database(entities = {Paciente.class, Atendimento.class}, version = 4)
//public abstract class AtendimentoDatabase extends RoomDatabase {
//        private static AtendimentoDatabase banco = null;
//
//        public abstract PacienteDao pacienteDao();
//        public abstract AtendimentoDao atendimentoDao();
//
//        public static AtendimentoDatabase getInstance(Context context){
//            if(banco == null)
//                banco = Room.databaseBuilder(
//                        context,
//                        AtendimentoDatabase.class,
//                        "banco_db"
//                ).fallbackToDestructiveMigration().build();
//            return banco;
//        }
//
//}
