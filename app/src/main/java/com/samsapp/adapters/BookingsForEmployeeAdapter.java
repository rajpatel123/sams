//package com.samsapp.adapters;
//
//import android.app.Activity;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.samsapp.R;
//import com.samsapp.models.bookingforemployee.NewServiceList;
//import com.samsapp.models.bookings.BookingHistory;
//
//import java.util.List;
//
//public class BookingsForEmployeeAdapter extends RecyclerView.Adapter<BookingsForEmployeeAdapter.ViewHolder> {
//
//    Activity context;
//    List<NewServiceList> newServiceLists;
//    OnActionItemClickListener listener;
//    float amountt;
//
//    public BookingsForEmployeeAdapter(Activity context, List<NewServiceList> bookingHistoryList) {
//        this.context = context;
//        this.newServiceLists = bookingHistoryList;
//    }
//
//    @Override
//    public BookingsForEmployeeAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_booking, viewGroup, false);
//        return new BookingsForEmployeeAdapter.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(final BookingsForEmployeeAdapter.ViewHolder holder, int position) {
//        holder.tv_category_name.setText(newServiceLists.get(position).getServiceName());
//        holder.date.setText("Booking Date : "+newServiceLists.get(position).getBookingDate());
//        holder.time.setText("Booking Time Slot: "+newServiceLists.get(position).getTimeSlotName());
//        holder.amount.setText("Service Charge:  INR "+newServiceLists.get(position).getBalance());
//        holder.booking_status.setText(newServiceLists.get(position).getStatusName());
//
//        amountt = Float.parseFloat((newServiceLists.get(position).getBalance()));
//        if (amountt > 0) {
//            holder.booking_status.setText("paynow");
//
//        }
////        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                if (listener != null) {
////                    listener.onActionItemClick(holder.getAdapterPosition());
////                }
////            }
////        });
//
//    }
//
//
//
//    @Override
//    public int getItemCount() {
//        if (bookingHistoryList != null && bookingHistoryList.size() > 0)
//            return bookingHistoryList.size();
//        return 0;
//    }
//
//    public void setBookingData(List<BookingHistory> bookingHistory) {
//        this.bookingHistoryList=bookingHistory;
//    }
//
//    public interface OnActionItemClickListener {
//        void onActionItemClick(int position);
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView icon, right_icon;
//        TextView tv_category_name, date, time, amount;
//        Button booking_status;
//        View row;
//
//        public ViewHolder(View view) {
//            super(view);
//            row = view;
//            tv_category_name = view.findViewById(R.id.tv_category_name);
//            date = view.findViewById(R.id.date);
//            time = view.findViewById(R.id.timeSlot);
//            amount = view.findViewById(R.id.amount);
//            booking_status =  view.findViewById(R.id.booking_status);
//        }
//    }
//
//}
//
