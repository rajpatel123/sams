package com.e.driver.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.e.driver.R;
import com.e.driver.models.onGoingService.OngoingServiceList;

import java.util.List;

public class OngoingAdapter extends RecyclerView.Adapter<OngoingAdapter.ViewHolder> {


    private Context context;
    private List<OngoingServiceList> ongoingList;
    public OngoingAdapter(Context context, List<OngoingServiceList> ongoingList) {
        this.context = context;
        this.ongoingList = ongoingList;
    }


    @NonNull
    @Override
    public OngoingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

       View view= LayoutInflater.from(context).inflate(R.layout.item_ongoing,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OngoingAdapter.ViewHolder holder, int i) {
        OngoingServiceList ongoingServiceList=ongoingList.get(i);
        holder.ongoingServiceName.setText(""+ongoingServiceList.getServiceName());
        holder.onGoingName.setText(""+ongoingServiceList.getCustName());
        holder.ongoingAdd.setText(""+ongoingServiceList.getCustAddress());



    }

    @Override
    public int getItemCount() {
        return ongoingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView ongoingServiceName,onGoingName,ongoingAdd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ongoingServiceName=itemView.findViewById(R.id.tv_ongoingServiceName);
            onGoingName=itemView.findViewById(R.id.ongoing_Cust_Name);
            ongoingAdd=itemView.findViewById(R.id.ongoing_Cus_Add);
        }
    }
}
