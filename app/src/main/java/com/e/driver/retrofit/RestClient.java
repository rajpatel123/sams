package com.e.driver.Retrofit;

import com.e.driver.Model.Category.ServiceResponse;
import com.e.driver.Model.SubCategory.SubCategoryResponse;
import com.e.driver.models.Category.ServiceResponse;

import retrofit2.Callback;

public class RestClient {
    private static final String TAG = "RestClient";

   public  static void getService(Callback<ServiceResponse> getServiceCallbackResponse){
        com.e.driver.retrofit.RetrofitClient.getClient().getService().enqueue(getServiceCallbackResponse);
}
    public static void getSubCategory(String category_id,Callback<SubCategoryResponse> callback){
       com.e.driver.retrofit.RetrofitClient.getClient().getSubCategory(category_id).enqueue(callback);
    }



}
