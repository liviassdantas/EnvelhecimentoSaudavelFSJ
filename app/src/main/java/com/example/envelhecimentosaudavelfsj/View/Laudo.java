package com.example.envelhecimentosaudavelfsj.View;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.envelhecimentosaudavelfsj.Dao.AtendimentoDatabase;
import com.example.envelhecimentosaudavelfsj.Model.Atendimento;
import com.example.envelhecimentosaudavelfsj.Model.Paciente;
import com.example.envelhecimentosaudavelfsj.Principal;
import com.example.envelhecimentosaudavelfsj.R;
import com.example.envelhecimentosaudavelfsj.Util.PDF;
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

    private Button btnSalvar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laudo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final AtendimentoDatabase banco = AtendimentoDatabase.getInstance(this.getApplicationContext());

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

        btnSalvar = findViewById(R.id.laudo_btnSalvar);

        final Atendimento atendimentoLaudoExibe = new Gson().fromJson(getIntent()
                .getStringExtra("atendimentoCaminhada"), Atendimento.class);
        final Paciente pacienteLaudoExibe = new Gson().fromJson(getIntent()
                .getStringExtra("pacienteCaminhada"), Paciente.class);

        nomeExibe.setText(pacienteLaudoExibe.getNome());
        idadeExibe.setText(String.valueOf(pacienteLaudoExibe.getIdade()));
        sexoExibe.setText(pacienteLaudoExibe.getSexo());

        pesoExibe.setText(String.valueOf(atendimentoLaudoExibe.getPeso()));
        imcExibe.setText(atendimentoLaudoExibe.getIMC());
        freqCardExibe.setText(atendimentoLaudoExibe.getFrequenciaCardiaca());
        alturaExibe.setText(String.valueOf(atendimentoLaudoExibe.getAltura()));
        rcqExibe.setText(String.valueOf(atendimentoLaudoExibe.getRCQ()));
        pressaoExibe.setText(atendimentoLaudoExibe.getPressaoArterial());
        oximetriaPreExibe.setText(String.valueOf(atendimentoLaudoExibe.getOximetriaPre()));
        oximetriaPosExibe.setText(String.valueOf(atendimentoLaudoExibe.getOximetriaPos()));
        dobrasCutExibe.setText(String.valueOf(atendimentoLaudoExibe.getDobrasCutaneas()));
        distanciaTesteExibe.setText(String.valueOf(atendimentoLaudoExibe.getDistanciaTesteErg()));
        pressaoPreExibe.setText(atendimentoLaudoExibe.getPApreTeste());
        pressaoPosExibe.setText(atendimentoLaudoExibe.getPAposTeste());
        vo2maxExibe.setText(String.valueOf(atendimentoLaudoExibe.getVOobtidoTesteErg()));

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ActivityCompat.checkSelfPermission(Laudo.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Laudo.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
                    return;
                }

                        if (PDF.salvar(pacienteLaudoExibe.getNome(), Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(), pacienteLaudoExibe.getNome(),
                                String.valueOf(pacienteLaudoExibe.getIdade()), pacienteLaudoExibe.getSexo(), String.valueOf(atendimentoLaudoExibe.getPeso()),
                                atendimentoLaudoExibe.getIMC(), atendimentoLaudoExibe.getFrequenciaCardiaca(),
                                String.valueOf(atendimentoLaudoExibe.getAltura()), atendimentoLaudoExibe.getRCQ(), atendimentoLaudoExibe.getPressaoArterial(),
                                String.valueOf(atendimentoLaudoExibe.getDobrasCutaneas()), atendimentoLaudoExibe.getDistanciaTesteErg(), atendimentoLaudoExibe.getPApreTeste(),
                                atendimentoLaudoExibe.getPAposTeste(), atendimentoLaudoExibe.getVOobtidoTesteErg(),atendimentoLaudoExibe.getOximetriaPre(), atendimentoLaudoExibe.getOximetriaPos())) {

                            Toast.makeText(getApplicationContext(), "PDF Salvo", Toast.LENGTH_SHORT).show();
                        }else  {
                            Toast.makeText(getApplicationContext(), "Não salvo", Toast.LENGTH_SHORT).show();
                        }

                Intent finalI = new Intent(Laudo.this, Principal.class);
                startActivity(finalI);
                    }
            });
        }


        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            int id = item.getItemId();
            if (id == android.R.id.home) {
                finish();
            }

            return super.onOptionsItemSelected(item);
        }
    }
