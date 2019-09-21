package com.e.driver.retrofit;

import com.e.driver.models.Category.ServiceResponse;
import com.e.driver.models.LoginMobile.OtpLoginResponse;
import com.e.driver.models.SubCategory.SubCategoryResponse;

import okhttp3.RequestBody;
import retrofit2.Callback;

public class RestClient {
    private static final String TAG = "RestClient";


   public  static void getService(Callback<ServiceResponse> getServiceCallbackResponse) {
        com.e.driver.retrofit.RetrofitClient.getClient().getService().enqueue(getServiceCallbackResponse);
}
    public static void getSubCategory(String category_id,Callback<SubCategoryResponse> callback){
       com.e.driver.retrofit.RetrofitClient.getClient().getSubCategory(category_id).enqueue(callback);
    }

    public static void otpLogin(String mobileNumber, Callback<OtpLoginResponse> callback){
       RetrofitClient.getClient().otpLogin(mobileNumber).enqueue(callback);


    }



}
