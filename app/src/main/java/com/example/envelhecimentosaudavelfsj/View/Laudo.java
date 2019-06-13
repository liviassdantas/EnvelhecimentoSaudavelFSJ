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

        nomeExibe = findViewById(R.id.laudo_nomePaciente);
        idadeExibe = findViewById(R.id.laudo_idade);
        sexoExibe = findViewById(R.id.laudo_sexo);

        pesoExibe = findViewById(R.id.laudo_peso);
        imcExibe = findViewById(R.id.laudo_imc);
        freqCardExibe = findViewById(R.id.laudo_frequenciaCardiaca);
        alturaExibe = findViewById(R.id.laudo_altura);
        rcqExibe = findViewById(R.id.laudo_rcq);
        pressaoExibe = findViewById(R.id.laudo_pressaArterial);
        oximetriaPreExibe = findViewById(R.id.laudo_oximetriaPreTeste);
        oximetriaPosExibe = findViewById(R.id.laudo_oximetriaPosTeste);
        dobrasCutExibe = findViewById(R.id.laudo_dobrasCutaneas);
        distanciaTesteExibe = findViewById(R.id.laudo_distanciaPercorrido);
        pressaoPreExibe = findViewById(R.id.laudo_pressaoPreTeste);
        pressaoPosExibe = findViewById(R.id.laudo_pressaoPosTeste);
        vo2maxExibe = findViewById(R.id.laudo_vo2Max);

        if (getIntent().hasExtra("PACIENTE")) {
            mPaciente = new Gson().fromJson(getIntent().getStringExtra("PACIENTE"), Paciente.class);
            mAtendimento = mPaciente.getAtendimentos().get(mPaciente.getAtendimentos().size() - 1);
        } else if (getIntent().hasExtra("PACIENTE_ADAPTER")) {
            mPaciente = new Gson().fromJson(getIntent().getStringExtra("PACIENTE_ADAPTER"), Paciente.class);
            mAtendimento = mPaciente.getAtendimentos().get(getIntent().getIntExtra("POSITION", 0));

            findViewById(R.id.laudo_btnSalvar).setVisibility(View.INVISIBLE);
//            findViewById(R.id.laudo_numeroPasso).setVisibility(View.INVISIBLE);
        }

        nomeExibe.setText(mPaciente.getNome());
        idadeExibe.append(" " + mPaciente.getIdade());
        sexoExibe.append(" "  + mPaciente.getSexo());

        pesoExibe.append( (mAtendimento.getPeso() != 0) ? " " + mAtendimento.getPeso() + " Kg" : "");
        imcExibe.append(" " + mAtendimento.getIMC());
        freqCardExibe.append( (!mAtendimento.getFrequenciaCardiaca().isEmpty()) ? " " + mAtendimento.getFrequenciaCardiaca() + " bpm": "");
        alturaExibe.append( (mAtendimento.getAltura() != 0) ? " " + mAtendimento.getAltura() + " m" : "");
        rcqExibe.append( (mAtendimento.getRCQ() != null)  ? " " + mAtendimento.getRCQ() : "");
        pressaoExibe.append(" " + mAtendimento.getPressaoArterial());
        oximetriaPreExibe.append(" " + mAtendimento.getOximetriaPre());
        oximetriaPosExibe.append(" " + mAtendimento.getOximetriaPos());
        dobrasCutExibe.append( (mAtendimento.getDobrasCutaneas()) != 0 ? " " + mAtendimento.getDobrasCutaneas() + "%" : "");
        distanciaTesteExibe.append( (!mAtendimento.getDistanciaTesteErg().isEmpty()) ? " " + mAtendimento.getDistanciaTesteErg() + " Km" : "");
        pressaoPreExibe.append( (mAtendimento.getPApreTeste() != null) ? " " + mAtendimento.getPApreTeste() : "");
        pressaoPosExibe.append( (mAtendimento.getPAposTeste() != null) ? " " + mAtendimento.getPAposTeste() : "");
        vo2maxExibe.append( (!mAtendimento.getVOobtidoTesteErg().isEmpty()) ? " " + mAtendimento.getVOobtidoTesteErg() + " ml" : "");

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

                Intent intent = new Intent(Laudo.this, Principal.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
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
