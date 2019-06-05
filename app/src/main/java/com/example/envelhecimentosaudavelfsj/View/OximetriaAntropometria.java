package com.example.envelhecimentosaudavelfsj.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.envelhecimentosaudavelfsj.R;
/*
 *Created by: Livia Dantas - 05/06/2019
 */

public class OximetriaAntropometria extends AppCompatActivity {
    private EditText vPreTeste;
    private EditText vPosTeste;
    private EditText peso;
    private EditText altura;
    private EditText pressaoDiastolica;
    private EditText pressaoSistolica;
    private EditText frequeciaCardiaca;
    private TextView IMC;
    private TextView IMCresult;
    private TextView RCQ;
    private TextView RCQresult;
    private Button   btnProximo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_oximetria_antro);
        vPreTeste = findViewById(R.id.oximetria_valor_pre_teste);
        vPosTeste = findViewById(R.id.oximetria_valor_pos_teste);
        peso = findViewById(R.id.antropometria_peso);
        altura = findViewById(R.id.antropometria_altura);
        pressaoDiastolica = findViewById(R.id.antropometria_pressaodiastolica);
        pressaoSistolica = findViewById(R.id.antropometria_pressaosistolica);
        frequeciaCardiaca = findViewById(R.id.antropometria_fc);
        IMC = findViewById(R.id.imc_rcqIMC);
        IMCresult = findViewById(R.id.imc_rcqIMCresultado);
        RCQ = findViewById(R.id.imc_rcqRCQ);
        RCQresult = findViewById(R.id.imc_rcqRCQresultado);
        btnProximo = findViewById(R.id.oximetria_btnProximo);
    }
}
