package com.example.envelhecimentosaudavelfsj.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.envelhecimentosaudavelfsj.Model.Atendimento;
import com.example.envelhecimentosaudavelfsj.Model.Endereco;
import com.example.envelhecimentosaudavelfsj.Model.Paciente;
import com.example.envelhecimentosaudavelfsj.banco.BancoSQLite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Raphael Rodrigues on 08/06/2019.
 */
public class PacienteDAO {

    public static final String TABELA_PACIENTE = "TABELA_PACIENTE";
    public static final String CAMPOS = "CPF, SERVIDOR_ID, NOME, SEXO, DATA_NASCIMENTO, IDADE, TELEFONE";

    private Context context;

    public PacienteDAO(Context context) {
        this.context = context;
    }

    public static void createTable(SQLiteDatabase db) {
        StringBuffer create = new StringBuffer();
        create.append("CREATE TABLE " + TABELA_PACIENTE);
        create.append("(");
        create.append("     CPF             INTEGER NOT NULL PRIMARY KEY,");
        create.append("     SERVIDOR_ID     INTEGER,");
        create.append("     NOME            TEXT,");
        create.append("     SEXO            TEXT,");
        create.append("     DATA_NASCIMENTO TEXT,");
        create.append("     IDADE           INTEGER,");
        create.append("     TELEFONE        TEXT");
        create.append(")");
        db.execSQL(create.toString());
    }

    public boolean insertPaciente(Paciente paciente) {

        ContentValues values = new ContentValues();
        values.put("CPF",               paciente.getCpf());
        values.put("SERVIDOR_ID",       paciente.getServidorId());
        values.put("NOME",              paciente.getNome());
        values.put("SEXO",              paciente.getSexo());

        String dataNascimento = new SimpleDateFormat("yyyy-MM-dd", new Locale("en-US"))
                .format(paciente.getDataNascimento());

        values.put("DATA_NASCIMENTO",   dataNascimento);
        values.put("IDADE",             paciente.getIdade());
        values.put("TELEFONE",          paciente.getTelefone());

        SQLiteDatabase banco = BancoSQLite.getInstance(context).getWritableDatabase();

        long id = banco.insert(TABELA_PACIENTE, null, values);
        if (id != -1) {
            new EnderecoDAO(context).insertEndereco(paciente.getEndereco());
        }

        return id != -1;
    }

    public Paciente getPacienteByCpf(Long cpfPaciente) {
        SQLiteDatabase banco = BancoSQLite.getInstance(context).getReadableDatabase();

        String select = String.format("SELECT %s FROM %s WHERE CPF = ?", CAMPOS, TABELA_PACIENTE);

        Cursor cursor = banco.rawQuery(select, new String[]{String.valueOf(cpfPaciente)});

        Paciente paciente = null;

        Endereco endereco;

        List<Atendimento> atendimentos;

        if (cursor.moveToFirst()) {
            paciente = new Paciente();
            endereco = new EnderecoDAO(context).getEnderecoPaciente(cpfPaciente);
            atendimentos = new AtendimentoDAO(context).getAtendimentosByPaciente(cpfPaciente);

            paciente.setCpf(cursor.getLong(cursor.getColumnIndex("CPF")));
            paciente.setServidorId(cursor.getLong(cursor.getColumnIndex("SERVIDOR_ID")));
            paciente.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
            paciente.setEndereco(endereco);
            paciente.setSexo(cursor.getString(cursor.getColumnIndex("SEXO")));
            paciente.setAtendimentos(atendimentos);

            String dataNascimento;
            dataNascimento = cursor.getString(cursor.getColumnIndex("DATA_NASCIMENTO"));

            Date date = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", new Locale("en-US"));
                date = sdf.parse(dataNascimento);

                sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt-BR"));
                dataNascimento = sdf.format(date);

                date = sdf.parse(dataNascimento);
                paciente.setDataNascimento(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            paciente.setDataNascimento(date);
            paciente.setIdade(cursor.getInt(cursor.getColumnIndex("IDADE")));
            paciente.setTelefone(cursor.getString(cursor.getColumnIndex("TELEFONE")));
        }
        cursor.close();

        return paciente;
    }

    public boolean checkPacienteCadastrado(Long cpfPaciente) {
        SQLiteDatabase banco = BancoSQLite.getInstance(context).getReadableDatabase();

        String select = String.format("SELECT NOME FROM %s WHERE CPF = ?", TABELA_PACIENTE);

        Cursor cursor = banco.rawQuery(select, new String[]{String.valueOf(cpfPaciente)});

        boolean resultadoBusca = cursor.moveToFirst();
        cursor.close();

        return resultadoBusca;
    }

    public List<Paciente> getAllPacientes() {
        SQLiteDatabase banco = BancoSQLite.getInstance(context).getReadableDatabase();

        String select = String.format("SELECT %s FROM %s ", CAMPOS, TABELA_PACIENTE);

        Cursor cursor = banco.rawQuery(select, null);

        List<Paciente> pacientes = new ArrayList<>();

        Endereco endereco;

        if (cursor.moveToFirst()) {
            do {
                Paciente paciente = new Paciente();
                paciente.setCpf(cursor.getLong(cursor.getColumnIndex("CPF")));

                endereco = new EnderecoDAO(context).getEnderecoPaciente(paciente.getCpf());

                paciente.setServidorId(cursor.getLong(cursor.getColumnIndex("SERVIDOR_ID")));
                paciente.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
                paciente.setEndereco(endereco);
                paciente.setSexo(cursor.getString(cursor.getColumnIndex("SEXO")));

                String dataNascimento;
                dataNascimento = cursor.getString(cursor.getColumnIndex("DATA_NASCIMENTO"));

                Date date = null;
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", new Locale("en-US"));
                    date = sdf.parse(dataNascimento);

                    sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt-BR"));
                    dataNascimento = sdf.format(date);

                    date = sdf.parse(dataNascimento);
                    paciente.setDataNascimento(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                paciente.setDataNascimento(date);
                paciente.setIdade(cursor.getInt(cursor.getColumnIndex("IDADE")));
                paciente.setTelefone(cursor.getString(cursor.getColumnIndex("TELEFONE")));

                pacientes.add(paciente);
            } while (cursor.moveToNext());
        }
        cursor.close();
        banco.close();
        return pacientes;
    }

    public long updatePaciente(Paciente paciente) {

        String dataNascimento = new SimpleDateFormat("yyyy-MM-dd", new Locale("en-US"))
                .format(paciente.getDataNascimento());

        new EnderecoDAO(context).updateEndereco(paciente.getEndereco());

        ContentValues values = new ContentValues();
        values.put("CPF",               paciente.getCpf());
        values.put("SERVIDOR_ID",       paciente.getServidorId());
        values.put("NOME",              paciente.getNome());
        values.put("SEXO",              paciente.getSexo());
        values.put("DATA_NASCIMENTO",   dataNascimento);
        values.put("IDADE",             paciente.getIdade());
        values.put("TELEFONE",          paciente.getTelefone());

        SQLiteDatabase banco = BancoSQLite.getInstance(context).getWritableDatabase();

        String[] args = new String[]{String.valueOf(paciente.getCpf())};

        return banco.update(TABELA_PACIENTE, values, "CPF = ?", args);
    }

    public boolean deletePaciente(Long cpfPaciente) {
        SQLiteDatabase banco = BancoSQLite.getInstance(context).getWritableDatabase();

        new EnderecoDAO(context).deleteEndereco(cpfPaciente);

        return banco.delete(TABELA_PACIENTE, "CPF = ?", new String[]{String.valueOf(cpfPaciente)}) > 0;
    }
}
