package com.example.envelhecimentosaudavelfsj.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.envelhecimentosaudavelfsj.R;
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
    private TextView IMC;
    private TextView IMCresult;
    private TextView RCQ;
    private TextView RCQresult;
    private Button btnProximo;

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
        IMC = findViewById(R.id.imc_rcqIMC);
        IMCresult = findViewById(R.id.imc_rcqIMCresultado);
        RCQ = findViewById(R.id.imc_rcqRCQ);
        RCQresult = findViewById(R.id.imc_rcqRCQresultado);
        btnProximo = findViewById(R.id.oximetria_btnProximo);

        findViewById(R.id.oximetria_btnProximo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (validarCampos()) {
                startActivity(new Intent(OximetriaAntropometria.this, DobrasCutaneas.class));
//                } else {
//                    Toast.makeText(getBaseContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
//                }
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
}
