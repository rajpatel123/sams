package com.e.driver.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.e.driver.R;
import com.e.driver.models.pendingServices.PendingSerivceList;

import java.util.List;

public class PendingAdapter extends RecyclerView.Adapter<PendingAdapter.ViewHolder> {

    private Context context;
    private List<PendingSerivceList> pendingList;
    public PendingAdapter(Context context, List<PendingSerivceList> pendingList) {
        this.context = context;
        this.pendingList = pendingList;
    }

    @NonNull
    @Override
    public PendingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view=LayoutInflater.from(context).inflate(R.layout.item_pending,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingAdapter.ViewHolder holder, int i) {
        PendingSerivceList pendingSerivceList=pendingList.get(i);
        holder.pendingServiceName.setText(""+pendingSerivceList.getServiceName());
        holder.pending_Cust_name.setText(""+pendingSerivceList.getCustName());
        holder.pending_Add_Name.setText(""+pendingSerivceList.getCustAddress());

    }

    @Override
    public int getItemCount() {
        return pendingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView pendingServiceName,pending_Cust_name,pending_Add_Name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pendingServiceName=itemView.findViewById(R.id.tv_pendingServiceName);
            pending_Cust_name=itemView.findViewById(R.id.pending_Cust_Name);
            pending_Add_Name=itemView.findViewById(R.id.pending_Cus_Add);

        }
    }
}
