package com.rado.piero.notesapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rado.piero.notesapp.R;
import com.rado.piero.notesapp.models.Nota;

import java.lang.reflect.Type;
import java.util.List;

public class NotaAdapter extends RecyclerView.Adapter<NotaAdapter.ViewHolder>  {
    private List<Nota> notas;

    public NotaAdapter(List<Nota> notas){
        this.notas = notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView titulo;
        public TextView contenido;

        public ViewHolder(View itemView) {
            super(itemView);

            titulo = (TextView) itemView.findViewById(R.id.titulo_text);
            contenido = (TextView) itemView.findViewById(R.id.contenido_text);
        }
    }

    @NonNull
    @Override
    public NotaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nota, parent, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull NotaAdapter.ViewHolder viewHolder, int position) {
        Nota nota = this.notas.get(position);
        viewHolder.titulo.setText(nota.getTitulo());
        viewHolder.contenido.setText(nota.getContenido());


    }

    @Override
    public int getItemCount() {
        return this.notas.size();
    }
}
