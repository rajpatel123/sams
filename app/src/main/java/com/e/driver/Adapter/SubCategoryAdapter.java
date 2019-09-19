package com.e.driver.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.e.driver.Model.SubCategory.ServiceList;
import com.e.driver.R;

import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {
    private Context context;
    private List<ServiceList> subCategoryList;

    public SubCategoryAdapter(Context context, List<ServiceList> subCategoryList) {
        this.context = context;
        this.subCategoryList = subCategoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
         View view= LayoutInflater.from(context).inflate(R.layout.item_sub_category,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        ServiceList serviceList=subCategoryList.get(i);

        holder.serviceName.setText(serviceList.getServiceName());
        holder.servicePrice.setText(serviceList.getPrice());

    }

    @Override
    public int getItemCount() {
        return subCategoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView serviceName,servicePrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceName=itemView.findViewById(R.id.tv_sub_category_name);
            servicePrice=itemView.findViewById(R.id.tv_sub_category_price);

        }
    }
}
