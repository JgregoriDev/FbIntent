package com.lpc.fbintent;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Seguent_Client extends MenuActivity {
    private TextView Text_SegClient;
    private Button b_reiniciar;
    private Button b_seguent;
    private DatabaseReference actual_numero;
    private DatabaseReference ultim_numero;
    private int n_actual_numero, n_ultim_numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguent__client);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Text_SegClient = (TextView) findViewById(R.id.Text_SegClient);
        b_seguent = (Button) findViewById(R.id.b_seguent);
        b_reiniciar = (Button) findViewById(R.id.b_reiniciar);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        actual_numero = firebaseDatabase.getReference("actual_numero");
        actual_numero.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String actual_num = dataSnapshot.getValue(String.class);
                n_actual_numero = Integer.parseInt(actual_num);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Text_SegClient.setText("" + n_actual_numero);
        ultim_numero = firebaseDatabase.getReference("ultim_numero");


        ultim_numero.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String ultim_num = dataSnapshot.getValue(String.class);
                n_ultim_numero = Integer.parseInt(ultim_num);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        b_seguent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NouClient(v);
            }
        });
        b_reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReiniciarValors(v);
            }
        });

    }

    private void ReiniciarValors(View v) {
        actual_numero.setValue(""+0);
        ultim_numero.setValue(""+0);
        actual_numero.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String d = dataSnapshot.getValue(String.class);
                n_actual_numero = Integer.parseInt(d);
                Text_SegClient.setText("" + n_actual_numero);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void NouClient(View v) {
        if (n_actual_numero >= n_ultim_numero) {
            Toast.makeText(this, "No hi han més clients", Toast.LENGTH_SHORT).show();
        } else {
            actual_numero.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String d = dataSnapshot.getValue(String.class);
                    n_actual_numero = Integer.parseInt(d);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            n_actual_numero++;
            actual_numero.setValue("" + n_actual_numero);
            Text_SegClient.setText("" + n_actual_numero);
        }

    }

}
