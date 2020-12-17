package com.example.appv4.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appv4.Activity.LoginActivity;
import com.example.appv4.R;

public class MenuAministrador extends AppCompatActivity {

    Button btndoctores,btnespecialidades;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_aministrador);

        btndoctores=(Button) findViewById(R.id.btnRDoctores);
        btnespecialidades=(Button) findViewById(R.id.btnREscpecialidades);

        btnespecialidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuAministrador.this, RegistrarEspecialidad.class);
                startActivity(intent);
            }
        });

        btndoctores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuAministrador.this, RegistarEspecialista.class);
                startActivity(intent);
            }
        });
    }
}