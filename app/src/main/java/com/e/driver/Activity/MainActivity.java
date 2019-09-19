package com.e.driver.Activity;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.e.driver.Adapter.CategoryAdapter;
import com.e.driver.Model.Category.ServiceResponse;
import com.e.driver.R;
import com.e.driver.Retrofit.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ServiceResponse serviceResponse;
    RecyclerView rv_service;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_service = findViewById(R.id.rv_service);


        RestClient.getService(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {

                if (response.code() == 200) {
                    serviceResponse = response.body();

                    if (serviceResponse != null && serviceResponse.getCategories() != null) {
                        rv_service.setLayoutManager(new GridLayoutManager(MainActivity.this,2));

                        CategoryAdapter categoryAdapter = new CategoryAdapter(MainActivity.this, serviceResponse.getCategories());
                        rv_service.setAdapter(categoryAdapter);
                        categoryAdapter.notifyDataSetChanged();
                    }

                    Log.d("msg", serviceResponse.toString());


                }
            }


            @Override
            public void onFailure(Call<ServiceResponse> call, Throwable t) {

            }
        });


    }

}
