package com.example.envelhecimentosaudavelfsj.Adapter;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.envelhecimentosaudavelfsj.Dao.AtendimentoDatabase;
import com.example.envelhecimentosaudavelfsj.Dao.Banco;
import com.example.envelhecimentosaudavelfsj.Dao.PacienteAtendimento;
import com.example.envelhecimentosaudavelfsj.Dao.PacienteDao;
import com.example.envelhecimentosaudavelfsj.Model.Atendimento;

import com.example.envelhecimentosaudavelfsj.Model.Paciente;
import com.example.envelhecimentosaudavelfsj.R;

import java.util.List;

@SuppressWarnings("ALL")
//Criado por Yan Vitor 06/06/2019

public class AtendimentoAdapter extends RecyclerView.Adapter<AtendimentoAdapter.CardViewRelatorio>
{

    private List<Atendimento> pacienteatendimentos;
    private AppCompatActivity activity;

    public AtendimentoAdapter(List<Atendimento> pacienteatendimentos, AppCompatActivity activity )
    {
        this.pacienteatendimentos = pacienteatendimentos;
        this.activity = activity;
    }

    @Override
    public int getItemCount() {
        return pacienteatendimentos.size();
    }

    @NonNull
    @Override
    public CardViewRelatorio onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_atendimento, viewGroup, false);

        return new CardViewRelatorio(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewRelatorio cardViewRelatorio,final int i) {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Paciente paciente = AtendimentoDatabase.getInstance(activity.getApplicationContext()).pacienteDao().getPacienteById(pacienteatendimentos.get(i).getCpfPaciente());
                cardViewRelatorio.txtNome.setText(paciente.getNome()); //Mudar para o nome do paciente

            }
        });

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class CardViewRelatorio extends RecyclerView.ViewHolder {

        CardView cv;
        TextView txtNome,txtIMC;

        CardViewRelatorio(View itemView) {
            super(itemView);

            cv = itemView.findViewById(R.id.cardView_atendimento);
            txtNome = itemView.findViewById(R.id.card_txt_nome);
            txtIMC = itemView.findViewById(R.id.card_txt_dataConsulta);

        }
    }
}
