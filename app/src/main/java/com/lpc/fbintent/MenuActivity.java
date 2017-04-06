package com.lpc.fbintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MenuActivity extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inici, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent = null;
        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.Inici:
                intent = new Intent(this, Inici.class);
                break;
            case R.id.Atendre_client:
                intent = new Intent(this, Seguent_Client.class);
                break;
            case R.id.Agafar_numero:
                intent = new Intent(this, Agafar_Numero.class);
                break;
        }
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }
}
