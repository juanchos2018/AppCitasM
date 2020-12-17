package com.example.appv4.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;

import com.example.appv4.Activity.LoginActivity;
import com.example.appv4.R;
import com.example.appv4.adapter.ListaEspeadapter;
import com.example.appv4.model.ClsRegistrarEspecialidad;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListaEspecialidad extends AppCompatActivity {


    ArrayList<Especialidad> listdatos;
    RecyclerView recyclerView;
    AdaptadorEspecialistaFb myadapter;

    //DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_especialidad);

       // mDatabase=FirebaseDatabase.getInstance().getReference();
        listdatos= new ArrayList<>();
        recyclerView=(RecyclerView) findViewById(R.id.idlistaEspecialidad);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Especialidad> option=
                new FirebaseRecyclerOptions.Builder<Especialidad>()
                    .setQuery(FirebaseDatabase.getInstance().getReference().child("Especialidad"),Especialidad.class)
                        .build();

        myadapter= new AdaptadorEspecialistaFb(option);

        myadapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaEspecialidad.this, ListaEspecialista.class);
                startActivity(intent);
            }
        });
        /*llenarLista();
        AdaptadorEspecialista adapter=new AdaptadorEspecialista(listdatos);*/
        recyclerView.setAdapter(myadapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        myadapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();

        myadapter.stopListening();
    }

    private void llenarLista() {
        listdatos.add(new Especialidad("12345","odontologia","trata de dientes"));
        listdatos.add(new Especialidad("22345","pediatra","trata de pediatra"));
        listdatos.add(new Especialidad("32345","general","trata de general"));
        listdatos.add(new Especialidad("42345","otoriino","trata de otoriino"));
        listdatos.add(new Especialidad("12345","odontologia","trata de dientes"));
        listdatos.add(new Especialidad("22345","pediatra","trata de pediatra"));
        listdatos.add(new Especialidad("32345","general","trata de general"));
        listdatos.add(new Especialidad("42345","otoriino","trata de otoriino"));
        listdatos.add(new Especialidad("12345","odontologia","trata de dientes"));
        listdatos.add(new Especialidad("22345","pediatra","trata de pediatra"));
        listdatos.add(new Especialidad("32345","general","trata de general"));
        listdatos.add(new Especialidad("42345","otoriino","trata de otoriino"));
        listdatos.add(new Especialidad("12345","odontologia","trata de dientes"));
        listdatos.add(new Especialidad("22345","pediatra","trata de pediatra"));
        listdatos.add(new Especialidad("32345","general","trata de general"));
        listdatos.add(new Especialidad("42345","otoriino","trata de otoriino"));
    }
}