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
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class PasswordActivity extends AppCompatActivity {

    String vendor, aadhar, amount;
    SharedPreferences sharedPreferences;

    Button pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);


        ImageView imageView = (ImageView) findViewById(R.id.main_image);
        imageView.setColorFilter(Color.argb(255, 0, 255, 0));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            vendor = extras.getString("VENDOR");
            amount = extras.getString("AMOUNT");
            ( (TextView) findViewById(R.id.amtTxt)).setText("Amount to Pay: " + amount);
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

        pay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String pin = ( (EditText)findViewById(R.id.PIN) ).getText().toString();

                HttpResponse response = null;
                try {
                    HttpClient client = new DefaultHttpClient();
                    HttpGet request = new HttpGet();
                    request.setURI(new URI("http:192.168.55.63:3000/from="+aadhar+"&frompass="+pin+"&to="+vendor+"&amt="+amount));
                    response = client.execute(request);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (!(response==null || response.toString()=="-1")) {
                    Intent intent = new Intent(getBaseContext(), PaymentSuccess.class);
                    intent.putExtra("BALANCE", response.toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "An error occurred", Toast.LENGTH_LONG).show();
                }
            }
        });


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
