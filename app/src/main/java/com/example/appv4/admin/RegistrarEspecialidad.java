package com.example.appv4.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appv4.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class RegistrarEspecialidad extends AppCompatActivity {
    EditText editcodigo,editespeciaidad,editdescripcion;
    Button btnGuardar;

    private String codigo="",especialidad="",descripcion="";

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_especialidad);

        editcodigo=(EditText) findViewById(R.id.editcodigo);
        editespeciaidad=(EditText) findViewById(R.id.editespecialidad);
        editdescripcion=(EditText) findViewById(R.id.editdescripcion);
        btnGuardar=(Button) findViewById(R.id.btnguardarcita);

        iniciarFirebase();
        mAuth=FirebaseAuth.getInstance();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                codigo=editcodigo.getText().toString();
                especialidad=editespeciaidad.getText().toString();
                descripcion=editdescripcion.getText().toString();


               /* Especialidad obj= new Especialidad();
                obj.setCodigoE(codigo.toString());
                obj.setEspecialidad(especialidad.toString());
                obj.setDescripcionE(descripcion.toString());*/

                Especialidad obj2= new Especialidad();
                obj2.setId(UUID.randomUUID().toString());
                obj2.setCodigoE(codigo.toString());
                obj2.setEspecialidad(especialidad.toString());
                obj2.setDescripcionE(descripcion.toString());

               // databaseReference.child(especialidad).setValue(obj);
                databaseReference.child("Especialidad").child(obj2.getId()).setValue(obj2);
                Toast.makeText(RegistrarEspecialidad.this,especialidad+" Se registro correctamente",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private  void iniciarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }
}