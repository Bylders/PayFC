package com.palashbansal96.payfc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


public class PasswordActivity extends AppCompatActivity {

    String vendor, aadhar, amount;
    SharedPreferences sharedPreferences;

    Button pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            vendor = extras.getString("VENDOR");
            amount = extras.getString("AMOUNT");
        }
        else{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        sharedPreferences = getSharedPreferences("Prefs", Context.MODE_PRIVATE);

        if(sharedPreferences.getBoolean("first_time", true)){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        aadhar = sharedPreferences.getString("AADHAR", "0");

        pay = (Button) findViewById(R.id.payMoney);
        pay.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_password, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }
}
