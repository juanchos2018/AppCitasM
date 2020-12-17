package com.example.appv4.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appv4.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.ArrayList;
import java.util.UUID;

public class RegistroActivity extends AppCompatActivity {

    EditText editdni,editnombre,editapellidos,edittelefono,editcorreo,editcontrasena;
    Button btnRegistrar;

    private String dni="",nombre="",apellido="",telfono="",correo="",contrsena="";

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
   // FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private DatabaseReference reference;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    ArrayList<String> listacargo;
    ArrayAdapter<String> adapterspiner;

    Spinner spinercargo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        editdni= (EditText) findViewById(R.id.editdniC);
        editnombre= (EditText) findViewById(R.id.editnombreC);
        editapellidos= (EditText) findViewById(R.id.editapellidoC);
        edittelefono= (EditText) findViewById(R.id.edittelefonoC);
        editcorreo= (EditText) findViewById(R.id.editcorreoC);
        editcontrasena= (EditText) findViewById(R.id.editcontrsenaC);
        btnRegistrar=(Button) findViewById(R.id.btnRegistrarC);
        spinercargo=(Spinner) findViewById(R.id.spinercargo);
        listacargo = new ArrayList<>();
        listacargo.add("Administrador");
        listacargo.add("Doctor");
        listacargo.add("Paciente");

        adapterspiner= new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,listacargo);
        spinercargo.setAdapter(adapterspiner);

        iniciarFirebase();
        mAuth=FirebaseAuth.getInstance();

        user = mAuth.getCurrentUser();
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                dni=editdni.getText().toString();
                nombre=editnombre.getText().toString();
                apellido=editapellidos.getText().toString();
                telfono=edittelefono.getText().toString();
                correo=editcorreo.getText().toString();
                contrsena=editcontrasena.getText().toString();
                registrar(dni,nombre,apellido,correo,contrsena,"Doctor");
                //if (dni.equals("") )
                /*
                Usuario obj= new Usuario();
                obj.setId(UUID.randomUUID().toString());
                obj.setDni(dni.toString());
                obj.setNombre(nombre.toString());
                obj.setApellido(apellido.toString());
                obj.setTelefono(telfono.toString());
                obj.setCorreo(correo.toString());
                obj.setContrasena(contrsena.toString());
                databaseReference.child("Usuario").child(obj.getId()).setValue(obj);

                mAuth.createUserWithEmailAndPassword(correo,contrsena);


                 */
            }
        });
    }

    private void registrar(final String dni, final String nombres, final String apellidos, final String correo, String clave, final String cargo) {

        if (TextUtils.isEmpty(nombres)){
            editnombre.setError("campo requerido");
        }
        else if (TextUtils.isEmpty(apellidos)){
            editapellidos.setError("campo requerido");
        }
        else if (TextUtils.isEmpty(correo)){
            editcorreo.setError("campo requerido");
        }
        else if(TextUtils.isEmpty(clave)){
            editcontrasena.setError("campo requerido");
        }
        else if (TextUtils.isEmpty(cargo)){
            Toast.makeText(this, "falta cargo", Toast.LENGTH_SHORT).show();
        }
        else{
            progressDialog =new ProgressDialog(this);
            progressDialog.setTitle("Creando Cuenta");
            progressDialog.setMessage("Espera We ....");
            progressDialog.show();
            progressDialog.setCanceledOnTouchOutside(false);

            mAuth.createUserWithEmailAndPassword(correo,clave).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        String current_userID =  mAuth.getCurrentUser().getUid();
                        reference = FirebaseDatabase.getInstance().getReference("Usuario").child(current_userID);
                        reference.child("id").setValue(current_userID);
                        reference.child("dni").setValue(dni);
                        reference.child("nombre").setValue(nombres);
                        reference.child("apellido").setValue(apellidos);
                        reference.child("correo").setValue(correo);
                        reference.child("cargo").setValue(cargo);
                   //     reference.child("image_usuario").setValue("defult_image");
                        reference.child("created_at").setValue(ServerValue.TIMESTAMP) .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    user=mAuth.getCurrentUser();
                                    if (user!=null){
                                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){
                                                    progressDialog.dismiss();
                                                 //   mensajeverfica();
                                                    //  Toast.makeText(Registro.this, "Registrado", Toast.LENGTH_SHORT).show();
                                                }else{
                                                    mAuth.signOut();
                                                }
                                            }
                                        });
                                    }
                                }
                            }
                        });
                    }else {
                        String message = task.getException().getMessage();

                        Toast.makeText(RegistroActivity.this, "Error occurred : " + message, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                }
            });
        }
    }

    private void iniciarFirebase() {

        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }
}