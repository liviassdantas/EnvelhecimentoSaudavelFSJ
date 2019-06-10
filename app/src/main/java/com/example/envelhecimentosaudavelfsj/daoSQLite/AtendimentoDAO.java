package com.example.envelhecimentosaudavelfsj.daoSQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.envelhecimentosaudavelfsj.Model.Atendimento;
import com.example.envelhecimentosaudavelfsj.banco.BancoSQLite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Raphael Rodrigues on 09/06/2019.
 */
public class AtendimentoDAO {

    private static String TABELA_ATENDIMENTO = "TABELA_ATENDIMENTO";
    private static String CAMPOS = "ID, FK_CPF_PACIENTE, PESO, ALTURA, PRESSAO, IMC, RCQ, PA_PRE_TESTE, " +
            "PA_POS_TESTE, FREQ_CARDIACA, DISTANCIA_TESTE, VO_OBTIDO, OXIMETRIA_PRE, OXIMETRIA_POS, DOBRAS_CUTANEAS, DATA_ATENDIMENTO";

    private Context context;

    public AtendimentoDAO(Context context) {
        this.context = context;
    }

    public static void createTable(SQLiteDatabase db) {
        StringBuffer create = new StringBuffer();
        create.append("CREATE TABLE " + TABELA_ATENDIMENTO);
        create.append("(");
        create.append("     ID              INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,");
        create.append("     FK_CPF_PACIENTE INTEGER NOT NULL,");
        create.append("     PESO                REAL,");
        create.append("     ALTURA              REAL,");
        create.append("     PRESSAO             TEXT,");
        create.append("     IMC                 TEXT,");
        create.append("     RCQ                 TEXT,");
        create.append("     PA_PRE_TESTE        TEXT,");
        create.append("     PA_POS_TESTE        TEXT,");
        create.append("     FREQ_CARDIACA       TEXT,");
        create.append("     DISTANCIA_TESTE     TEXT,");
        create.append("     VO_OBTIDO           TEXT,");
        create.append("     OXIMETRIA_PRE       TEXT,");
        create.append("     OXIMETRIA_POS       TEXT,");
        create.append("     DOBRAS_CUTANEAS     REAL,");
        create.append("     DATA_ATENDIMENTO    TEXT");
        create.append(")");
        db.execSQL(create.toString());
    }

    public boolean insertAtendimento(Atendimento atendimento) {

        String dataAtendimento = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS", new Locale("en-US"))
                .format(atendimento.getDataEHoraAtendimento());

        ContentValues values = new ContentValues();
        values.put("ID",                atendimento.getAtendimentoId());
        values.put("FK_CPF_PACIENTE",   atendimento.getCpfPaciente());
        values.put("PESO",              atendimento.getPeso());
        values.put("ALTURA",            atendimento.getAltura());
        values.put("PRESSAO",           atendimento.getPressaoArterial());
        values.put("IMC",               atendimento.getIMC());
        values.put("RCQ",               atendimento.getRCQ());
        values.put("PA_PRE_TESTE",      atendimento.getPApreTeste());
        values.put("PA_POS_TESTE",      atendimento.getPAposTeste());
        values.put("FREQ_CARDIACA",     atendimento.getFrequenciaCardiaca());
        values.put("DISTANCIA_TESTE",   atendimento.getDistanciaTesteErg());
        values.put("VO_OBTIDO",         atendimento.getVOobtidoTesteErg());
        values.put("OXIMETRIA_PRE",     atendimento.getOximetriaPre());
        values.put("OXIMETRIA_POS",     atendimento.getOximetriaPos());
        values.put("DOBRAS_CUTANEAS",   atendimento.getDobrasCutaneas());
        values.put("DATA_ATENDIMENTO",  dataAtendimento);

        SQLiteDatabase banco = BancoSQLite.getInstance(context).getWritableDatabase();

        return banco.insert(TABELA_ATENDIMENTO, null, values) != -1;
    }

