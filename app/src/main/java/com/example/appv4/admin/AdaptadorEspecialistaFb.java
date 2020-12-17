package com.example.appv4.admin;

import android.content.ClipData;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appv4.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class AdaptadorEspecialistaFb  extends FirebaseRecyclerAdapter<Especialidad,AdaptadorEspecialistaFb.myviewholder> implements View.OnClickListener {

    public AdaptadorEspecialistaFb(@NonNull FirebaseRecyclerOptions<Especialidad> options) {
        super(options);

    }

  private List<Especialidad> items;
 /*
    public AdaptadorEspecialistaFb(@NonNull FirebaseRecyclerOptions<Especialidad> options, List<Especialidad> items) {
        super(options);
        this.items = items;
    }
*/
    private  View.OnClickListener listener;

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, int position, @NonNull Especialidad model) {

        //final Especialidad item= items.get(position);

        holder.txtcodigo.setText(model.getCodigoE());
        holder.txtnombre.setText(model.getEspecialidad());
        holder.txtdescripcion.setText(model.getDescripcionE());

        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(holder.itemView.getContext(),ListaEspecialista.class);
                intent.putExtra("ItemDetail",item);
                holder.itemView.getContext().startActivity(intent);
            }
        });*/
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listespecialidad,null,false);

        view.setOnClickListener(this);

        return new myviewholder(view);
    }

    public  void  setOnClickListener (View.OnClickListener listener){
        this.listener=listener;
    }
    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }

    class myviewholder extends RecyclerView.ViewHolder{

        TextView txtnombre,txtdescripcion,txtcodigo;
        public myviewholder(@NonNull View itemView) {
            super(itemView);

            txtcodigo=(TextView) itemView.findViewById(R.id.txtCodigo);
            txtnombre=(TextView) itemView.findViewById(R.id.txtNombre);
            txtdescripcion=(TextView) itemView.findViewById(R.id.txtdescripcion);

        }
    }
}
