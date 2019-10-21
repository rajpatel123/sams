package com.e.driver.activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.e.driver.R;
import com.e.driver.models.paymentTransaction.MMap;
import com.e.driver.models.paymentTransaction.TransactionResponse;
import com.e.driver.models.primeMember.PrimeOrderResponse;
import com.e.driver.payment.checksum;
import com.e.driver.retrofit.RestClient;
import com.e.driver.utils.Constants;
import com.e.driver.utils.SamsPrefs;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoiPrimeMembershipActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button joinNow;
    private String primrOrder;
    private String loginid;
    private Context context;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joi_prime_membership);
        loginid=SamsPrefs.getString(getApplicationContext(),Constants.CUST_ID);
        joinNow=findViewById(R.id.btn_joinNow);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getPrimeOrderNo();

        if (getIntent().hasExtra(Constants.PRIME_MEMBER)){
            toolbar.setTitle(getIntent().getStringExtra(Constants.PRIME_MEMBER));
        }

        joinNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JoiPrimeMembershipActivity.this, checksum.class);
                intent.putExtra(Constants.ORDER_ID, primrOrder);
                intent.putExtra(Constants.ORDER_NUMBER, loginid);
                intent.putExtra(Constants.AMOUNT, "1.00");
                startActivityForResult(intent, Constants.PAYMENT_CALL);
            }

        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("checksum ", " respon true " + data.getStringExtra("data"));

        if (SamsPrefs.getString(context, Constants.EMAIL) != null) {
            email = SamsPrefs.getString(context, Constants.EMAIL);
        }
        TransactionResponse transactionResponse = new Gson().fromJson(data.getStringExtra("data"),TransactionResponse.class);
        MMap mMap=transactionResponse.getMMap();

        String status = mMap.getSTATUS();
        String checksum = mMap.getCHECKSUMHASH();
        String bankName = mMap.getBANKNAME();
        String orderId = mMap.getORDERID();
        String txnAmount = mMap.getTXNAMOUNT();
        String txnDate = mMap.getTXNDATE();
        String txnMid = mMap.getMID();
        String txnId = mMap.getTXNID();
        final String respCode = mMap.getRESPCODE();
        String paymentMode = mMap.getPAYMENTMODE();
        String bankTxnId = mMap.getBANKTXNID();
        String currency = mMap.getCURRENCY();
        String gatewayName = mMap.getGATEWAYNAME();
        String respMsg = mMap.getRESPMSG();



    }

    private void getPrimeOrderNo() {
        RestClient.genPrimeOrderNum(new Callback<PrimeOrderResponse>() {
            @Override
            public void onResponse(Call<PrimeOrderResponse> call, Response<PrimeOrderResponse> response) {
                PrimeOrderResponse primeOrderResponse=response.body();
                if (primeOrderResponse.getStatusType().equalsIgnoreCase("Success")&&
                    primeOrderResponse.getData()!=null){
                    primrOrder=primeOrderResponse.getData().getOrderNo();
                    SamsPrefs.putString(getApplicationContext(),Constants.PRIME_ORDER_NUM,primrOrder);
                }

            }

            @Override
            public void onFailure(Call<PrimeOrderResponse> call, Throwable t) {
                Toast.makeText(JoiPrimeMembershipActivity.this, "Payment Failure", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
