package com.example.envelhecimentosaudavelfsj.View;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.envelhecimentosaudavelfsj.Model.Endereco;
import com.example.envelhecimentosaudavelfsj.Model.Paciente;
import com.example.envelhecimentosaudavelfsj.Principal;
import com.example.envelhecimentosaudavelfsj.R;
import com.example.envelhecimentosaudavelfsj.cep.CepListener;
import com.example.envelhecimentosaudavelfsj.daoSQLite.PacienteDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    private Long mCpfPaciente;


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
        Estado.setSelection(18);

        CEP.getEditText().addTextChangedListener(new CepListener(this));

        if (getIntent().hasExtra("CPF")) {
            mCpfPaciente = Long.parseLong(getIntent().getStringExtra("CPF"));
            CPF.getEditText().setText(String.valueOf(mCpfPaciente));
            CPF.getEditText().setEnabled(false);
        }

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
            @Override
            public void onClick(View v) {
                if (validarCampos(R.id.telaPaciente_nomePaciente, R.id.telaPaciente_cpf, R.id.telaPaciente_dataNascimento)) {

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
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt-BR"));
                    try {
                        paciente.setDataNascimento(sdf.parse(mDataNascimento.getText().toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    paciente.setIdade(paciente.getIdade());

                    endereco.setCpfPaciente(paciente.getCpf());
                    endereco.setBairro(Bairro.getEditText().getText().toString());
                    endereco.setCep(CEP.getEditText().getText().toString());
                    endereco.setComplemento(Complemento.getText().toString());
                    endereco.setLogradouro(Rua.getEditText().getText().toString());
                    endereco.setLocalidade(Cidade.getEditText().getText().toString());
                    endereco.setUf(Estado.getSelectedItem().toString());
                    endereco.setNumero(Numero.getEditText().getText().toString());
                    paciente.setEndereco(endereco);

                    if (new PacienteDAO(getBaseContext()).insertPaciente(paciente)) {
                        new AlertDialog.Builder(DadosPaciente.this)
                                .setCancelable(false)
                                .setTitle("Aviso")
                                .setMessage("Paciente cadastrado com sucesso. Deseja iniciar o atendimento?")
                                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(DadosPaciente.this, OximetriaAntropometria.class);
                                        intent.putExtra("CPF", CPF.getEditText().getText().toString());
                                        startActivity(intent);
                                        finish();
                                    }
                                })
                                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(DadosPaciente.this, Principal.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                })
                                .create()
                                .show();
                    } else {
                        new AlertDialog.Builder(DadosPaciente.this)
                                .setTitle("Erro")
                                .setCancelable(false)
                                .setMessage("Paciente não cadastrado")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(DadosPaciente.this, Principal.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                })
                                .create()
                                .show();
                    }

                    Log.i("BANCO", "onClick: " + paciente.getCpf() + " " + paciente.getEndereco().getLocalidade() + " " + paciente.getDataNascimento().toString());

                } else {
                    Toast.makeText(getBaseContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
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

    public boolean validarCampos(int... ids) {

        if (ids.length > 0) {
            boolean[] resultado = new boolean[ids.length];
            for (int i = 0; i < ids.length; i++) {
                resultado[i] = checkCampo(ids[i]);
            }

            for (boolean b : resultado) {
                if (!b) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean checkCampo(int id) {

        switch (id) {
            case R.id.telaPaciente_nomePaciente:
                if (Nome.getEditText().getText().toString().trim().isEmpty()) {
                    Nome.setError("Digite o nome do paciente");
                    return false;
                } else {
                    Nome.setError(null);
                    return true;
                }
            case R.id.telaPaciente_cpf:
                if (CPF.getEditText().getText().toString().trim().isEmpty()) {
                    CPF.setError("Digite o cpf do paciente");
                    return false;
                } else {
                    CPF.setError(null);
                    return true;
                }
            case R.id.telaPaciente_dataNascimento:
                if (mDataNascimento.getText().toString().trim().isEmpty()) {
                    mDataNascimento.setError("Informe a data de nascimento");
                    return false;
                } else {
                    mDataNascimento.setError(null);
                    return true;
                }
        }
        return true;
    }
}