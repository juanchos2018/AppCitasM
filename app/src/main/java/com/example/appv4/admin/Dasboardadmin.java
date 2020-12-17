package com.example.appv4.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.appv4.DasboardActivity;
import com.example.appv4.R;

public class Dasboardadmin extends AppCompatActivity {

    ImageView imvRegistrarEspecialidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dasboardadmin);

        imvRegistrarEspecialidad=(ImageView) findViewById(R.id.idivmRegistroEpecialidad);

        imvRegistrarEspecialidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dasboardadmin.this, RegistrarEspecialidad.class);
                startActivity(intent);
            }
        });
    }
}