package com.example.envelhecimentosaudavelfsj.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.envelhecimentosaudavelfsj.Model.Atendimento;
import com.example.envelhecimentosaudavelfsj.R;
import com.example.envelhecimentosaudavelfsj.daoSQLite.PacienteDAO;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@SuppressWarnings("ALL")
//Criado por Yan Vitor 06/06/2019

public class AtendimentoAdapter extends RecyclerView.Adapter<AtendimentoAdapter.CardViewRelatorio> {

    private Context mContext;
    private List<Atendimento> mAtendimentos;

    public AtendimentoAdapter(Context context, List<Atendimento> atendimentos) {
        this.mAtendimentos = atendimentos;
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

        final Atendimento atendimento = mAtendimentos.get(position);

        cardViewRelatorio.nomePaciente.setText(new PacienteDAO(mContext).getPacienteByCpf(atendimento.getCpfPaciente()).getNome());

        cardViewRelatorio.imc.append(atendimento.getIMC());

        String data = new SimpleDateFormat("dd/MM/yyyy HH:MM", new Locale("pt-BR")).format(atendimento.getDataEHoraAtendimento());

        cardViewRelatorio.dataAtendimento.append(data);
    }

    @Override
    public int getItemCount() {
        return mAtendimentos.size();
    }

    public static class CardViewRelatorio extends RecyclerView.ViewHolder {

        private TextView imc;
        private TextView nomePaciente;
        private TextView dataAtendimento;

        CardViewRelatorio(View itemView) {
            super(itemView);

            imc = itemView.findViewById(R.id.cardView_imc);
            nomePaciente = itemView.findViewById(R.id.cardView_paciente);
            dataAtendimento = itemView.findViewById(R.id.cardView_data);
        }
    }
}
