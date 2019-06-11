package com.example.envelhecimentosaudavelfsj.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.example.envelhecimentosaudavelfsj.Model.Paciente;
import com.example.envelhecimentosaudavelfsj.R;
import com.google.gson.Gson;

/*
 *Created by: Livia Dantas - 05/06/2019
 */
public class DobrasCutaneas extends AppCompatActivity {

    private Paciente mPaciente;
    private TextInputLayout dobraResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dobras_cutaneas);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dobraResult = findViewById(R.id.dobras_cutaneas_valor);

        if (getIntent().hasExtra("PACIENTE")) {
            mPaciente = new Gson().fromJson(getIntent().getStringExtra("PACIENTE"), Paciente.class);
        }

        findViewById(R.id.dobras_cutaneas_btnProximo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPaciente.getAtendimentos().get(mPaciente.getAtendimentos().size() - 1)
                        .setDobrasCutaneas((!dobraResult.getEditText().getText().toString().isEmpty() ?
                                Double.parseDouble(dobraResult.getEditText().getText().toString()) : 0D));

                Intent intent = new Intent(DobrasCutaneas.this, TesteCaminhada.class);
                intent.putExtra("PACIENTE", new Gson().toJson(mPaciente));

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
