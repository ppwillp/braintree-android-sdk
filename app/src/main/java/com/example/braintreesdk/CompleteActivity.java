package com.example.braintreesdk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class CompleteActivity extends AppCompatActivity {

    String nonce;
    String captureId;

    TextView successTV;
    TextView captureIdTV;
    Button sendNonce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

        nonce = getIntent().getExtras().getString("nonce");

        sendNonce = findViewById(R.id.sendNonce);
        successTV = findViewById(R.id.successTV);
        captureIdTV = findViewById(R.id.captureIdTV);

        sendNonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postNonceToServer(nonce);
            }
        });



      }

      public void postNonceToServer(String nonce) {
        BTCLient client = new BTCLient();
        RequestParams params = new RequestParams();
        params.put("payment_method_nonce", nonce);
        client.post("/nonce", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {
                Log.d("LOG", "Response: " + responseBody.toString());
                Gson gson = new Gson();
                String jsonResponse = gson.toJson(responseBody);
                Log.d("LOG", jsonResponse);
                Response res = new Response();
                res = gson.fromJson(String.valueOf(responseBody), Response.class);
                captureId = res.getCaptureId();
                successTV.setText(R.string.success);
                captureIdTV.setText(captureId);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable t, JSONObject obj) {
                Log.d("LOG", "Errors: " + t);
            }
        });
      }
}
