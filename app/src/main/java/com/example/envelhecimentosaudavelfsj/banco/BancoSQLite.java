package com.example.envelhecimentosaudavelfsj.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.envelhecimentosaudavelfsj.daoSQLite.AtendimentoDAO;
import com.example.envelhecimentosaudavelfsj.daoSQLite.EnderecoDAO;
import com.example.envelhecimentosaudavelfsj.daoSQLite.PacienteDAO;

/**
 * Created by Raphael Rodrigues on 08/06/2019.
 */
public class BancoSQLite extends SQLiteOpenHelper {

    private static final String BD_NAME = "BancoDeDadosProjetoEnvelhecimentoSaudavel";
    private static final int BD_VERSION = 1;

    private static BancoSQLite conexaoBancoSQLite;

    public BancoSQLite(Context context) {
        super(context, BD_NAME, null, BD_VERSION);
    }

    public static BancoSQLite getInstance(Context context) {
        if (conexaoBancoSQLite == null) {
            conexaoBancoSQLite = new BancoSQLite(context);
        }
        return conexaoBancoSQLite;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        PacienteDAO.createTable(db);
        EnderecoDAO.createTable(db);
        AtendimentoDAO.createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            onCreate(db);
        }
    }
}
