package com.example.envelhecimentosaudavelfsj;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.envelhecimentosaudavelfsj.Dao.Banco;
import com.example.envelhecimentosaudavelfsj.Model.Atendimento;
import com.example.envelhecimentosaudavelfsj.Util.PDF;
import com.example.envelhecimentosaudavelfsj.View.DadosPaciente;
import com.example.envelhecimentosaudavelfsj.View.Relatorio;

import java.util.Date;

public class Principal extends AppCompatActivity {

    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
    }

    public void btnNovoPaciente(View v) {
        startActivity(new Intent(Principal.this, DadosPaciente.class));
    }

    public void btnRelatorio(View v) {

        LayoutInflater li = getLayoutInflater();

        final View view = li.inflate(R.layout.alert_consulta_cpf, null);

        view.findViewById(R.id.alert_btn_cancelar).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                alerta.dismiss(); //cancelar o alert dialog
            }
        });

        view.findViewById(R.id.alert_btn_pesquisar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cpf = ((EditText) view.findViewById(R.id.alert_txt_cpf)).getText().toString();

                if (cpf.length() == 11) {
                    Intent i = new Intent(Principal.this, Relatorio.class);
                    i.putExtra("cpf", cpf);
                    startActivity(i);
                } else {
                    Toast.makeText(Principal.this, "Insira um CPF correto", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Consulta");
        builder.setView(view);
        alerta = builder.create();

        alerta.show();

    }

}
