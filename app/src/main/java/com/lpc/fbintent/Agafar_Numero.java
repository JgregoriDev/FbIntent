package com.lpc.fbintent;

import android.database.sqlite.SQLiteDatabase;
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

public class Agafar_Numero extends MenuActivity {
    private Button b_agafar;
    private TextView Text_NumSQL;
    private TextView Text_NumFb;
    private TextView Text_Torn;
    private DatabaseReference ultim_numero;
    private DatabaseReference actual_numero;
    private int num_ult_num,countclick, num_act;

    private DatabaseReference databaseReference;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agafar__numero);
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
        b_agafar= (Button) findViewById(R.id.b_agafar);
        Text_NumFb= (TextView) findViewById(R.id.Text_NumFb);
        Text_Torn= (TextView) findViewById(R.id.Text_Torn);
        Text_NumSQL= (TextView) findViewById(R.id.Text_NumSQL);

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        ultim_numero=firebaseDatabase.getReference("ultim_numero");
        actual_numero=firebaseDatabase.getReference("actual_numero");
        ultim_numero.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String valor=dataSnapshot.getValue(String.class);
                num_ult_num =Integer.parseInt(valor);
                Log.d("Proba",""+num_ult_num);
                Text_NumFb.setText(""+num_ult_num);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        actual_numero.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String valor=dataSnapshot.getValue(String.class);
                num_act =Integer.parseInt(valor);
                //Text_NumFb.setText(""+ num_ult_num);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        b_agafar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countclick++;
                AgafarNumero(v);
            }
        });

    }

    private void AgafarNumero(View v) {
        //String SQL="SELECT(NumClick) FROM T_NumeroCuaAndClicks";
        if(countclick==1){
            ultim_numero.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String valor=dataSnapshot.getValue(String.class);
                    num_ult_num =Integer.parseInt(valor);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            num_ult_num++;
            ultim_numero.setValue(""+ num_ult_num);
            Text_NumFb.setText(""+ num_ult_num);
            InsertarValor(num_ult_num);
        }else{
             if(countclick % 3==0){
                Toast.makeText(this,"No pots demanar més d'un torn al mateix temps",Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void InsertarValor(int n) {
        MySQLiteHelper ayudaBD=new MySQLiteHelper(this);
        SQLiteDatabase db = ayudaBD.getWritableDatabase();
        String sql="INSERT INTO T_NumeroCuaAndClicks(NumCua,NumCLick)VALUES("+n+",1)";
        db.execSQL(sql);

        Log.d("Inserción correcta","Inserción ha sido realizada de manera exitosa "+sql);
        //Log.d("Inserción correcta","Ruta "+db.getPath());
        Text_NumSQL.setText(""+n);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MySQLiteHelper ayudaBD=new MySQLiteHelper(this);
        SQLiteDatabase db = ayudaBD.getWritableDatabase();
        String SQL="Update Set NumCLick=0 WHERE NumCLick=1";
        db.execSQL(SQL);
        db.close();
    }
}
