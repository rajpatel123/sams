package com.samsapp.activities.ui.home;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.samsapp.R;
import com.samsapp.activities.BookRequestActivity;
import com.samsapp.activities.MainActivity;
import com.samsapp.activities.SubCategoryActivity;
import com.samsapp.adapters.CategoryAdapter;
import com.samsapp.models.Category.ServiceResponse;
import com.samsapp.retrofit.RestClient;
import com.samsapp.utils.Constants;
import com.samsapp.utils.SamsPrefs;
import com.samsapp.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment implements CategoryAdapter.OnClickCategoryClick{

    ServiceResponse serviceResponse;
    RecyclerView rv_service;
    private Context context;
    private CategoryAdapter categoryAdapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
         context=getActivity();
        rv_service = root.findViewById(R.id.rv_service);
        serviceResponse = new ServiceResponse();
        rv_service.setLayoutManager(new GridLayoutManager(context, 2));
        categoryAdapter = new CategoryAdapter(context);
        categoryAdapter.setOnCategoryClickListener(this);
        rv_service.setAdapter(categoryAdapter);

        if (Utils.isInternetConnected(context)) {
            Utils.showProgressDialog(context);
            getAllcategories();
        } else {
            Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show();
        }

        return root;
    }

    private void getAllcategories() {
        RestClient.getService(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
                Utils.dismissProgressDialog();
                if (response.code() == 200) {
                    serviceResponse = response.body();
                    if (serviceResponse.getStatusType().equalsIgnoreCase("Success")  && serviceResponse.getData().getCategories() != null) {
                        categoryAdapter.setCategryData(serviceResponse.getData().getCategories());
                        categoryAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse> call, Throwable t) {
                Utils.dismissProgressDialog();
            }
        });

    }

    @Override
    public void onCategoryClick(int position) {
        Intent intent = new Intent(context, SubCategoryActivity.class);
        intent.putExtra(Constants.CAT_ID,serviceResponse.getData().getCategories().get(position).getCategoryId());
        SamsPrefs.putString(getActivity(),Constants.CATEGORY,serviceResponse.getData().getCategories().get(position).getCategoryName());
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);


    }
}
