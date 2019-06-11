package com.example.envelhecimentosaudavelfsj.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.envelhecimentosaudavelfsj.Model.Endereco;
import com.example.envelhecimentosaudavelfsj.banco.BancoSQLite;

/**
 * Created by Raphael Rodrigues on 09/06/2019.
 */
public class EnderecoDAO {

    public static final String TABELA_ENDERECO = "TABELA_ENDERECO";
    public static final String CAMPOS = "ID, FK_CPF_PACIENTE, RUA, NUMERO, BAIRRO, CEP, CIDADE, ESTADO, COMPLEMENTO";

    private Context context;

    public EnderecoDAO(Context context) {
        this.context = context;
    }

    public static void createTable(SQLiteDatabase db) {
        StringBuffer create = new StringBuffer();
        create.append("CREATE TABLE " + TABELA_ENDERECO);
        create.append("(");
        create.append("     ID              INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,");
        create.append("     FK_CPF_PACIENTE INTEGER NOT NULL,");
        create.append("     RUA             TEXT,");
        create.append("     NUMERO          TEXT,");
        create.append("     BAIRRO          TEXT,");
        create.append("     CEP             TEXT,");
        create.append("     CIDADE          TEXT,");
        create.append("     ESTADO          TEXT,");
        create.append("     COMPLEMENTO     TEXT,");
        create.append("     FOREIGN KEY (FK_CPF_PACIENTE) REFERENCES TABELA_PACIENTE (CPF)");
        create.append(")");
        db.execSQL(create.toString());
    }

    public long insertEndereco(Endereco endereco) {
        ContentValues contentValuesEndereco = new ContentValues();
        contentValuesEndereco.put("FK_CPF_PACIENTE", endereco.getCpfPaciente());
        contentValuesEndereco.put("RUA", endereco.getLogradouro());
        contentValuesEndereco.put("NUMERO", endereco.getNumero());
        contentValuesEndereco.put("BAIRRO", endereco.getBairro());
        contentValuesEndereco.put("CEP", endereco.getCep());
        contentValuesEndereco.put("CIDADE", endereco.getLocalidade());
        contentValuesEndereco.put("ESTADO", endereco.getUf());
        contentValuesEndereco.put("COMPLEMENTO", endereco.getComplemento());

        SQLiteDatabase conn = BancoSQLite.getInstance(context).getWritableDatabase();

        return conn.insert(TABELA_ENDERECO, null, contentValuesEndereco);
    }

    public Endereco getEnderecoPaciente(Long cpfPacinte) {
        SQLiteDatabase banco = BancoSQLite.getInstance(context).getReadableDatabase();

        String select = String.format("SELECT %s FROM %s WHERE FK_CPF_PACIENTE = ?", CAMPOS, TABELA_ENDERECO);

        Cursor cursor = banco.rawQuery(select, new String[]{String.valueOf(cpfPacinte)});

        Endereco endereco = new Endereco();

        if (cursor.moveToFirst()) {
            endereco.setEnderecoId(cursor.getInt(cursor.getColumnIndex("ID")));
            endereco.setCpfPaciente(cursor.getLong(cursor.getColumnIndex("FK_CPF_PACIENTE")));
            endereco.setLogradouro(cursor.getString(cursor.getColumnIndex("RUA")));
            endereco.setNumero(cursor.getString(cursor.getColumnIndex("NUMERO")));
            endereco.setLocalidade(cursor.getString(cursor.getColumnIndex("CIDADE")));
            endereco.setCep(cursor.getString(cursor.getColumnIndex("CEP")));
            endereco.setUf(cursor.getString(cursor.getColumnIndex("ESTADO")));
            endereco.setBairro(cursor.getString(cursor.getColumnIndex("BAIRRO")));
            endereco.setComplemento(cursor.getString(cursor.getColumnIndex("COMPLEMENTO")));
            Log.i("BANCO", "getEnderecoPaciente: " + endereco.getLocalidade());
        }
        cursor.close();

        return endereco;
    }

    public boolean deleteEndereco(Long cpfPaciente) {
        SQLiteDatabase banco = BancoSQLite.getInstance(context).getWritableDatabase();

        String[] value = new String[]{String.valueOf(cpfPaciente)};

        return banco.delete(TABELA_ENDERECO, "FK_CPF_PACIENTE = ?", value) > 0;
    }

    public boolean updateEndereco(Endereco endereco) {
        SQLiteDatabase banco = BancoSQLite.getInstance(context).getWritableDatabase();

        ContentValues valuesEndereco = new ContentValues();
        valuesEndereco.put("RUA", endereco.getLogradouro());
        valuesEndereco.put("FK_CPF_PACIENTE", endereco.getCpfPaciente());
        valuesEndereco.put("NUMERO", endereco.getNumero());
        valuesEndereco.put("BAIRRO", endereco.getBairro());
        valuesEndereco.put("CEP", endereco.getCep());
        valuesEndereco.put("CIDADE", endereco.getLocalidade());
        valuesEndereco.put("ESTADO", endereco.getUf());
        valuesEndereco.put("COMPLEMENTO", endereco.getComplemento());

        String[] args = new String[]{String.valueOf(endereco.getCpfPaciente())};

        return banco.update(TABELA_ENDERECO, valuesEndereco, "FK_CPF_PACIENTE = ?", args) > 0;
    }
}

