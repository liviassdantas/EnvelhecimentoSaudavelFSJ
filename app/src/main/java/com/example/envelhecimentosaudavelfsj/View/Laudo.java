package com.example.envelhecimentosaudavelfsj.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.envelhecimentosaudavelfsj.Model.Atendimento;
import com.example.envelhecimentosaudavelfsj.Model.Paciente;
import com.example.envelhecimentosaudavelfsj.Principal;
import com.example.envelhecimentosaudavelfsj.R;
import com.example.envelhecimentosaudavelfsj.dao.AtendimentoDAO;
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

    private Paciente mPaciente;
    private Atendimento mAtendimento;

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

        if (getIntent().hasExtra("PACIENTE")) {
            mPaciente = new Gson().fromJson(getIntent().getStringExtra("PACIENTE"), Paciente.class);
            mAtendimento = mPaciente.getAtendimentos().get(mPaciente.getAtendimentos().size() - 1);
        } else if (getIntent().hasExtra("PACIENTE_ADAPTER")) {
            mPaciente = new Gson().fromJson(getIntent().getStringExtra("PACIENTE_ADAPTER"), Paciente.class);
            mAtendimento = mPaciente.getAtendimentos().get(getIntent().getIntExtra("POSITION", 0));

            findViewById(R.id.laudo_btnSalvar).setVisibility(View.INVISIBLE);
            findViewById(R.id.laudo_numeroPasso).setVisibility(View.INVISIBLE);
        }

        nomeExibe.setText(mPaciente.getNome());
        idadeExibe.setText(String.valueOf(mPaciente.getIdade()));
        sexoExibe.setText(mPaciente.getSexo());

        pesoExibe.setText(String.valueOf(mAtendimento.getPeso()));
        imcExibe.setText(mAtendimento.getIMC());
        freqCardExibe.setText(mAtendimento.getFrequenciaCardiaca());
        alturaExibe.setText(String.valueOf(mAtendimento.getAltura()));
        rcqExibe.setText(String.valueOf(mAtendimento.getRCQ()));
        pressaoExibe.setText(mAtendimento.getPressaoArterial());
        oximetriaPreExibe.setText(String.valueOf(mAtendimento.getOximetriaPre()));
        oximetriaPosExibe.setText(String.valueOf(mAtendimento.getOximetriaPos()));
        dobrasCutExibe.setText(String.valueOf(mAtendimento.getDobrasCutaneas()));
        distanciaTesteExibe.setText(String.valueOf(mAtendimento.getDistanciaTesteErg()));
        pressaoPreExibe.setText(mAtendimento.getPApreTeste());
        pressaoPosExibe.setText(mAtendimento.getPAposTeste());
        vo2maxExibe.setText(String.valueOf(mAtendimento.getVOobtidoTesteErg()));

        findViewById(R.id.laudo_btnSalvar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (ActivityCompat.checkSelfPermission(Laudo.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(Laudo.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
//                    return;
//                }
//
//                if (PDF.salvar(pacienteLaudoExibe.getNome(), Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(), pacienteLaudoExibe.getNome(),
//                        String.valueOf(pacienteLaudoExibe.getIdade()), pacienteLaudoExibe.getSexo(), String.valueOf(mAtendimento.getPeso()),
//                        mAtendimento.getIMC(), mAtendimento.getFrequenciaCardiaca(),
//                        String.valueOf(mAtendimento.getAltura()), mAtendimento.getRCQ(), mAtendimento.getPressaoArterial(),
//                        String.valueOf(mAtendimento.getDobrasCutaneas()), mAtendimento.getDistanciaTesteErg(), mAtendimento.getPApreTeste(),
//                        mAtendimento.getPAposTeste(), mAtendimento.getVOobtidoTesteErg(), mAtendimento.getOximetriaPre(), mAtendimento.getOximetriaPos())) {
//
//                    Toast.makeText(getApplicationContext(), "PDF Salvo", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getApplicationContext(), "Não salvo", Toast.LENGTH_SHORT).show();
//                }

                if (mAtendimento != null) {
                    new AtendimentoDAO(getBaseContext()).insertAtendimento(mAtendimento);
                }

                Intent finalI = new Intent(Laudo.this, Principal.class);
                finalI.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(finalI);
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
