package com.example.appv4.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appv4.R;
import com.example.appv4.admin.ListaEspecialidad;
import com.example.appv4.model.ClsRegistrarEspecialidad;

import java.util.ArrayList;
import java.util.List;

public class ListaEspeadapter extends RecyclerView.Adapter<ListaEspeadapter.EspecialidadHolder>{

    List<ClsRegistrarEspecialidad> DatosEspecialidad;

    public ListaEspeadapter(List<ClsRegistrarEspecialidad> datosEspecialidad) {
        DatosEspecialidad = datosEspecialidad;

    }

    @NonNull
    @Override
    public EspecialidadHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_news, parent, false);
        EspecialidadHolder holder = new EspecialidadHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EspecialidadHolder holder, int position) {
        ClsRegistrarEspecialidad datosEspe = DatosEspecialidad.get(position);
        holder.textViewNom.setText(datosEspe.getNombreAREA());
        holder.textViewDescrip.setText(datosEspe.getDescripcionAREA());
    }

    @Override
    public int getItemCount() {
        return DatosEspecialidad.size();
    }

    public static class EspecialidadHolder extends RecyclerView.ViewHolder{

        TextView textViewNom, textViewDescrip;

        public EspecialidadHolder(@NonNull View itemView) {
            super(itemView);
            textViewNom = (TextView) itemView.findViewById(R.id.idusername);
            textViewDescrip = (TextView) itemView.findViewById(R.id.iddescripcion);
        }
    }
}
