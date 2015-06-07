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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {

    Button getOTP, cont;
    EditText aadharNo, OTP;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getOTP = (Button) findViewById(R.id.getOTP);
        cont = (Button) findViewById(R.id.cont);
        aadharNo = (EditText) findViewById(R.id.aadharNo);
        OTP = (EditText) findViewById(R.id.OTP);
        cont.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
        getOTP.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);


        sharedpreferences = getSharedPreferences("Prefs", Context.MODE_PRIVATE);

        if(!sharedpreferences.getBoolean("first_time", true)){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        getOTP.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                boolean reply=false;
                String aadhar = aadharNo.getText().toString();
                //TODO: Send aadhar no to server
                reply=true;
                if(reply==true){
                    getOTP.setVisibility(View.GONE);
                    OTP.setVisibility(View.VISIBLE);
                    cont.setVisibility(View.VISIBLE);
                }
            }
        });

        cont.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {

                boolean reply=false;
                String aadhar = aadharNo.getText().toString();
                String otp = OTP.getText().toString();
                // TODO: Send OTP and Aadhar and chk
                reply=true;
                if(reply==true){
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("AADHAR", aadhar);
                    editor.putBoolean("first_time", false);
                    editor.apply();
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
