package com.example.braintreesdk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://bt-sdk-android-wpittman.c9users.io";

    String token;
    Button btn1;
    TextView tokenTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.secondActivityBtn);
        tokenTV = findViewById(R.id.tokenTV);
    }



    public void getClientToken(View view) {
        BTCLient.get("/client_token", null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("LOG", "error = " + throwable);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("LOG", "RESPONSE = " + responseString);
                token = responseString;
            }
        });
        }
    
    public void secondActivity(View view) {
        Intent i = new Intent(this, SecondActivity.class);
        i.putExtra("token", token);
        startActivity(i);
    }
}
