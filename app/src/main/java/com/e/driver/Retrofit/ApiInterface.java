package com.e.driver.Retrofit;


import com.e.driver.Model.Category.ServiceResponse;
import com.e.driver.Model.SubCategory.SubCategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET(value = "Customer/GetCategories")
     Call<ServiceResponse>getService();

    @GET("Customer/GetService")
    Call<SubCategoryResponse>getSubCategory(@Query("category_id")String category_id);

}