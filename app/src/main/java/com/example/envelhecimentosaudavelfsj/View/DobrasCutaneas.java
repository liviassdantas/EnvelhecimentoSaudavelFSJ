package com.example.envelhecimentosaudavelfsj.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.envelhecimentosaudavelfsj.R;

/*
 *Created by: Livia Dantas - 05/06/2019
 */
public class DobrasCutaneas extends AppCompatActivity
{
    private EditText dobraResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dobras_cutaneas);

        dobraResult = findViewById(R.id.dobras_cutaneas_valor);
    }
    
    public void btnProximo(View v)
    {
        if(!dobraResult.getText().toString().isEmpty())
        {
            startActivity(new Intent(DobrasCutaneas.this,Laudo.class));
        }
        else
        {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
    }
}
