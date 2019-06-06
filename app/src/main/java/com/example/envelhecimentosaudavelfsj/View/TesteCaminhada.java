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

import com.example.envelhecimentosaudavelfsj.R;
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
    private String pressaoPre;
    private String pressaoPos;

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

        pressaoPre = PressaoPreTeste();
        pressaoPos = PressaoPosTeste();


        findViewById(R.id.teste_caminhada_btnProximo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TesteCaminhada.this, Laudo.class));
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

    public String PressaoPreTeste() {
        return this.paSistolicapreTeste + "x" + this.paDiastolicapreTeste + "mmHg";
    }

    public String PressaoPosTeste() {
        return this.paSistolicaposTeste + "x" + this.paDiastolicaposTeste + "mmHg";
    }
}
