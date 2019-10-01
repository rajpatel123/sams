package com.e.driver.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.e.driver.R;
import com.e.driver.models.completedService.CompleteServiceList;

import java.util.List;

public class CompletedAdapter extends RecyclerView.Adapter<CompletedAdapter.ViewHolder> {

    private Context context;
    private List<CompleteServiceList> completedList;
    public CompletedAdapter(Context context, List<CompleteServiceList> completedList) {
        this.context = context;
        this.completedList = completedList;
    }

    @NonNull
    @Override

    public CompletedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_completed,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompletedAdapter.ViewHolder holder, int i) {
        CompleteServiceList completeServiceList=completedList.get(i);
        holder.completedServiceName.setText(""+completeServiceList.getServiceName());
        holder.Complete_Cust_name.setText(""+completeServiceList.getCustName());
        holder.Complete_Add_Name.setText(""+completeServiceList.getCustAddress());

    }

    @Override
    public int getItemCount() {
        return completedList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView completedServiceName,Complete_Cust_name,Complete_Add_Name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            completedServiceName=itemView.findViewById(R.id.tv_completedServiceName);
            Complete_Cust_name=itemView.findViewById(R.id.completed_Cust_Name);
            Complete_Add_Name=itemView.findViewById(R.id.completed_Cus_Add);
        }
    }
}