    public List<Atendimento> getAtendimentosByPaciente(Long cpfPaciente) {
        SQLiteDatabase banco = BancoSQLite.getInstance(context).getReadableDatabase();

        String select = String.format("SELECT %s FROM %s WHERE FK_CPF_PACIENTE = ?", CAMPOS, TABELA_ATENDIMENTO);

        Cursor cursor = banco.rawQuery(select, new String[]{String.valueOf(cpfPaciente)});

        Atendimento atendimento = null;
        List<Atendimento> atendimentos = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                atendimento = new Atendimento();
                atendimento.setAtendimentoId(cursor.getLong(cursor.getColumnIndex("ID")));
                atendimento.setCpfPaciente(cursor.getLong(cursor.getColumnIndex("FK_CPF_PACIENTE")));
                atendimento.setPeso(cursor.getDouble(cursor.getColumnIndex("PESO")));
                atendimento.setAltura(cursor.getDouble(cursor.getColumnIndex("ALTURA")));
                atendimento.setPressaoArterial(cursor.getString(cursor.getColumnIndex("PRESSAO")));
                atendimento.setIMC(cursor.getString(cursor.getColumnIndex("IMC")));
                atendimento.setRCQ(cursor.getString(cursor.getColumnIndex("RCQ")));
                atendimento.setPApreTeste(cursor.getString(cursor.getColumnIndex("PA_PRE_TESTE")));
                atendimento.setPAposTeste(cursor.getString(cursor.getColumnIndex("PA_POS_TESTE")));
                atendimento.setFrequenciaCardiaca(cursor.getString(cursor.getColumnIndex("FREQ_CARDIACA")));
                atendimento.setDistanciaTesteErg(cursor.getString(cursor.getColumnIndex("DISTANCIA_TESTE")));
                atendimento.setVOobtidoTesteErg(cursor.getString(cursor.getColumnIndex("VO_OBTIDO")));
                atendimento.setOximetriaPre(cursor.getString(cursor.getColumnIndex("OXIMETRIA_PRE")));
                atendimento.setOximetriaPos(cursor.getString(cursor.getColumnIndex("OXIMETRIA_POS")));
                atendimento.setDobrasCutaneas(cursor.getDouble(cursor.getColumnIndex("DOBRAS_CUTANEAS")));

                String dataNascimento;
                dataNascimento = cursor.getString(cursor.getColumnIndex("DATA_ATENDIMENTO"));

                Date date = null;
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS", new Locale("en-US"));
                    date = sdf.parse(dataNascimento);

                    sdf = new SimpleDateFormat("dd/MM/yyyy HH:MM:SS", new Locale("pt-BR"));
                    dataNascimento = sdf.format(date);

                    date = sdf.parse(dataNascimento);
                    atendimento.setDataEHoraAtendimento(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                atendimento.setDataEHoraAtendimento(date);

                atendimentos.add(atendimento);
            } while (cursor.moveToNext());
        }
        cursor.close();
        banco.close();
        return atendimentos;
    }

    public boolean deleteAtendimento(Long cpfPaciente) {
        SQLiteDatabase banco = BancoSQLite.getInstance(context).getWritableDatabase();

        String[] args = new String[]{String.valueOf(cpfPaciente)};

        return banco.delete(TABELA_ATENDIMENTO, "FK_CPF_PACIENTE = ?", args) > 0;
    }

    public boolean updateAtendimento(Atendimento atendimento) {
        SQLiteDatabase banco = BancoSQLite.getInstance(context).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ID",                atendimento.getAtendimentoId());
        values.put("FK_CPF_PACIENTE",   atendimento.getCpfPaciente());
        values.put("PESO",              atendimento.getPeso());
        values.put("ALTURA",            atendimento.getAltura());
        values.put("PRESSAO",           atendimento.getPressaoArterial());
        values.put("IMC",               atendimento.getIMC());
        values.put("RCQ",               atendimento.getRCQ());
        values.put("PA_PRE_TESTE",      atendimento.getPApreTeste());
        values.put("PA_POS_TESTE",      atendimento.getPAposTeste());
        values.put("FREQ_CARDIACA",     atendimento.getFrequenciaCardiaca());
        values.put("DISTANCIA_TESTE",   atendimento.getDistanciaTesteErg());
        values.put("VO_OBTIDO",         atendimento.getVOobtidoTesteErg());
        values.put("OXIMETRIA_PRE",     atendimento.getOximetriaPre());
        values.put("OXIMETRIA_POS",     atendimento.getOximetriaPos());
        values.put("DOBRAS_CUTANEAS",   atendimento.getDobrasCutaneas());

        String dataAtendimento = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS", new Locale("en-US"))
                .format(atendimento.getDataEHoraAtendimento());
        values.put("DATA_ATENDIMENTO",  dataAtendimento);

        String[] args = new String[]{String.valueOf(atendimento.getAtendimentoId()), String.valueOf(atendimento.getCpfPaciente())};
        return banco.update(TABELA_ATENDIMENTO, values, "ID = ? AND FK_CPF_PACIENTE = ?", args) > 0;
    }
}
