package com.e.driver.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.e.driver.Adapter.CategoryAdapter;
import com.e.driver.Adapter.SubCategoryAdapter;
import com.e.driver.Model.SubCategory.SubCategoryResponse;
import com.e.driver.R;
import com.e.driver.Retrofit.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCategoryActivity extends AppCompatActivity {

    RecyclerView rv_SubCategory;
    SubCategoryResponse subCategoryResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        RestClient.getSubCategory("id", new Callback<SubCategoryResponse>() {
            @Override
            public void onResponse(Call<SubCategoryResponse> call, Response<SubCategoryResponse> response) {
                if (response.code()==200){
                    subCategoryResponse=response.body();

                    if (subCategoryResponse!=null && subCategoryResponse.getServiceList()!=null ){
                        rv_SubCategory.setLayoutManager(new GridLayoutManager(SubCategoryActivity.this,2));
                        SubCategoryAdapter subCategoryAdapter = new SubCategoryAdapter(SubCategoryActivity.this, subCategoryResponse.getServiceList());
                        rv_SubCategory.setAdapter(subCategoryAdapter);
                        subCategoryAdapter.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void onFailure(Call<SubCategoryResponse> call, Throwable t) {

            }
        });

    }
}
