package com.example.envelhecimentosaudavelfsj.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
    private EditText distancia;
    private EditText vo2max;
    private EditText pressaoArterialpreTeste;
    private EditText pressaoArterialposTeste;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_teste_caminhada);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        distancia = findViewById(R.id.teste_caminhada_distancia);
//        vo2max = findViewById(R.id.teste_caminhada_vo2max);


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
}
