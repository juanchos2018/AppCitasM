package com.example.appv4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appv4.Activity.LoginActivity;
import com.example.appv4.admin.Dasboardadmin;
import com.example.appv4.admin.Informacionapp;
import com.example.appv4.admin.ListaEspecialidad;
import com.example.appv4.admin.RegistrarEspecialidad;
import com.example.appv4.medico.Dashboardmedico;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DasboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    ImageView imvReservar, imvInformacion, ivmAdmin, ivmMedico;
    TextView txtReservar;
    //Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar idtoolbar;
    private DatabaseReference referenceusuario;
    private FirebaseAuth mAuth;
    public FirebaseUser currentUser;
    private ProgressDialog progressDialog;
     CardView card1,card2,card3,card4,card5,card6,card7,card8;
     Button btnsalir;
    TextView tvnombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dasboard);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        idtoolbar = findViewById(R.id.toolbar);

        imvReservar=(ImageView) findViewById(R.id.imvReservarCita);
        imvInformacion=(ImageView) findViewById(R.id.idmenuinformacion);
        ivmAdmin=(ImageView) findViewById(R.id.idopadmin);
        ivmMedico=(ImageView) findViewById(R.id.idopdoctor);
        txtReservar=(TextView) findViewById(R.id.txtReservarcita);
        tvnombre=(TextView)findViewById(R.id.textView2);
        card1=(CardView)findViewById(R.id.card1);
        card2=(CardView)findViewById(R.id.card2);
        card3=(CardView)findViewById(R.id.card3);
        card4=(CardView)findViewById(R.id.card4);
        card5=(CardView)findViewById(R.id.card5);
        card6=(CardView)findViewById(R.id.card6);
        card7=(CardView)findViewById(R.id.card7);
        card8=(CardView)findViewById(R.id.card8);
        btnsalir=(Button)findViewById(R.id.btnsalir);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        progressDialog=new ProgressDialog(DasboardActivity.this);
        progressDialog.setMessage("Espere un momento");
        progressDialog.setCancelable(false);
        progressDialog.show();
        if (currentUser != null) {
            final String user_id = mAuth.getCurrentUser().getUid();
            referenceusuario = FirebaseDatabase.getInstance().getReference("Usuario").child(user_id);
            referenceusuario.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    String cargo = snapshot.child("cargo").getValue().toString();
                    String nombre = snapshot.child("nombre").getValue().toString();
                    String apellido = snapshot.child("apellido").getValue().toString();
                    tvnombre.setText(nombre+ " "+ apellido);
                    if (cargo.equals("Doctor")){
                  //      imvInformacion.setVisibility(View.INVISIBLE);
                        card1.setEnabled(false);
                        card2.setEnabled(false);
                        card3.setEnabled(false);
                        card4.setEnabled(false);
                        card5.setEnabled(false);
                        card6.setEnabled(false);
                        card7.setEnabled(false);
                        card1.setCardBackgroundColor(getColor(R.color.colorOrange));
                        imvReservar.setEnabled(false);
                        imvInformacion.setEnabled(false);


                    }
                    if (cargo.equals("Paciente")){
                        card7.setVisibility(View.INVISIBLE);
                        card8.setVisibility(View.GONE);

                    }
                    if (cargo.equals("Administrador")){

                    }

                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    progressDialog.dismiss();
                }
            });


        }
        btnsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salir();
            }
        });

                /*--------------------------------------------------------------*/

        setSupportActionBar(idtoolbar);

        /*--------------------------------------------------------------*/

        //Menu menu = navigationView.getMenu();
        //menu.findItem(R.id.idNAV_HOME2).setVisible(false);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this,drawerLayout,idtoolbar,
                R.string.Navigation_drawer_ddddd,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        /*-------------------------------EVENTOS ONCLICK MENU-------------------------------*/

            imvReservar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DasboardActivity.this, ListaEspecialidad.class);
                    startActivity(intent);
                }
            });

            imvInformacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DasboardActivity.this, Informacionapp.class);
                    startActivity(intent);
                    finish();
                }
            });

            ivmAdmin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DasboardActivity.this, Dasboardadmin.class);
                    startActivity(intent);
                }
            });

            ivmMedico.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DasboardActivity.this, Dashboardmedico.class);
                    startActivity(intent);
                }
            });


            txtReservar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

    private void salir() {
        mAuth.signOut();
        logOutUser();
    }
    /*-------------------------------EVENTOS ONCLICK RESERVAS-------------------------------*/

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
        //checking logging, if not login redirect to Login ACTIVITY
        if (currentUser == null ){
            mAuth.signOut();
            logOutUser(); // Return to Login activity

        }
        if (currentUser != null ){
         //   userDatabaseReference.child("active_now").setValue("true");

        }

    }

    private void logOutUser() {

        Intent loginIntent =  new Intent(DasboardActivity.this, LoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }
    @Override
    public void onBackPressed(){

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);

        }else{
            super.onBackPressed();

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        //int id = menuItem.getItemId();
        //FragmentManager fm = getSupportFragmentManager();
        //if(id == R.id.idNAV_HOME){
        //    fm.beginTransaction().replace(R.id.drawer_layout, new InformacionFragment()).commit();
        //}

        switch (menuItem.getItemId()){

            case R.id.idNAV_HOME:
                Intent intent1 = new Intent(DasboardActivity.this, RegistrarEspecialidad.class);
                startActivity(intent1);
                break;

            case R.id.idNAV_HOME1:
                Intent intent2 = new Intent(DasboardActivity.this, ListaEspecialidad.class);
                startActivity(intent2);
                break;
        }

        return true;
    }
}