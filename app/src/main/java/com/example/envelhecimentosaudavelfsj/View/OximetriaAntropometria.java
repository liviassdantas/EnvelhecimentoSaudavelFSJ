package com.example.envelhecimentosaudavelfsj.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.envelhecimentosaudavelfsj.Model.Atendimento;
import com.example.envelhecimentosaudavelfsj.Model.Paciente;
import com.example.envelhecimentosaudavelfsj.R;
import com.example.envelhecimentosaudavelfsj.Util.TextListener;
import com.example.envelhecimentosaudavelfsj.Util.Util;
import com.example.envelhecimentosaudavelfsj.dao.PacienteDAO;
import com.google.gson.Gson;

import java.util.Calendar;
/*
 *Created by: Livia Dantas - 05/06/2019
 */

public class OximetriaAntropometria extends AppCompatActivity {

    private TextInputLayout vPreTeste;
    private TextInputLayout vPosTeste;
    private TextInputLayout peso;
    private TextInputLayout altura;
    private TextInputLayout pressaoDiastolica;
    private TextInputLayout pressaoSistolica;
    private TextInputLayout frequeciaCardiaca;
    private TextInputLayout IMC;
    private TextInputLayout cintura;
    private TextInputLayout quadril;
    private EditText RCQedt;
    private Double cinturaRCQ;
    private Double quadrilRCQ;

    private String alturaInserida;
    private String pesoInserido;

    private Paciente mPaciente;
    private Atendimento mAtendimento;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_oximetria_antro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        vPreTeste = findViewById(R.id.oximetria_valor_pre_teste);
        vPosTeste = findViewById(R.id.oximetria_valor_pos_teste);
        peso = findViewById(R.id.antropometria_peso);
        altura = findViewById(R.id.antropometria_altura);
        pressaoDiastolica = findViewById(R.id.antropometria_pressaodiastolica);
        pressaoSistolica = findViewById(R.id.antropometria_pressaosistolica);
        frequeciaCardiaca = findViewById(R.id.antropometria_fc);
        IMC = findViewById(R.id.oximetria_imc);
        RCQedt = findViewById(R.id.oximetria_rcq_edit);

        if (getIntent().hasExtra("CPF")) {
            Long cpf = Long.parseLong(getIntent().getStringExtra("CPF"));
            mPaciente = new PacienteDAO(getBaseContext()).getPacienteByCpf(cpf);
        }

        mAtendimento = new Atendimento();

        findViewById(R.id.oximetria_btnProximo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    mAtendimento.setDataEHoraAtendimento(Calendar.getInstance().getTime());

                    mAtendimento.setOximetriaPre((!vPreTeste.getEditText().getText().toString().isEmpty()) ?
                            "SaO2 " + vPreTeste.getEditText().getText().toString() + "%" : "");

                    mAtendimento.setOximetriaPos((!vPosTeste.getEditText().getText().toString().isEmpty()) ?
                            "SaO2 " + vPosTeste.getEditText().getText().toString() + "%" : "");

                    mAtendimento.setPeso((!peso.getEditText().getText().toString().isEmpty()) ?
                            Double.parseDouble(peso.getEditText().getText().toString()) : 0D);

                    mAtendimento.setAltura((!altura.getEditText().getText().toString().isEmpty()) ?
                            Double.parseDouble(altura.getEditText().getText().toString()) : 0D);

                    mAtendimento.setPressaoArterial(
                            pressaoSistolica.getEditText().getText().toString(),
                            pressaoDiastolica.getEditText().getText().toString()
                    );

                    mAtendimento.setFrequenciaCardiaca(frequeciaCardiaca.getEditText().getText().toString());
                    mAtendimento.setIMC(IMC.getEditText().getText().toString());

                    mAtendimento.setCpfPaciente(mPaciente.getCpf());
                    mPaciente.setAtendimentos(mAtendimento);

                    Intent intent = new Intent(OximetriaAntropometria.this, DobrasCutaneas.class);
                    intent.putExtra("PACIENTE", new Gson().toJson(mPaciente));

                    startActivity(intent);
            }
        });

        RCQedt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View viewAlertRCQ = getLayoutInflater().inflate(R.layout.alert_rcq, null);

                cintura = viewAlertRCQ.findViewById(R.id.RCQ_cintura);
                quadril = viewAlertRCQ.findViewById(R.id.RCQ_quadril);

                new AlertDialog.Builder(OximetriaAntropometria.this)
                        .setView(viewAlertRCQ)
                        .setTitle("Dados do RCQ")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (!cintura.getEditText().getText().toString().isEmpty() &&
                                        !quadril.getEditText().getText().toString().isEmpty()) {

                                    cinturaRCQ = Double.parseDouble(cintura.getEditText().getText().toString());
                                    quadrilRCQ = Double.parseDouble(quadril.getEditText().getText().toString());

                                    mAtendimento.setRCQ(new Util().RCQ(cinturaRCQ, quadrilRCQ, mPaciente.getSexo(), mPaciente.getIdade()));

                                    RCQedt.setText(mAtendimento.getRCQ());
                                }
                            }
                        })
                        .create()
                        .show();
            }
        });

        altura.getEditText().addTextChangedListener(new TextListener(this, "altura"));
        peso.getEditText().addTextChangedListener(new TextListener(this, "peso"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public String getAlturaInserida() {
        return alturaInserida;
    }

    public void setAlturaInserida(String altura) {
        this.alturaInserida = altura;
    }

    public String getPesoInserido() {
        return pesoInserido;
    }

    public void setPesoInserido(String peso) {
        this.pesoInserido = peso;
    }

    public void setIMC(String imc) {
        IMC.getEditText().setText(imc);
    }
}
