package com.example.envelhecimentosaudavelfsj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.envelhecimentosaudavelfsj.View.DadosPaciente;
import com.example.envelhecimentosaudavelfsj.View.Relatorio;

public class Principal extends AppCompatActivity {

    private AlertDialog alertaConsultaCpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

    }

    public void btnNovoPaciente(View v) {
        startActivity(new Intent(Principal.this, DadosPaciente.class));
    }

    public void btnRelatorio(View v) {

//        final View view = getLayoutInflater().inflate(R.layout.alert_consulta_cpf, null);
//
//        alertaConsultaCpf = new AlertDialog.Builder(Principal.this)
//                .setTitle("Buscar paciente")
//                .setView(view)
//                .create();
//
//        view.findViewById(R.id.alert_consulta_btnCancelar).setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                alertaConsultaCpf.dismiss();
//            }
//        });
//
//        view.findViewById(R.id.alert_consulta_btnOK).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String cpf = ((EditText) view.findViewById(R.id.alert_txt_cpf)).getText().toString();
//
//                if (cpf.length() == 11) {
//                    Intent i = new Intent(Principal.this, Relatorio.class);
//                    i.putExtra("cpf", cpf);
//                    startActivity(i);
//                } else {
//                    Toast.makeText(Principal.this, "Insira um CPF válido", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        alertaConsultaCpf.show();
    }
}
