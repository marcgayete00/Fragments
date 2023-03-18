package com.example.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class AdapterForeCast extends RecyclerView.Adapter<AdaptadorTexto.ViewHolder> {
    private String[] datos;

    public AdaptadorTexto(String[] datos) {
        this.datos = datos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_texto, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String texto = datos[position];
        holder.textoItem.setText(texto);
    }

    @Override
    public int getItemCount() {
        return datos.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textoItem;

        public ViewHolder(View vista) {
            super(vista);
            textoItem = vista.findViewById(R.id.textoItem);
        }
    }
}
