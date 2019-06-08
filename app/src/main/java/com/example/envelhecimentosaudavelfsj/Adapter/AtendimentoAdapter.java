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
import com.example.envelhecimentosaudavelfsj.Model.Atendimento;
import com.example.envelhecimentosaudavelfsj.Model.Paciente;
import com.example.envelhecimentosaudavelfsj.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@SuppressWarnings("ALL")
//Criado por Yan Vitor 06/06/2019

public class AtendimentoAdapter extends RecyclerView.Adapter<AtendimentoAdapter.CardViewRelatorio> {

    private List<Atendimento> pacienteatendimentos;
    private AppCompatActivity activity;
    String nomeGeral;
    String IMCGeral;
    String DataGeral;

    public AtendimentoAdapter(List<Atendimento> pacienteatendimentos, AppCompatActivity activity) {
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
    public void onBindViewHolder(@NonNull final CardViewRelatorio cardViewRelatorio, final int i) {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                final Paciente paciente = AtendimentoDatabase.getInstance(activity.getApplicationContext()).pacienteDao().getPacienteById(pacienteatendimentos.get(i).getCpfPaciente());
                final List<Atendimento> atendimento = AtendimentoDatabase.getInstance(activity.getApplicationContext()).atendimentoDao().getByPaciente(pacienteatendimentos.get(i).getCpfPaciente());

                if (paciente != null && atendimento != null) {

                    if (atendimento.size() > 0) {

                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                String nome = paciente.getNome();
                                nomeGeral = nome;

                                if (atendimento != null) {
                                    String IMC = atendimento.get(i).getIMC();
                                    IMCGeral = IMC;

                                    DataGeral = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt-BR")).format(atendimento.get(i).getDataEHoraAtendimento());

                                }

                                if (IMCGeral == null) {
                                    IMCGeral = " ";
                                }

                                cardViewRelatorio.txtNome.append(nomeGeral);
                                cardViewRelatorio.txtIMC.append(IMCGeral);
                                cardViewRelatorio.txtData.append(DataGeral);
                            }
                        });
                    }
                }
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class CardViewRelatorio extends RecyclerView.ViewHolder {

        CardView cv;
        TextView txtNome, txtIMC, txtData;

        CardViewRelatorio(View itemView) {
            super(itemView);

            cv = itemView.findViewById(R.id.card);
            txtNome = itemView.findViewById(R.id.cardView_paciente);
            txtIMC = itemView.findViewById(R.id.cardView_imc);
            txtData = itemView.findViewById(R.id.cardView_data);

        }
    }
}
