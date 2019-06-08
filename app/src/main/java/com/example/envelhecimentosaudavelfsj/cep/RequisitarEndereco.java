package com.example.envelhecimentosaudavelfsj.cep;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.envelhecimentosaudavelfsj.Model.Endereco;
import com.example.envelhecimentosaudavelfsj.R;
import com.example.envelhecimentosaudavelfsj.View.DadosPaciente;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;

/**
 * Created by Raphael Rodrigues on 29/04/2019.
 */
public class RequisitarEndereco extends AsyncTask<Void, Void, Endereco> {

    private WeakReference<DadosPaciente> dadosPacienteWeakReference;

    public RequisitarEndereco(DadosPaciente activity) {
        this.dadosPacienteWeakReference = new WeakReference<>(activity);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Endereco doInBackground(Void... voids) {

        try {
            String jsonCep;
            jsonCep = JsonRequest.requesitarJson(dadosPacienteWeakReference.get().getUriCep());

            Gson gson = new Gson();

            return gson.fromJson(jsonCep, Endereco.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Endereco endereco) {
        super.onPostExecute(endereco);
        if (!checkConnection()) {
            Toast.makeText(dadosPacienteWeakReference.get(), "Sem internet", Toast.LENGTH_LONG).show();
        }

        if (endereco != null) {
            if (endereco.getCep() != null && dadosPacienteWeakReference.get() != null) {
                setDadosEndereco(endereco);
            } else {
                Toast.makeText(dadosPacienteWeakReference.get(), "Cep inválido", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void setDadosEndereco(Endereco endereco) {
        setCampos(R.id.endereco_edtCepId, endereco.getCep());
        setCampos(R.id.endereco_edtRuaId, endereco.getLogradouro());
        setCampos(R.id.endereco_edtNumeroId, endereco.getNumero());
        setCampos(R.id.endereco_edtBairroId, endereco.getBairro());
        setCampos(R.id.endereco_edtCidadeId, endereco.getLocalidade());
        setCampos(R.id.endereco_edtComplementoId, endereco.getComplemento());
        setSpinner(R.id.endereco_spinnerEstados, R.array.estados, endereco.getUf());
    }

    public void setCampos(int id, String data) {
        if (id == R.id.endereco_edtComplementoId) {
            ((EditText) dadosPacienteWeakReference.get().findViewById(id)).setText(data);
        } else {
            ((TextInputLayout) dadosPacienteWeakReference.get().findViewById(id)).getEditText().setText(data);
        }
    }

    private void setSpinner(int id, int arrayId, String data) {
        String[] arraySpinner = dadosPacienteWeakReference.get().getResources().getStringArray(arrayId);

        for (int i = 0; i < arraySpinner.length; i++) {
            if (data.equals(arraySpinner[i])) {
                ((Spinner) dadosPacienteWeakReference.get().findViewById(id)).setSelection(i);
                return;
            }
        }
        ((Spinner) dadosPacienteWeakReference.get().findViewById(id)).setSelection(0);
    }

    public boolean checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) dadosPacienteWeakReference.get().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
