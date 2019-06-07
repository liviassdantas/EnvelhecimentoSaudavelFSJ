package com.example.envelhecimentosaudavelfsj.View;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.envelhecimentosaudavelfsj.Dao.AtendimentoDatabase;
import com.example.envelhecimentosaudavelfsj.Dao.PacienteAtendimento;
import com.example.envelhecimentosaudavelfsj.Model.Endereco;
import com.example.envelhecimentosaudavelfsj.Model.Paciente;
import com.example.envelhecimentosaudavelfsj.R;
import com.example.envelhecimentosaudavelfsj.cep.CepListener;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

//region
//Activity criada por Raphael Rodrigues
//Tela de cadastro dos pacientes
//endregion

public class DadosPaciente extends AppCompatActivity {

    private TextInputLayout Nome, CPF, Rua, Bairro, CEP, Numero, Cidade;
    private EditText mDataNascimento, Complemento;
    private Spinner Estado;
    private RadioButton btnFeminino;
    private RadioButton btnMasculino;


    private AtendimentoDatabase banco;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_paciente);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Nome = findViewById(R.id.telaPaciente_nomePaciente);
        CPF = findViewById(R.id.telaPaciente_cpf);
        Rua = findViewById(R.id.endereco_edtRuaId);
        Bairro = findViewById(R.id.endereco_edtBairroId);
        CEP = findViewById(R.id.endereco_edtCepId);
        Numero = findViewById(R.id.endereco_edtNumeroId);
        Cidade = findViewById(R.id.endereco_edtCidadeId);
        btnFeminino = findViewById(R.id.telaPaciente_sexoFeminino);
        btnMasculino = findViewById(R.id.telaPaciente_sexoMasculino);
        Complemento = findViewById(R.id.endereco_edtComplementoId);
        Estado = findViewById(R.id.endereco_spinnerEstados);


        CEP.getEditText().addTextChangedListener(new CepListener(this));

        banco = AtendimentoDatabase.getInstance(getApplicationContext());
        mDataNascimento = findViewById(R.id.telaPaciente_dataNascimento);

        mDataNascimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();

                if (!mDataNascimento.getText().toString().isEmpty()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy",
                            new Locale("pt-BR"));
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

        findViewById(R.id.telaPaciente_btnProximo).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SimpleDateFormat")
            @Override
            public void onClick(View v) {
                if (ValidarCampos()) {

                    Paciente paciente = new Paciente();
                    Endereco endereco = new Endereco();

                    paciente.setCpf(Long.parseLong(CPF.getEditText().getText().toString()));
                    paciente.setNome(Nome.getEditText().getText().toString());

                    //sexo
                    if (btnFeminino.isChecked()) {
                        paciente.setSexo("Feminino");
                    } else {
                        paciente.setSexo("Masculino");
                    }

                    //data de nascimento
                    try {
                        paciente.setDataNascimento(new SimpleDateFormat("dd/MM/YYYY").parse(mDataNascimento.getText().toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    paciente.setIdade(paciente.getIdade());


                    endereco.setBairro(Bairro.getEditText().getText().toString());
                    endereco.setCep(CEP.getEditText().getText().toString());
                    endereco.setComplemento(Complemento.getText().toString());
                    endereco.setLogradouro(Rua.getEditText().getText().toString());
                    endereco.setLocalidade(Cidade.getEditText().getText().toString());
                    endereco.setUf(Estado.getSelectedItem().toString());
                    endereco.setNumero(Numero.getEditText().toString());
                    paciente.setEndereco(endereco);

                    String pacienteGson = new Gson().toJson(paciente);
                    Intent intentPaciente = new Intent(DadosPaciente.this, OximetriaAntropometria.class);
                    intentPaciente.putExtra("paciente", pacienteGson);

                    startActivity(intentPaciente);

                } else {
                    Toast.makeText(getBaseContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean ValidarCampos() {
        return !(
                Nome.getEditText().getText().toString().isEmpty() ||
                        CPF.getEditText().getText().toString().isEmpty() ||
                        Rua.getEditText().getText().toString().isEmpty() ||
                        Bairro.getEditText().getText().toString().isEmpty() ||
                        CEP.getEditText().getText().toString().isEmpty() ||
                        Numero.getEditText().getText().toString().isEmpty() ||
                        Cidade.getEditText().getText().toString().isEmpty());
    }

    public String getUriCep() {
        return "https://viacep.com.br/ws/" + CEP.getEditText().getText() + "/json/";
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