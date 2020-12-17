package com.example.appv4.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.appv4.R;

public class ListaEspecialidad2 extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_especialidad2);

        recyclerView=(RecyclerView)findViewById(R.id.idrecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}