package com.example.envelhecimentosaudavelfsj.Util;

import android.text.Editable;
import android.text.TextWatcher;

import com.example.envelhecimentosaudavelfsj.View.OximetriaAntropometria;

import java.lang.ref.WeakReference;

public class TextListener implements TextWatcher {

    private WeakReference<OximetriaAntropometria> oximetriaAntropometriaWeakReference;
    private String elemento;

    public TextListener(OximetriaAntropometria activity, String elemento) {
        this.oximetriaAntropometriaWeakReference = new WeakReference<>(activity);
        this.elemento = elemento;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        if (elemento.equals("altura")) {
            oximetriaAntropometriaWeakReference.get().setAlturaInserida(s.toString());
        } else if (elemento.equals("peso")) {
            oximetriaAntropometriaWeakReference.get().setPesoInserido(s.toString());
        }

        if (oximetriaAntropometriaWeakReference.get().getAlturaInserida() != null &&
            !oximetriaAntropometriaWeakReference.get().getAlturaInserida().isEmpty() &&
            oximetriaAntropometriaWeakReference.get().getPesoInserido() != null &&
            !oximetriaAntropometriaWeakReference.get().getPesoInserido().isEmpty()) {

            Double altura = Double.parseDouble(oximetriaAntropometriaWeakReference.get().getAlturaInserida());
            Double peso = Double.parseDouble(oximetriaAntropometriaWeakReference.get().getPesoInserido());

            String imc = new Util().IMC(peso, altura);

            oximetriaAntropometriaWeakReference.get().setIMC(imc);
        }
    }
}
