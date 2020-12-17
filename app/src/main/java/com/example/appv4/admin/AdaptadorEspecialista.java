package com.example.appv4.admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appv4.R;

import java.util.ArrayList;

public class AdaptadorEspecialista extends RecyclerView.Adapter<AdaptadorEspecialista.ViewHolderDatos> {

    ArrayList<Especialidad> listdatos;


    public AdaptadorEspecialista(ArrayList<Especialidad> listdatos) {
        this.listdatos = listdatos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listespecialidad,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.txtcodigo.setText(listdatos.get(position).getCodigoE());
        holder.txtnombre.setText(listdatos.get(position).getEspecialidad());
        holder.txtdescripcion.setText(listdatos.get(position).getDescripcionE());
       // holder.foto.setImageResource(listdatos.get(position).getFoto());
    }

    @Override
    public int getItemCount() {
        return listdatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView txtnombre,txtdescripcion,txtcodigo;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            txtcodigo=(TextView) itemView.findViewById(R.id.txtCodigo);
            txtnombre=(TextView) itemView.findViewById(R.id.txtNombre);
            txtdescripcion=(TextView) itemView.findViewById(R.id.txtdescripcion);

        }

        /*public void asignardatos(String dato) {
            nombre.setText(dato);

        }*/
    }
}
