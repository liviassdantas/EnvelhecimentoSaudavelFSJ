//package com.example.envelhecimentosaudavelfsj.Dao;
//
//import android.arch.persistence.room.Dao;
//import android.arch.persistence.room.Delete;
//import android.arch.persistence.room.Insert;
//import android.arch.persistence.room.Query;
//import android.arch.persistence.room.Transaction;
//import android.arch.persistence.room.Update;
//
//import com.example.envelhecimentosaudavelfsj.Model.Paciente;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Dao
//public abstract class PacienteDao {
//
//    //método inserir
//    @Insert()
//    protected abstract Long _insert(Paciente paciente);
//
//    public long insertReturn(Paciente paciente) {
//        return _insert(paciente);
//    }
//
//    public boolean insert(Paciente paciente) {
//        return _insert(paciente) > 0;
//    }
//
//    @Update
//    public abstract void update(Paciente paciente);
//
//    @Delete
//    public abstract void delete(Paciente paciente);
//
//    @Transaction
//    @Query("SELECT*FROM paciente")
//    public abstract List<PacienteAtendimento> getAll();
//
//    public List<Paciente> getPaciente(List<PacienteAtendimento>list){
//    List<Paciente> retorno = new ArrayList<>();
//    if(list != null){
//    for (PacienteAtendimento pa: list){
//     pa.getPaciente().setAtendimentos(pa.getAtendimentos());
//     retorno.add(pa.getPaciente());
//     }}
//     return retorno;}
//
//    @Query("SELECT * FROM paciente WHERE cpf = :id")
//    public abstract Paciente getPacienteById (Long id);
//}
