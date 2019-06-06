package com.example.envelhecimentosaudavelfsj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.envelhecimentosaudavelfsj.View.DadosPaciente;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        setTitle("Envelhecimento Saudável");

        (findViewById(R.id.Txv_tela_inicial_cadatrar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNovoPaciente(v);
            }
        });

        (findViewById(R.id.Txv_tela_inicial_relatório)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRelatorio(v);
            }
        });

    }

    public void btnNovoPaciente(View v)
    {
        startActivity(new Intent(Principal.this, DadosPaciente.class));
    }

    public void btnRelatorio(View v)
    {

    }

}
