package com.e.driver.adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.e.driver.R;
import com.e.driver.activities.MainActivity;
import com.e.driver.models.bookings.BookingHistory;
import com.e.driver.payment.checksum;
import com.e.driver.utils.Constants;
import com.e.driver.utils.SamsPrefs;

import java.util.List;

public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.ViewHolder> {

    Activity context;
    List<BookingHistory> bookingHistoryList;
    OnActionItemClickListener listener;
    float amountt;

    public BookingsAdapter(Activity context, List<BookingHistory> bookingHistoryList) {
        this.context = context;
        this.bookingHistoryList = bookingHistoryList;
    }

    @Override
    public BookingsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_booking, viewGroup, false);
        return new BookingsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BookingsAdapter.ViewHolder holder, int position) {
        holder.tv_category_name.setText(bookingHistoryList.get(position).getServiceName());
        holder.date.setText("Booking Date : "+bookingHistoryList.get(position).getBookingDate());
        holder.time.setText("Booking Time Slot: "+bookingHistoryList.get(position).getTimeSlotName());
        holder.amount.setText("Service Charge:  INR "+bookingHistoryList.get(position).getBalance());
        holder.booking_status.setText(bookingHistoryList.get(position).getStatusName());

        SamsPrefs.putString(context, Constants.ORDER_ID,bookingHistoryList.get(position).getOrderId());
        SamsPrefs.putString(context,Constants.ORDER_NUMBER,bookingHistoryList.get(position).getOrderNo());
        SamsPrefs.putString(context,Constants.AMOUNT,bookingHistoryList.get(position).getBalance());

        amountt = Float.parseFloat((bookingHistoryList.get(position).getBalance()));

        if (amountt > 0) {
            holder.booking_status.setText("paynow");
            holder.booking_status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context.getApplicationContext(), checksum.class);
                    context.startActivity(intent);

                }
            });

            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, 101);
            }


        }
//        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener != null) {
//                    listener.onActionItemClick(holder.getAdapterPosition());
//                }
//            }
//        });

    }



    @Override
    public int getItemCount() {
        if (bookingHistoryList != null && bookingHistoryList.size() > 0)
            return bookingHistoryList.size();
        return 0;
    }

    public void setBookingData(List<BookingHistory> bookingHistory) {
        this.bookingHistoryList=bookingHistory;
    }

    public interface OnActionItemClickListener {
        void onActionItemClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon, right_icon;
        TextView tv_category_name, date, time, amount;
        Button booking_status;
        View row;

        public ViewHolder(View view) {
            super(view);
            row = view;
            tv_category_name = view.findViewById(R.id.tv_category_name);
            date = view.findViewById(R.id.date);
            time = view.findViewById(R.id.timeSlot);
            amount = view.findViewById(R.id.amount);
            booking_status =  view.findViewById(R.id.booking_status);
        }
    }

}

