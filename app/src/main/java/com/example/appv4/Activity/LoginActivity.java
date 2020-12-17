package com.example.appv4.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appv4.DasboardActivity;
import com.example.appv4.R;
import com.example.appv4.admin.MenuAministrador;
import com.example.appv4.admin.MenuEspecialista;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    Button btnIngresar, btnRegistrar, btnRecuperar,btnAdministrador;
    EditText editusuario,editclave;
    String usuario,clave;
    FirebaseAuth mAuth;
    private FirebaseUser user;
    ImageView imvadmin;
    private DatabaseReference userDatabaseReference;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnIngresar = findViewById(R.id.btnREscpecialidades);
        btnRegistrar = findViewById(R.id.idREGISTRAR);
        btnRecuperar = findViewById(R.id.idRESTABLECER);
      //  btnAdministrador = findViewById(R.id.btnadministrador);
        imvadmin = findViewById(R.id.imvAdmin);
        editusuario=(EditText) findViewById(R.id.editusuario);
        editclave=(EditText) findViewById(R.id.editclave);
        userDatabaseReference = FirebaseDatabase.getInstance().getReference("Usuario");
        mAuth=FirebaseAuth.getInstance();


       /* btnadministrador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MenuAministrador.class);
                startActivity(intent);
            }
        });*/


        imvadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MenuAministrador.class);
                startActivity(intent);
            }
        });
        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MenuEspecialista.class);
                startActivity(intent);
            }
        });
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                usuario=editusuario.getText().toString();
                clave=editclave.getText().toString();
               /* String string = usuario;
                String[] parts = string.split("#");
                String part2 = parts[1];
                Toast.makeText(LoginActivity.this,part2,Toast.LENGTH_SHORT).show();

                if (string=="Administrador" ){

                    Toast.makeText(LoginActivity.this,"Bienbenido Administrador",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MenuAministrador.class);
                    startActivity(intent);
                }else if (part2=="@gmail.com") {

                    Toast.makeText(LoginActivity.this,"Bienbenido Administrador",Toast.LENGTH_SHORT).show();
                     loginEspecialista();

                }else {

                }*/
                login();
              //para cerrar sesion
               /* mAuth.signOut();
                startActivity(new Intent(LoginActivity.this,DasboardActivity.class));
                finish();*/

            }
        });
    }
    private  void  login(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Cargando...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        mAuth.signInWithEmailAndPassword(usuario,clave).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    checkVerifiedEmail();
                    //  Intent intent = new Intent(LoginActivity.this, DasboardActivity.class);
                    //  startActivity(intent);
                } else{
                    Toast.makeText(LoginActivity.this, "Verifique su Email", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }
    private void checkVerifiedEmail() {
        user = mAuth.getCurrentUser();
        boolean isVerified = false;
        if (user != null) {
            isVerified = user.isEmailVerified();
        }
        if (isVerified){

            final String UID = mAuth.getCurrentUser().getUid();
         //   userDatabaseReference.child(UID).child("verified").setValue("true");
            Intent intent = new Intent(LoginActivity.this, DasboardActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
           // userDatabaseReference.child(UID).child("active_now").setValue("true");
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Correo no verificado", Toast.LENGTH_SHORT).show();
            mAuth.signOut();
        }
    }
    private  void  loginEspecialista(){
        mAuth.signInWithEmailAndPassword(usuario,clave).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent = new Intent(LoginActivity.this, MenuEspecialista.class);
                    startActivity(intent);
                }
            }
        });
    }
}