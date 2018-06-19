package com.example.braintreesdk;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.PayPal;
import com.braintreepayments.api.exceptions.InvalidArgumentException;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCreatedListener;
import com.braintreepayments.api.models.PayPalAccountNonce;
import com.braintreepayments.api.models.PayPalRequest;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class SecondActivity extends AppCompatActivity {

    BraintreeFragment mBraintreeFragment;
    Button ecBtn;
    Button nonceBtn;
    String nonce;
    String mAuthorization;

    private static final String BASE_URL = "https://bt-sdk-android-wpittman.c9users.io";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ecBtn = findViewById(R.id.ecBtn);
        nonceBtn = findViewById(R.id.nonceBtn);

        mAuthorization = getIntent().getExtras().getString("token");


        try {
            mBraintreeFragment = BraintreeFragment.newInstance(this, mAuthorization);
                //mBraintreeFragment is ready to use
        } catch (InvalidArgumentException e) {
            //error
        }
        mBraintreeFragment.addListener(new PaymentMethodNonceCreatedListener() {
            @Override
            public void onPaymentMethodNonceCreated(PaymentMethodNonce paymentMethodNonce) {
                Log.d("TAG", "Results = " + paymentMethodNonce.getNonce());
                nonce = paymentMethodNonce.getNonce();
                if(paymentMethodNonce instanceof PayPalAccountNonce) {
                    PayPalAccountNonce payPalAccountNonce = (PayPalAccountNonce)paymentMethodNonce;

                }
            }
        });

    }

   public void setupBraintreeAndStartExpressCheckout(View view) {
        PayPalRequest request = new PayPalRequest("1")
                .currencyCode("USD")
                .intent(PayPalRequest.INTENT_SALE);
        PayPal.requestOneTimePayment(mBraintreeFragment, request);


    }

  /* public void startBillingAgreement(View view) {
       PayPalRequest request = new PayPalRequest()
               .localeCode("US")
               .billingAgreementDescription("TEST DESCRIPTION");
       PayPal.requestBillingAgreement(mBraintreeFragment, request);
   }*/

    public void postNonceToServer(View view) {
        Intent i = new Intent(this, CompleteActivity.class);
        i.putExtra("nonce", nonce);
        startActivity(i);
    }

    public void goToDropInUI(View view) {
        Intent i = new Intent(this, DropInUI.class);
        i.putExtra("token", mAuthorization);
        startActivity(i);
    }


}
