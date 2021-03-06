package com.samsapp.adapters;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.samsapp.R;
import com.samsapp.models.bookings.BookingHistory;
import com.samsapp.utils.Constants;
import com.samsapp.utils.SamsPrefs;

import java.util.List;

public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.ViewHolder> {

    Activity context;
    List<BookingHistory> bookingHistoryList;
    OnActionItemClickListener listener;
    float amountt;
    PaymentCallInterface paymentCallInterface;

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
    public void onBindViewHolder(final BookingsAdapter.ViewHolder holder, final int position) {
        holder.tv_category_name.setText(bookingHistoryList.get(position).getServiceName());
        holder.date.setText("Booking Date : " + bookingHistoryList.get(position).getBookingDate());
        holder.date.setText("Transaction Id : " + bookingHistoryList.get(position).getOrder_transaction_id());
        holder.time.setText("Booking Time Slot: " + bookingHistoryList.get(position).getTimeSlotName());
        holder.amount.setText("Service Charge:  " + bookingHistoryList.get(position).getBalance());
        holder.booking_statusText.setText("Booking Status  :" + bookingHistoryList.get(position).getStatusName());
        holder.orderNo.setText("Order No  :" + bookingHistoryList.get(position).getOrderNo());
        holder.booking_status.setText(bookingHistoryList.get(position).getStatusName());


        amountt = Float.parseFloat((bookingHistoryList.get(position).getBalance()));

        if (amountt > 0) {
            holder.booking_status.setText("pay now");
            holder.booking_status.setVisibility(View.VISIBLE);
            holder.booking_status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    paymentCallInterface.onPaymentClick(bookingHistoryList.get(holder.getAdapterPosition()).getOrder_transaction_id(),
                            bookingHistoryList.get(holder.getAdapterPosition()).getOrder_transaction_id(), bookingHistoryList.get(holder.getAdapterPosition()).getBalance());

                }
            });

            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, 101);
            }

        }else{
            holder.booking_status.setVisibility(View.GONE);
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
        this.bookingHistoryList = bookingHistory;
    }

    public void setpayMentCallBack(PaymentCallInterface paymentCallInterface) {
        this.paymentCallInterface = paymentCallInterface;
    }

    public interface OnActionItemClickListener {
        void onActionItemClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon, right_icon;
        TextView tv_category_name, date,transactionId, time, amount,booking_statusText,orderNo;
        Button booking_status;
        View row;

        public ViewHolder(View view) {
            super(view);
            row = view;
            tv_category_name = view.findViewById(R.id.tv_category_name);
            date = view.findViewById(R.id.date);
            time = view.findViewById(R.id.timeSlot);
            orderNo = view.findViewById(R.id.orderno);
            transactionId = view.findViewById(R.id.transactionId);
            amount = view.findViewById(R.id.amount);
            booking_statusText = view.findViewById(R.id.booking_statusText);
            booking_status = view.findViewById(R.id.booking_status);
        }
    }


    public interface PaymentCallInterface {
        public void onPaymentClick(String orderId, String orderNo, String balance);
    }

}

