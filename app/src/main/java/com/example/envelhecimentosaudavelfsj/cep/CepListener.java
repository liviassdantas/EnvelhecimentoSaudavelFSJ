package com.example.envelhecimentosaudavelfsj.cep;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.envelhecimentosaudavelfsj.View.DadosPaciente;

/**
 * Created by Raphael Rodrigues on 29/04/2019.
 */
public class CepListener implements TextWatcher {

    private Activity mActivity;
    private String mCep;

    public CepListener(Activity activity) {
        this.mActivity = activity;
    }

    public CepListener(Activity activity, String cep) {
        this.mActivity = activity;
        this.mCep = cep;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String cep = s.toString();
        if (cep.length() == 8) {
            new RequisitarEndereco((DadosPaciente) mActivity).execute();
        }

    }
}
