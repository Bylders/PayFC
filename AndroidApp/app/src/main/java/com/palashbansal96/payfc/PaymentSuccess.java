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
import android.widget.Toast;


public class PaymentSuccess extends AppCompatActivity {

    String amount;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);

        ImageView imageView = (ImageView) findViewById(R.id.main_image);
        imageView.setColorFilter(Color.argb(255, 0, 255, 0));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            amount = extras.getString("BALANCE");
        }
        else{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        ((EditText)findViewById(R.id.balance)).setText("Rs. " + amount);

        sharedPreferences = getSharedPreferences("Prefs", Context.MODE_PRIVATE);

        if(sharedPreferences.getBoolean("first_time", true)){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        Button balance = (Button) findViewById(R.id.payMoney);
        balance.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);

        balance.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_payment_success, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
