package com.example.envelhecimentosaudavelfsj.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.envelhecimentosaudavelfsj.Adapter.AtendimentoAdapter;
import com.example.envelhecimentosaudavelfsj.Model.Atendimento;
import com.example.envelhecimentosaudavelfsj.R;
import com.example.envelhecimentosaudavelfsj.daoSQLite.AtendimentoDAO;

import java.util.ArrayList;
import java.util.List;

//Criado por Yan Vitor 06/06/2019

@SuppressWarnings("ALL")
public class Relatorio extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Atendimento> mAtendimentoList;
    private String mCpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.relatorio_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        if (getIntent().getStringExtra("CPF") != null) {
            mCpf = getIntent().getStringExtra("CPF");

            mAtendimentoList = new ArrayList<>();
            mAtendimentoList = new AtendimentoDAO(Relatorio.this).getAtendimentosByPaciente(Long.parseLong(mCpf));

            AtendimentoAdapter adapter = new AtendimentoAdapter(this, mAtendimentoList);
            recyclerView.setAdapter(adapter);
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