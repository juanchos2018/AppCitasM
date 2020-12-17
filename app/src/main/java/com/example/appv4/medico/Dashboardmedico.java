package com.example.appv4.medico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.appv4.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dashboardmedico extends AppCompatActivity {


    private DatabaseReference referenceusuario;
    private FirebaseAuth mAuth;
    public FirebaseUser currentUser;

    TextView tvnombres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboardmedico);

        tvnombres=(TextView)findViewById(R.id.textView2);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        final String user_id = mAuth.getCurrentUser().getUid();
        referenceusuario = FirebaseDatabase.getInstance().getReference("Usuario").child(user_id);
        referenceusuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nombre = snapshot.child("nombre").getValue().toString();
                tvnombres.setText(nombre);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}