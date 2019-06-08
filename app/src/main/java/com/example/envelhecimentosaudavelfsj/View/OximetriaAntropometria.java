package com.example.envelhecimentosaudavelfsj.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.envelhecimentosaudavelfsj.Model.Atendimento;
import com.example.envelhecimentosaudavelfsj.Model.Paciente;
import com.example.envelhecimentosaudavelfsj.R;
import com.example.envelhecimentosaudavelfsj.Util.TextListener;
import com.example.envelhecimentosaudavelfsj.Util.Util;
import com.google.gson.Gson;
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
    private Button btnProximo;

    private String alturaInserida;
    private String pesoInserido;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_oximetria_antro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Atendimento atendimento = new Atendimento();

        final Paciente pacienteG = new Gson().fromJson(
                getIntent().getStringExtra("paciente"), Paciente.class);

        vPreTeste = findViewById(R.id.oximetria_valor_pre_teste);
        vPosTeste = findViewById(R.id.oximetria_valor_pos_teste);
        peso = findViewById(R.id.antropometria_peso);
        altura = findViewById(R.id.antropometria_altura);
        pressaoDiastolica = findViewById(R.id.antropometria_pressaodiastolica);
        pressaoSistolica = findViewById(R.id.antropometria_pressaosistolica);
        frequeciaCardiaca = findViewById(R.id.antropometria_fc);
        IMC = findViewById(R.id.oximetria_imc);
        RCQedt = findViewById(R.id.oximetria_rcq_edit);
        btnProximo = findViewById(R.id.oximetria_btnProximo);

        findViewById(R.id.oximetria_btnProximo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()) {
                    atendimento.setOximetriaPre(vPreTeste.getEditText().getText().toString());
                    atendimento.setOximetriaPos(vPosTeste.getEditText().getText().toString());
                    atendimento.setPeso(Double.parseDouble(peso.getEditText().getText().toString()));
                    atendimento.setAltura(Double.parseDouble(altura.getEditText().getText().toString()));
                    atendimento.setPressaoSis(pressaoSistolica.getEditText().getText().toString());
                    atendimento.setPressaoDias(pressaoDiastolica.getEditText().getText().toString());
                    atendimento.setPressaoArterial(atendimento.PressaoArterial());
                    atendimento.setFrequenciaCardiaca(frequeciaCardiaca.getEditText().getText().toString());
                    atendimento.setIMC(IMC.getEditText().getText().toString());

                    Intent atendimentoOximetria = new Intent(OximetriaAntropometria.this, DobrasCutaneas.class);

                    String atendiOximetria = new Gson().toJson(atendimento);
                    String pacienteGs = new Gson().toJson(pacienteG);
                    atendimentoOximetria.putExtra("atendimentoOximetriaGson", atendiOximetria);
                    atendimentoOximetria.putExtra("pacienteGsonOxi", pacienteGs);

                    Log.v("teste1", "" + pacienteGs);

                    startActivity(atendimentoOximetria);
                } else {
                    Toast.makeText(getBaseContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }
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
                                //convertidas
                                cinturaRCQ = Double.parseDouble(cintura.getEditText().getText().toString());
                                quadrilRCQ = Double.parseDouble(quadril.getEditText().getText().toString());

                                atendimento.setRCQ(new Util().RCQ(cinturaRCQ, quadrilRCQ, pacienteG.getSexo(), pacienteG.getIdade()));

                                RCQedt.setText(atendimento.getRCQ());
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

    private boolean validarCampos() {
        return !(
                vPreTeste.getEditText().getText().toString().isEmpty() ||
                        vPosTeste.getEditText().getText().toString().isEmpty() ||
                        peso.getEditText().getText().toString().isEmpty() ||
                        altura.getEditText().getText().toString().isEmpty() ||
                        pressaoDiastolica.getEditText().getText().toString().isEmpty() ||
                        pressaoSistolica.getEditText().getText().toString().isEmpty() ||
                        frequeciaCardiaca.getEditText().getText().toString().isEmpty()
        );
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

    public void setRCQed(String rcq) {
        RCQedt.setText(rcq);
    }
}
