package com.example.envelhecimentosaudavelfsj.View;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.envelhecimentosaudavelfsj.Adapter.AtendimentoAdapter;
import com.example.envelhecimentosaudavelfsj.Dao.Banco;
import com.example.envelhecimentosaudavelfsj.Model.Atendimento;
import com.example.envelhecimentosaudavelfsj.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Criado por Yan Vitor 06/06/2019

public class Relatorio extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Atendimento> atendimentoList;
    String CPF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);
        recyclerView = findViewById(R.id.relatorio_recyclerView);

        CPF = getIntent().getStringExtra("cpf");  //pega o CPF digitado no alert dialog da tela anterior

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
//                atendimentoList = Banco.getAtendimentoDatabase(Relatorio.this).servicoDatabase().getAllAtendimentos();

                LinearLayoutManager llm = new LinearLayoutManager(Relatorio.this);
                recyclerView.setLayoutManager(llm);

                AtendimentoAdapter adapter = new AtendimentoAdapter(atendimentoList);
                recyclerView.setAdapter(adapter);

            }
        });

    }
}
