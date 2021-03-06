package com.palashbansal96.payfc;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    Button go, cont;
    EditText aadharNo, OTP;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        go = (Button) findViewById(R.id.go);
        cont = (Button) findViewById(R.id.cont);
        aadharNo = (EditText) findViewById(R.id.aadharNo);
        OTP = (EditText) findViewById(R.id.OTP);
        cont.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
        go.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);


        sharedpreferences = getSharedPreferences("Prefs", Context.MODE_PRIVATE);

        if(!sharedpreferences.getBoolean("first_time", true)){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        go.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                boolean reply=false;
                String aadhar = aadharNo.getText().toString();

                //TODO: Send aadhar no to server

                reply=true;

                if(reply==true){
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("AADHAR", aadhar);
                    editor.putBoolean("first_time", false);
                    editor.apply();
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Aadhar No. not valid/registered", Toast.LENGTH_LONG).show();
                }
            }
        });

        ImageView imageView = (ImageView) findViewById(R.id.main_image);
        imageView.setColorFilter(Color.argb(255, 0, 255, 0));

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
