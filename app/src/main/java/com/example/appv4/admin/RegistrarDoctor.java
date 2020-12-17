package com.example.appv4.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.appv4.MainActivity;
import com.example.appv4.R;
import com.google.android.material.textfield.TextInputLayout;

import java.lang.reflect.Array;

public class RegistrarDoctor extends AppCompatActivity {

    TextInputLayout textInputLayout;
    AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_doctor);

        textInputLayout = (TextInputLayout)findViewById(R.id.idLtipoEspecialidad);
        autoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.idTipoEspecialidad);

        //crear array de nombres/iitems para el adaptador del autocompletetexview
        String[] nombres = new String[]{
                "Obstetricia",
                "Ginecologia",
                "Cardiologia",
                "Dentista",
                "Traumologia",
                "Pediatra Neumatologia",
                "Cirugia General",
                "Medicina General"
        };
        //crear arrayadapter y configurarlo
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                RegistrarDoctor.this,
                R.layout.dropdown_item,
                nombres
        );
        //Setear el adapter al autocompletetextview
        autoCompleteTextView.setAdapter(adapter);
    }
}