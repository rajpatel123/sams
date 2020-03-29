package com.samsapp.activities;

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

import com.samsapp.R;
import com.samsapp.models.paymentTransaction.MMap;
import com.samsapp.models.paymentTransaction.TransactionResponse;
import com.samsapp.models.paymentTransaction.primePaymentTransaction;
import com.samsapp.models.primeMember.PrimeOrderResponse;
import com.samsapp.models.primePayment.PrimePaymentResponse;
import com.samsapp.payment.checksum;
import com.samsapp.retrofit.RestClient;
import com.samsapp.utils.Constants;
import com.samsapp.utils.SamsPrefs;
import com.samsapp.utils.Utils;
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
    private Boolean isPrime;
    String email;
    String name;
    String loginMobile, AlterMobile="8474787487",address="NA",landMark="na",pincode="201301",City_id,State_id;

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
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{

            if (SamsPrefs.getString(context, Constants.EMAIL) != null) {
                email = SamsPrefs.getString(context, Constants.EMAIL);
                name=SamsPrefs.getString(getApplicationContext(),Constants.NAME);
                loginMobile=SamsPrefs.getString(getApplicationContext(),Constants.MOBILE_NUMBER);
                AlterMobile=SamsPrefs.getString(getApplicationContext(),Constants.CUST_ALTER_MOB);
                address=SamsPrefs.getString(getApplicationContext(),Constants.CUST_ADDRESS);
                landMark=SamsPrefs.getString(getApplicationContext(),Constants.CUST_LANDMARK);
                pincode=SamsPrefs.getString(getApplicationContext(),Constants.CUST_PINCODE);
                City_id=SamsPrefs.getString(getApplicationContext(),Constants.CITY_ID);
                State_id=SamsPrefs.getString(getApplicationContext(),Constants.STATE_ID);
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

            if (Utils.isInternetConnected(JoiPrimeMembershipActivity.this)){

                RestClient.updatePrimePaymentTransaction(txnMid, txnId, orderId, bankTxnId, txnAmount, currency, status, respCode, respMsg, txnDate,
                        gatewayName, bankName, checksum, paymentMode, email, loginid, name, loginMobile, AlterMobile, address, landMark, pincode,
                        ""+1, ""+1, new Callback<PrimePaymentResponse>() {
                            @Override
                            public void onResponse(Call<PrimePaymentResponse> call, Response<PrimePaymentResponse> response) {
                                PrimePaymentResponse primePaymentResponse=response.body();

                                if (primePaymentResponse.getStatusType().equalsIgnoreCase("Success")&&
                                        primePaymentResponse.getData()!=null){

                                    SamsPrefs.putString(getApplicationContext(), Constants.CTYPE_ID, primePaymentResponse.getData().getNewCType());
                                    SamsPrefs.putString(getApplicationContext(), Constants.CUST_ID, primePaymentResponse.getData().getNewLoginId());
                                    SamsPrefs.putString(getApplicationContext(), Constants.PRIME_COUNT, "" + primePaymentResponse.getData().getTotcount());

                                    SamsPrefs.putString(JoiPrimeMembershipActivity.this,Constants.ISPRIME,primePaymentResponse.getData().getPrime());
                                    SamsPrefs.putString(JoiPrimeMembershipActivity.this,Constants.ISPRIME_DATE,primePaymentResponse.getData().getPrimeEndDate());
                                }
                                //getPrimeOrderNo();
                                Toast.makeText(context, "Payment Success", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<PrimePaymentResponse> call, Throwable t) {

                                Toast.makeText(context, "Payment Failed", Toast.LENGTH_SHORT).show();

                            }
                        });
            }else{
                Toast.makeText(context, "connection failure", Toast.LENGTH_SHORT).show();

            }
        }catch(Exception e){
            e.printStackTrace();
        }

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
