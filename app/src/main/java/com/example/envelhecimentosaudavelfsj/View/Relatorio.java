package com.example.envelhecimentosaudavelfsj.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.envelhecimentosaudavelfsj.Adapter.AtendimentoAdapter;
import com.example.envelhecimentosaudavelfsj.Model.Paciente;
import com.example.envelhecimentosaudavelfsj.R;
import com.example.envelhecimentosaudavelfsj.daoSQLite.PacienteDAO;

//Criado por Yan Vitor 06/06/2019

@SuppressWarnings("ALL")
public class Relatorio extends AppCompatActivity {

    private String mCpf;
    private RecyclerView mRecyclerView;
    private Paciente mPaciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = findViewById(R.id.relatorio_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        if (getIntent().getStringExtra("CPF") != null) {
            mCpf = getIntent().getStringExtra("CPF");

            mPaciente = new PacienteDAO(Relatorio.this).getPacienteByCpf(Long.parseLong(mCpf));

            AtendimentoAdapter adapter = new AtendimentoAdapter(this, mPaciente);
            mRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}