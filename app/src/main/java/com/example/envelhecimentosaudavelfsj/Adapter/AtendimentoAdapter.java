package com.example.envelhecimentosaudavelfsj.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.envelhecimentosaudavelfsj.Model.Atendimento;

import com.example.envelhecimentosaudavelfsj.R;

import java.util.List;

//Criado por Yan Vitor 06/06/2019

public class AtendimentoAdapter extends RecyclerView.Adapter<AtendimentoAdapter.CardViewRelatorio>
{

    List<Atendimento> atendimentos;

    public AtendimentoAdapter(List<Atendimento> atendimentos)
    {
        this.atendimentos = atendimentos;
    }

    @Override
    public int getItemCount() {
        return atendimentos.size();
    }

    @NonNull
    @Override
    public CardViewRelatorio onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_atendimento, viewGroup, false);

        return new CardViewRelatorio(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewRelatorio cardViewRelatorio, int i) {

        cardViewRelatorio.txtNome.setText(atendimentos.get(i).getCpfPaciente()); //Mudar para o nome do paciente
        cardViewRelatorio.txtData.setText(atendimentos.get(i).getDataEHoraAtendimento().toString());

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class CardViewRelatorio extends RecyclerView.ViewHolder {

        CardView cv;
        TextView txtNome,txtData;

        CardViewRelatorio(View itemView) {
            super(itemView);

            cv = itemView.findViewById(R.id.cardView_atendimento);
            txtNome = itemView.findViewById(R.id.card_txt_nome);
            txtData = itemView.findViewById(R.id.card_txt_data);

        }
    }
}
