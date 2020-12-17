package com.example.appv4.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appv4.R;
import com.google.android.gms.actions.ItemListIntents;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ListaEspecialista extends AppCompatActivity {

    Button bntguardarcita;

    TextView txtprueba,txtespecialidad;
    DatabaseReference mDatabase;
    Especialidad especialidad;

    TextView txtfecha;
    private int naño,nmes,ndia,aaño,ames,adia;
    static final int DATE_ID=0;
    Calendar c=Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_especialista);

        aaño=c.get(Calendar.YEAR);
        ames=c.get(Calendar.MONTH);
        adia=c.get(Calendar.DAY_OF_MONTH);
        txtfecha=(TextView) findViewById(R.id.txtfechadereserva);

        txtfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_ID);
            }
        });

        bntguardarcita=(Button) findViewById(R.id.btnguardarcita);

        bntguardarcita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ListaEspecialista.this,especialidad+" Reservacion correcta",Toast.LENGTH_SHORT).show();
            }
        });

        //setTitle(getClass().getSimpleName());

        //initViews();
       // initValues();

        /*mDatabase= FirebaseDatabase.getInstance().getReference();
        txtprueba=(TextView) findViewById(R.id.txtPrueba);

        mDatabase.child("Especialidad").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String especialidad= snapshot.child("especialidad").getValue().toString();

                    txtprueba.setText("La especialidad es: "+ especialidad );

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
    }

    private void colocar_fecha(){
        txtfecha.setText((nmes+1)+"/"+ndia+"/"+naño+" ");
    }
    private DatePickerDialog.OnDateSetListener onDateSetListener=
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    naño=year;
                    nmes=month;
                    ndia=dayOfMonth;
                    colocar_fecha();
                }
            };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case DATE_ID:
                return  new DatePickerDialog(this,onDateSetListener,aaño,ames,adia);
        }
        return  null;
    }

    /* private void initViews() {

        txtespecialidad=(TextView) findViewById(R.id.txtespecialidad);
    }

    private void initValues() {
        especialidad=(Especialidad) getIntent().getExtras().getSerializable("itemDetail");

        txtespecialidad.setText(especialidad.getEspecialidad());

    }*/
}