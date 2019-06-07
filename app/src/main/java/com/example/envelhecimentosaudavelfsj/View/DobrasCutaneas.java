package com.example.envelhecimentosaudavelfsj.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.envelhecimentosaudavelfsj.Model.Atendimento;
import com.example.envelhecimentosaudavelfsj.Model.Paciente;
import com.example.envelhecimentosaudavelfsj.R;
import com.google.gson.Gson;

/*
 *Created by: Livia Dantas - 05/06/2019
 */
public class DobrasCutaneas extends AppCompatActivity {
    private TextInputLayout dobraResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dobras_cutaneas);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dobraResult = findViewById(R.id.dobras_cutaneas_valor);

        findViewById(R.id.dobras_cutaneas_btnProximo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dobraResult.getEditText().getText().toString().isEmpty()) {
                    Atendimento atendDobras = new Gson().fromJson(getIntent()
                            .getStringExtra("atendimentoOximetriaGson"), Atendimento.class);
                    Paciente pacienteDobras = new Gson().fromJson(getIntent()
                            .getStringExtra("pacienteGsonOxi"),Paciente.class);

                    atendDobras.setDobrasCutaneas(Double.parseDouble(dobraResult.getEditText().getText().toString()));

                    String atendimentoDobras = new Gson().toJson(atendDobras);
                    String pacienteDobrasCut = new Gson().toJson(pacienteDobras);
                    Intent atendDobrasCut = new Intent(DobrasCutaneas.this, TesteCaminhada.class);

                    atendDobrasCut.putExtra("atendimentoDobras",atendimentoDobras);
                    atendDobrasCut.putExtra("pacientedobras",pacienteDobrasCut);

                    Log.v("dobras"," "+pacienteDobrasCut);
                    startActivity(atendDobrasCut);
                } else {
                    Toast.makeText(getBaseContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }

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
