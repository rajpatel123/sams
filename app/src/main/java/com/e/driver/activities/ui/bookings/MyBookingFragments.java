package com.e.driver.activities.ui.bookings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.e.driver.R;
import com.e.driver.adapters.BookingsAdapter;
import com.e.driver.models.bookings.Bookings;
import com.e.driver.retrofit.RestClient;
import com.e.driver.utils.Constants;
import com.e.driver.utils.SamsPrefs;
import com.e.driver.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyBookingFragments extends Fragment {
    Bookings bookings;
    RecyclerView recyclerView;
    BookingsAdapter bookingsAdapter;
    TextView bookingText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        recyclerView = root.findViewById(R.id.customer_booking_recycler);
        bookingText=root.findViewById(R.id.bookingText);
        bookings = new Bookings();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        bookingsAdapter = new BookingsAdapter(getActivity(), bookings.getData().getBookingHistory());
        recyclerView.setAdapter(bookingsAdapter);

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
                    }
                    else {

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

}