package com.e.driver.activities.ui.bookings;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.e.driver.R;
import com.e.driver.adapters.BookingsAdapter;
import com.e.driver.models.bookings.Bookings;
import com.e.driver.models.paymentTransaction.MMap;
import com.e.driver.models.paymentTransaction.TransactionResponse;
import com.e.driver.models.paymentTransaction.paymentTransactionRequest;
import com.e.driver.payment.checksum;
import com.e.driver.retrofit.RestClient;
import com.e.driver.utils.Constants;
import com.e.driver.utils.SamsPrefs;
import com.e.driver.utils.Utils;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyBookingFragments extends Fragment implements BookingsAdapter.PaymentCallInterface {
    Bookings bookings;
    RecyclerView recyclerView;
    BookingsAdapter bookingsAdapter;
    TextView bookingText;
    String email;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        recyclerView = root.findViewById(R.id.customer_booking_recycler);
        bookingText = root.findViewById(R.id.bookingText);
        bookings = new Bookings();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        bookingsAdapter = new BookingsAdapter(getActivity(), bookings.getData().getBookingHistory());
        recyclerView.setAdapter(bookingsAdapter);
        bookingsAdapter.setpayMentCallBack(this);

        getCustomerBookings();
        return root;
    }

    private void getCustomerBookings() {
        RestClient.getCustomerBookings(SamsPrefs.getString(getActivity(), Constants.CUST_ID), new Callback<Bookings>() {
            @Override
            public void onResponse(Call<Bookings> call, Response<Bookings> response) {
                Utils.dismissProgressDialog();
                if (response.code() == 200) {
                    bookings = response.body();
                    if (bookings.getStatusType().equalsIgnoreCase("Success") &&
                            bookings.getData().getBookingHistory() != null) {

                        bookingsAdapter.setBookingData(bookings.getData().getBookingHistory());
                        bookingsAdapter.notifyDataSetChanged();
                        bookingText.setVisibility(View.GONE);
                    } else {

                        recyclerView.setVisibility(View.GONE);
                        bookingText.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<Bookings> call, Throwable t) {
                Utils.dismissProgressDialog();
            }
        });

    }

    @Override
    public void onPaymentClick(String orderId, String orderNo, String balance) {
        Intent intent = new Intent(getContext(), checksum.class);
        intent.putExtra(Constants.ORDER_ID, orderId);
        intent.putExtra(Constants.ORDER_NUMBER, orderNo);
        intent.putExtra(Constants.AMOUNT, balance);
        startActivityForResult(intent, Constants.PAYMENT_CALL);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        Log.e("checksum ", " respon true " + data.getStringExtra("data"));
        if (SamsPrefs.getString(getContext(), Constants.EMAIL) != null) {

            TransactionResponse transactionResponse = new Gson().fromJson(data.getStringExtra("data"),TransactionResponse.class);
            transactionResponse.getMMap().getBANKNAME();
            transactionResponse.getMMap().getBANKTXNID();
            transactionResponse.getMMap().getCHECKSUMHASH();

            email = SamsPrefs.getString(getContext(), Constants.EMAIL);
        }

        MMap mMap = new MMap();
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

        if (Utils.isInternetConnected(getContext())) {
            Utils.showProgressDialog(getContext());

            RestClient.updatePaymentTransaction(txnMid, txnId, orderId, bankTxnId, txnAmount, currency, status, respCode, respMsg,
                    txnDate, gatewayName, bankName, checksum, paymentMode, email, orderId, new Callback<paymentTransactionRequest>() {

                        @Override
                        public void onResponse(Call<paymentTransactionRequest> call, Response<paymentTransactionRequest> response) {
                            Utils.dismissProgressDialog();
                            getCustomerBookings();

                            Toast.makeText(getContext(), "Paymment Success", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<paymentTransactionRequest> call, Throwable t) {
                            Utils.dismissProgressDialog();
                            Toast.makeText(getContext(), "Something went wrong!!!", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Utils.dismissProgressDialog();
            Toast.makeText(getContext(), "Internet Connections Failed!!", Toast.LENGTH_SHORT).show();
        }

    }
}