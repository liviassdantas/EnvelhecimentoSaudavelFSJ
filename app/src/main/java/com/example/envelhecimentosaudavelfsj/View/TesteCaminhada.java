package com.example.envelhecimentosaudavelfsj.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
    private Button btnProximo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_teste_caminhada);
        distancia = findViewById(R.id.teste_caminhada_distancia);
        vo2max = findViewById(R.id.teste_caminhada_vo2max);
        btnProximo = findViewById(R.id.teste_caminhada_btnProximo);
    }
}
