package com.example.envelhecimentosaudavelfsj.View;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.envelhecimentosaudavelfsj.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

//region
//Activity criada por Raphael Rodrigues
//Tela de cadastro dos pacientes
//endregion

public class DadosPaciente extends AppCompatActivity {

    private EditText mDataNascimento , Nome , CPF , Rua, Bairro , CEP , Numero , Cidade ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_paciente);

        Nome = findViewById(R.id.txt_nome);
        CPF = findViewById(R.id.txt_cpf);
        Rua = findViewById(R.id.txt_rua);
        Bairro = findViewById(R.id.txt_bairro);
        CEP = findViewById(R.id.txt_cep);
        Numero = findViewById(R.id.txt_numero);
        Cidade = findViewById(R.id.txt_cidade);

        mDataNascimento = findViewById(R.id.cadastro_dataNascimento);

        mDataNascimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();

                if (!mDataNascimento.getText().toString().isEmpty()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt-BR"));
                    try {
                        calendar.setTime(sdf.parse(mDataNascimento.getText().toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                new DatePickerDialog(DadosPaciente.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar c = Calendar.getInstance();
                        c.set(year, month, dayOfMonth);
                        String data = new SimpleDateFormat("dd/MM/YYYY", new Locale("pt-BR")).format(c.getTime());
                        mDataNascimento.setText(data);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
    }

    private boolean ValidarCampos()
    {
        return !(
            Nome.getText().toString().isEmpty() || CPF.getText().toString().isEmpty() || Rua.getText().toString().isEmpty()
            || Bairro.getText().toString().isEmpty() || CEP.getText().toString().isEmpty() || Numero.getText().toString().isEmpty()
            || Cidade.getText().toString().isEmpty()
        );

    }

    public void btnProximo(View v)
    {
        if(ValidarCampos())
        {
            startActivity(new Intent(DadosPaciente.this,OximetriaAntropometria.class));
        }
        else
        {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }
    }
}