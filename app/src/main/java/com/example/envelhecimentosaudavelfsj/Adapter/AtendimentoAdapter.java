package com.example.envelhecimentosaudavelfsj.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.envelhecimentosaudavelfsj.Model.Atendimento;
import com.example.envelhecimentosaudavelfsj.Model.Paciente;
import com.example.envelhecimentosaudavelfsj.R;
import com.example.envelhecimentosaudavelfsj.View.Laudo;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Locale;

@SuppressWarnings("ALL")
//Criado por Yan Vitor 06/06/2019

public class AtendimentoAdapter extends RecyclerView.Adapter<AtendimentoAdapter.CardViewRelatorio> {

    private Context mContext;
    private Paciente mPaciente;

    public AtendimentoAdapter(Context context, Paciente paciente) {
        this.mPaciente = paciente;
        this.mContext = context;
    }

    @NonNull
    @Override
    public CardViewRelatorio onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_atendimento, viewGroup, false);

        return new CardViewRelatorio(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewRelatorio cardViewRelatorio, final int position) {

        final Atendimento atendimento = mPaciente.getAtendimentos().get(position);

        cardViewRelatorio.nomePaciente.setText(mPaciente.getNome());

        cardViewRelatorio.imc.append((!atendimento.getIMC().isEmpty() ? atendimento.getIMC().substring(0, 5) : ""));

        String data = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("pt-BR")).format(atendimento.getDataEHoraAtendimento());

        cardViewRelatorio.dataAtendimento.append(data);

        cardViewRelatorio.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Laudo.class);
                intent.putExtra("PACIENTE_ADAPTER", new Gson().toJson(mPaciente));
                intent.putExtra("POSITION", position);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPaciente.getAtendimentos().size();
    }

    public static class CardViewRelatorio extends RecyclerView.ViewHolder {

        private View view;
        private TextView imc;
        private TextView nomePaciente;
        private TextView dataAtendimento;

        CardViewRelatorio(View itemView) {
            super(itemView);

            view = itemView;
            imc = itemView.findViewById(R.id.cardView_imc);
            nomePaciente = itemView.findViewById(R.id.cardView_paciente);
            dataAtendimento = itemView.findViewById(R.id.cardView_data);
        }
    }
}
