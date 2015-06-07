package com.palashbansal96.payfc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
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
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

                String pin = ((EditText) findViewById(R.id.PIN)).getText().toString();
                String ur = "http://192.168.55.63:3000/?from=" + aadhar + "&&frompass=" + pin + "&&to=" + vendor + "&&amt=" + amount;
                new getBalance().execute(ur);
            }
        });
    }


    class getBalance extends AsyncTask<String, Void, Void> {

        protected Void doInBackground(String... uri) {
            String ur=uri[0];
//            HttpResponse response = null;
//            try {
//                HttpClient client = new DefaultHttpClient();
//                HttpGet request = new HttpGet();
//                request.setURI(new URI("http://192.168.55.63:3000/?from=64&&frompass=345&&to=345&&amt=3453"));
//                response = client.execute(request);
//            } catch (URISyntaxException e) {
//                e.printStackTrace();
//            } catch (ClientProtocolException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//



            HttpGet request=null;
            String html = "";
            try {
                HttpClient client = new DefaultHttpClient();
                request = new HttpGet(ur);
                HttpResponse response = client.execute(request);

                InputStream in = response.getEntity().getContent();
                BufferedReader reader;
                reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder str = new StringBuilder();
                html = reader.readLine();
            }catch (Exception e){
                e.printStackTrace();
            }
            if (html.equals("-1")) {
                Intent intent = new Intent(getBaseContext(), PaymentSuccess.class);
                startActivity(intent);
            } else {

            }
            return null;
        }

        protected void onPostExecute() {
            // TODO: check this.exception
            // TODO: do something with the feed
        }
    }
}
