package com.example.envelhecimentosaudavelfsj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.envelhecimentosaudavelfsj.View.DadosPaciente;
import com.example.envelhecimentosaudavelfsj.View.OximetriaAntropometria;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        findViewById(R.id.imgview_tela_inicial_cadastrar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Principal.this, DadosPaciente.class));
            }
        });
    }
}
