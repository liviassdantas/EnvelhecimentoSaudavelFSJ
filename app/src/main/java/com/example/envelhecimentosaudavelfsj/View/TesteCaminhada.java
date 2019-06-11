package com.example.envelhecimentosaudavelfsj.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.envelhecimentosaudavelfsj.Model.Atendimento;
import com.example.envelhecimentosaudavelfsj.Model.Paciente;
import com.example.envelhecimentosaudavelfsj.R;
import com.google.gson.Gson;
/*
 *Created by: Livia Dantas - 05/06/2019
 */

public class TesteCaminhada extends AppCompatActivity {

    private TextInputLayout distancia;
    private TextInputLayout vo2max;
    private TextInputLayout paSistolicapreTeste;
    private TextInputLayout paDiastolicapreTeste;
    private TextInputLayout paSistolicaposTeste;
    private TextInputLayout paDiastolicaposTeste;
    private Paciente mPaciente;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_teste_caminhada);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        distancia = findViewById(R.id.teste_caminhada_distancia);
        vo2max = findViewById(R.id.teste_caminhada_vo2max);
        paSistolicapreTeste = findViewById(R.id.teste_caminhada_pa_sistolica_pre);
        paDiastolicapreTeste = findViewById(R.id.teste_caminhada_pa_diastolica_pre);
        paSistolicaposTeste = findViewById(R.id.teste_caminhada_pa_sistolica_pos);
        paDiastolicaposTeste = findViewById(R.id.teste_caminhada_pa_diastolica_pos);

        if (getIntent().hasExtra("PACIENTE")) {
            mPaciente = new Gson().fromJson(getIntent().getStringExtra("PACIENTE"), Paciente.class);
        }

        final Atendimento atendimento = mPaciente.getAtendimentos().get(mPaciente.getAtendimentos().size() - 1);

        findViewById(R.id.teste_caminhada_btnProximo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                atendimento.setPApreTeste(
                        paSistolicapreTeste.getEditText().getText().toString(),
                        paDiastolicapreTeste.getEditText().getText().toString()
                );

                atendimento.setPAposTeste(
                        paSistolicaposTeste.getEditText().getText().toString(),
                        paDiastolicaposTeste.getEditText().getText().toString()
                );

                atendimento.setDistanciaTesteErg(distancia.getEditText().getText().toString());
                atendimento.setVOobtidoTesteErg(vo2max.getEditText().getText().toString());

                mPaciente.getAtendimentos().set(mPaciente.getAtendimentos().size() - 1, atendimento);

                Intent intent = new Intent(TesteCaminhada.this, Laudo.class);
                intent.putExtra("PACIENTE", new Gson().toJson(mPaciente));

                startActivity(intent);
            }
        });
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
