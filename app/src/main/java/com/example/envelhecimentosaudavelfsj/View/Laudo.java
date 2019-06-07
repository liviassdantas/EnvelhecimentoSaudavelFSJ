package com.example.envelhecimentosaudavelfsj.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.envelhecimentosaudavelfsj.Model.Atendimento;
import com.example.envelhecimentosaudavelfsj.Model.Paciente;
import com.example.envelhecimentosaudavelfsj.R;
import com.google.gson.Gson;
/*
 *Created by: Livia Dantas - 05/06/2019
 */

public class Laudo extends AppCompatActivity {
    private TextView nomeExibe;
    private TextView idadeExibe;
    private TextView sexoExibe;
    private TextView pesoExibe;
    private TextView imcExibe;
    private TextView freqCardExibe;
    private TextView alturaExibe;
    private TextView rcqExibe;
    private TextView pressaoExibe;
    private TextView oximetriaPreExibe;
    private TextView oximetriaPosExibe;
    private TextView dobrasCutExibe;
    private TextView distanciaTesteExibe;
    private TextView pressaoPreExibe;
    private TextView pressaoPosExibe;
    private TextView vo2maxExibe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laudo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nomeExibe = findViewById(R.id.nomepessoa_laudo);
        idadeExibe = findViewById(R.id.idadeexibe_laudo);
        sexoExibe = findViewById(R.id.sexoExibir_laudo);

        pesoExibe = findViewById(R.id.pesoexibe_laudo);
        imcExibe = findViewById(R.id.resultado_imc_laudo);
        freqCardExibe = findViewById(R.id.frequencia_exibe_laudo);
        alturaExibe = findViewById(R.id.alturaexibe_laudo);
        rcqExibe = findViewById(R.id.rcqExibe);
        pressaoExibe = findViewById(R.id.pa_exibe_laudo);
        oximetriaPreExibe = findViewById(R.id.valorpretesteoximetriaexibe_laudo);
        oximetriaPosExibe = findViewById(R.id.valorpostesteoximetriaexibe_laudo);
        dobrasCutExibe = findViewById(R.id.resultadoDobrasExibe_laudo);
        distanciaTesteExibe = findViewById(R.id.distanciaCaminhadaExibe_laudo);
        pressaoPreExibe = findViewById(R.id.paPreTesteExibe_laudo);
        pressaoPosExibe = findViewById(R.id.paPosTesteExibe_laudo);
        vo2maxExibe = findViewById(R.id.vo2maxExibe_laudo);

        Atendimento atendimentoLaudoExibe = new Gson().fromJson(getIntent()
                .getStringExtra("atendimentoCaminhada"), Atendimento.class);
        Paciente pacienteLaudoExibe = new Gson().fromJson(getIntent()
                .getStringExtra("pacienteCaminhada"),Paciente.class);

        nomeExibe.setText(pacienteLaudoExibe.getNome());
        idadeExibe.setText(String.valueOf(pacienteLaudoExibe.getIdade()));
        sexoExibe.setText(pacienteLaudoExibe.getSexo());

        pesoExibe.setText(String.valueOf(atendimentoLaudoExibe.getPeso()));
        imcExibe.setText(atendimentoLaudoExibe.getIMC());
        freqCardExibe.setText(atendimentoLaudoExibe.getFrequenciaCardiaca());
        alturaExibe.setText(String.valueOf(atendimentoLaudoExibe.getAltura()));
    //  rcqExibe.setText(String.valueOf(atendimentoLaudoExibe.getRCQ()));
        pressaoExibe.setText(atendimentoLaudoExibe.getPressaoArterial());
        oximetriaPreExibe.setText(String.valueOf(atendimentoLaudoExibe.getOximetriaPre()));
        oximetriaPosExibe.setText(String.valueOf(atendimentoLaudoExibe.getOximetriaPos()));
        dobrasCutExibe.setText(String.valueOf(atendimentoLaudoExibe.getDobrasCutaneas()));
        distanciaTesteExibe.setText(String.valueOf(atendimentoLaudoExibe.getDistanciaTesteErg()));
        pressaoPreExibe.setText(atendimentoLaudoExibe.getPApreTeste());
        pressaoPosExibe.setText(atendimentoLaudoExibe.getPAposTeste());
        vo2maxExibe.setText(String.valueOf(atendimentoLaudoExibe.getVOobtidoTesteErg()));

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
