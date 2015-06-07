package com.palashbansal96.payfc;

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

import org.w3c.dom.Text;


public class AmountEnterActivity extends AppCompatActivity {

    Button contAmt;
    String aadhar;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount_enter);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            aadhar = extras.getString("VENDOR");
            ( (TextView) findViewById(R.id.venderAad)).setText("Vendor's Aadhar: " + aadhar);
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

        contAmt = (Button) findViewById(R.id.contAmt);
        contAmt.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);

        contAmt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String cost = ((EditText) findViewById(R.id.amount)).getText().toString();
                Intent intent = new Intent(getBaseContext(), PaymentSuccess.class);
                intent.putExtra("VENDOR", aadhar);
                intent.putExtra("AMOUNT", cost);
                startActivity(intent);
            }
        });
        ImageView imageView = (ImageView) findViewById(R.id.amount_image);
        imageView.setColorFilter(Color.argb(255, 0, 255, 0));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_amount_enter, menu);
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
