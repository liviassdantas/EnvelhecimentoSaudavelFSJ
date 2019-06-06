package com.example.envelhecimentosaudavelfsj.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.envelhecimentosaudavelfsj.R;

/*
 *Created by: Livia Dantas - 05/06/2019
 */
public class DobrasCutaneas extends AppCompatActivity {
    private EditText dobraResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dobras_cutaneas);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        dobraResult = findViewById(R.id.dobras_cutaneas_valor);

        findViewById(R.id.dobras_cutaneas_btnProximo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DobrasCutaneas.this, TesteCaminhada.class));
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
