package com.example.appv4.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appv4.Activity.Usuario;
import com.example.appv4.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class RegistarEspecialista extends AppCompatActivity {

    private  static  final String[] ESCPECIALIDADES=new String[]{
            "Odontologia","General","Pediatra","Oftalmologia","Urologia","Angiologia"
    };


    EditText editdni,editnombre,editapellido,edittipoespecialidad,edittelefono,editcorreo,editcontrasena;
    private String dni="",nombre="",apellidos="",tiposespecialidad="",telefono="",correo="",contrasena="";

    //FIREBASE
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;

    AutoCompleteTextView comboTipoE;
    Button btnGuardar;
    String resultadoAutcompleted="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_especialista);

        //Para obtener los datos del arrar de values
        //String[] datos= getResources().getStringArray(R.array.elarray);

        editdni=(EditText) findViewById(R.id.editdniD);
        editnombre=(EditText) findViewById(R.id.editnombreD);
        editapellido=(EditText) findViewById(R.id.editapellidoD);
        //edittipoespecialidad=(EditText) findViewById(R.id.);
        edittelefono=(EditText) findViewById(R.id.edittelefonoD);
        editcorreo=(EditText) findViewById(R.id.editcorreoD);
        editcontrasena=(EditText) findViewById(R.id.editcontrasenaD);
        btnGuardar=(Button) findViewById(R.id.btnguardarD);
        iniciarFirebase();
        mAuth=FirebaseAuth.getInstance();

        //Adaptador para el  Autocomplete
        final AutoCompleteTextView edittext= findViewById(R.id.spinerTiposE);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,ESCPECIALIDADES);
        edittext.setAdapter(adapter);




        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edittext.showDropDown();
                resultadoAutcompleted=edittext.getText().toString();
                Toast.makeText(RegistarEspecialista.this,"dfdf"+resultadoAutcompleted,Toast.LENGTH_SHORT).show();

                registrar();

            }
        });
    }

    private void iniciarFirebase() {

        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }

    private  void registrar(){
        dni=editdni.getText().toString();
        nombre=editnombre.getText().toString();
        apellidos=editapellido.getText().toString();
        tiposespecialidad=resultadoAutcompleted.toString();
        telefono=edittelefono.getText().toString();
        correo=editcorreo.getText().toString()+"#@gmail.com";   //
        contrasena=editcontrasena.getText().toString();
        mAuth=FirebaseAuth.getInstance();
        Especialista obj= new Especialista();
        obj.setId(UUID.randomUUID().toString());
        obj.setDni(dni.toString());
        obj.setNommbre(nombre.toString());
        obj.setApellidos(apellidos.toString());
        obj.setTipoEspecialidad(tiposespecialidad.toString());
        obj.setTelefono(telefono.toString());
        obj.setCorreo(correo.toString());
        obj.setContrasena(contrasena.toString());

        Especialista obj2= new Especialista();
        //obj2.setId(UUID.randomUUID().toString());
        obj2.setDni(dni.toString());
        obj2.setNommbre(nombre.toString());
        obj2.setApellidos(apellidos.toString());
        obj2.setTipoEspecialidad(tiposespecialidad.toString());
        obj2.setTelefono(telefono.toString());
        obj2.setCorreo(correo.toString());
        obj2.setContrasena(contrasena.toString());

        databaseReference.child("Especialista").child(obj.getId()).setValue(obj);
        databaseReference.child(tiposespecialidad).setValue(obj2);

         mAuth.createUserWithEmailAndPassword(correo,contrasena);

        Toast.makeText(RegistarEspecialista.this,"Se registro correctamente",Toast.LENGTH_SHORT).show();
    }
}