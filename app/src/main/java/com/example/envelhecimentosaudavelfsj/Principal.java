package com.example.envelhecimentosaudavelfsj;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.envelhecimentosaudavelfsj.View.DadosPaciente;
import com.example.envelhecimentosaudavelfsj.View.OximetriaAntropometria;
import com.example.envelhecimentosaudavelfsj.View.Relatorio;
import com.example.envelhecimentosaudavelfsj.daoSQLite.PacienteDAO;

public class Principal extends AppCompatActivity {

    private AlertDialog alertaConsultaCpf;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        new PacienteDAO(getBaseContext()).getAllPacientes();

//        findViewById(R.id.imgview_tela_inicial_realizarAtendimento).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Principal.this, OximetriaAntropometria.class));
//            }
//        });
    }

    public void btnNovoPaciente(View v) {
        startActivity(new Intent(Principal.this, DadosPaciente.class));
    }

    public void btnAtendimento(View v) {

        final View view = getLayoutInflater().inflate(R.layout.alert_consulta_cpf, null);

        alertaConsultaCpf = new AlertDialog.Builder(Principal.this)
                .setTitle("Buscar paciente")
                .setView(view)
                .create();

        view.findViewById(R.id.alert_consulta_btnCancelar).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alertaConsultaCpf.dismiss();
            }
        });

        view.findViewById(R.id.alert_consulta_btnOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String cpf = ((EditText) view.findViewById(R.id.alert_txt_cpf)).getText().toString();

                if (cpf.length() == 11) {
                    if (new PacienteDAO(getBaseContext()).getPacienteByCpf(Long.parseLong(cpf)) == null) {

                        new AlertDialog.Builder(Principal.this)
                                .setTitle("Aviso")
                                .setMessage("Paciente não encontrado. Deseja cadastra - lo?")
                                .setNegativeButton("Cancelar", null)
                                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent i = new Intent(Principal.this, DadosPaciente.class);
                                        i.putExtra("CPF", cpf);
                                        startActivity(i);
                                        alertaConsultaCpf.dismiss();
                                    }
                                })
                                .create()
                                .show();
                    } else {
                        Intent i = new Intent(Principal.this, OximetriaAntropometria.class);
                        i.putExtra("CPF", cpf);
                        startActivity(i);
                    }

                } else {
                    Toast.makeText(Principal.this, "Insira um CPF válido", Toast.LENGTH_LONG).show();
                }
            }
        });

        alertaConsultaCpf.show();
    }

    public void btnRelatorio(View v) {

        final View view = getLayoutInflater().inflate(R.layout.alert_consulta_cpf, null);

        alertaConsultaCpf = new AlertDialog.Builder(Principal.this)
                .setTitle("Buscar paciente")
                .setView(view)
                .create();

        view.findViewById(R.id.alert_consulta_btnCancelar).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alertaConsultaCpf.dismiss();
            }
        });

        view.findViewById(R.id.alert_consulta_btnOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cpf = ((EditText) view.findViewById(R.id.alert_txt_cpf)).getText().toString();

                if (cpf.length() == 11) {
                    if (new PacienteDAO(getBaseContext()).getPacienteByCpf(Long.parseLong(cpf)) == null) {
                        Toast.makeText(Principal.this, "Paciente não encotrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent i = new Intent(Principal.this, Relatorio.class);
                        i.putExtra("CPF", cpf);
                        startActivity(i);
                        alertaConsultaCpf.dismiss();
                    }
                } else {
                    Toast.makeText(Principal.this, "Insira um CPF válido", Toast.LENGTH_SHORT).show();
                }
            }
        });

        alertaConsultaCpf.show();
    }
}
