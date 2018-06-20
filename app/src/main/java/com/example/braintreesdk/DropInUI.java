package com.example.braintreesdk;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.dropin.DropInActivity;
import com.braintreepayments.api.dropin.DropInRequest;
import com.braintreepayments.api.dropin.DropInResult;
import com.braintreepayments.api.exceptions.InvalidArgumentException;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class DropInUI extends AppCompatActivity {

    String token;
    String captureId;
    public int REQUEST_CODE = 100;

    BraintreeFragment mBraintreeFragment;

    TextView successTV;
    TextView captureIdTV;

    Button completeDropInBtn;

    PaymentMethodNonce nonce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_in_ui);

        successTV = findViewById(R.id.successTV);
        captureIdTV = findViewById(R.id.captureIdTV);

        completeDropInBtn = findViewById(R.id.completeDropInBtn);
        completeDropInBtn.setVisibility(View.GONE);

        //token = getIntent().getExtras().getString("token");
        token = getClientToken(this);

        try {
            mBraintreeFragment = BraintreeFragment.newInstance(this, token);
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }

        completeDropInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postNoncetoServer(nonce);
            }
        });

    }

    public void onBraintreeSubmit(View v) {
        DropInRequest dropInRequest = new DropInRequest()
                .clientToken(token);
        startActivityForResult(dropInRequest.getIntent(this), REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                DropInResult result = data.getParcelableExtra(DropInResult.EXTRA_DROP_IN_RESULT);
                // use the result to update your UI and send the payment method nonce to your server
                Log.d("LOG", "Result: " + result);
                nonce = result.getPaymentMethodNonce();
                completeDropInBtn.setVisibility(View.VISIBLE);
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // the user canceled
            } else {
                // handle errors here, an exception may be available in
                Exception error = (Exception) data.getSerializableExtra(DropInActivity.EXTRA_ERROR);
            }
        }
    }

    public String getClientToken(DropInUI view) {
        BTCLient.get("/dropin_token", null, new TextHttpResponseHandler() {
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
        return token;
    }

    void postNoncetoServer(PaymentMethodNonce nonce) {
        BTCLient client = new BTCLient();
        RequestParams params = new RequestParams();
        params.put("payment_method_nonce", nonce);
        client.post("/dropin_nonce", params, new JsonHttpResponseHandler() {
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
