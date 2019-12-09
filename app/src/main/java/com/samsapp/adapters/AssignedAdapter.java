package com.samsapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samsapp.R;
import com.samsapp.activities.ui.tabLayoutFragment.AssignedFragment;
import com.samsapp.interfaces.OnBookingClickListener;
import com.samsapp.models.assigendServices.AssignedServicesResponse;
import com.samsapp.models.assigendServices.NewServiceList;

import java.util.List;

public class AssignedAdapter extends RecyclerView.Adapter<AssignedAdapter.ViewHolder> {

    private Context context;
    private List<NewServiceList> assignedList;
    OnBookingClickListener onBookingClick;

    public AssignedAdapter(Context context, List<NewServiceList> assignedList) {
        this.context = context;
        this.assignedList = assignedList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

       View view= LayoutInflater.from(context).inflate(R.layout.item_assign_service,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i) {
        NewServiceList newServiceList=assignedList.get(i);
        holder.assignServiceName.setText(""+newServiceList.getServiceName());
        holder.assignName.setText(""+newServiceList.getCustName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBookingClick!=null){
                    onBookingClick.onBookingClick(holder.getAdapterPosition());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return assignedList.size();
    }

    public void setOnBookingClick(OnBookingClickListener onBookingClick) {
        this.onBookingClick=onBookingClick;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView assignServiceName, assignName;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            assignServiceName=itemView.findViewById(R.id.tv_assignedServiceName);
            assignName=itemView.findViewById(R.id.Cust_Name);
        }
    }
}
