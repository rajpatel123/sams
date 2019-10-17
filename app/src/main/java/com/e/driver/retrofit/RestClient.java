package com.e.driver.retrofit;

import com.e.driver.models.Category.ServiceResponse;
import com.e.driver.models.GetState.StateResponse;
import com.e.driver.models.LoginEmail.LoginEmailResponse;
import com.e.driver.models.LoginMobile.LoginMobileNumberResponse;
import com.e.driver.models.SubCategory.SubCategoryResponse;
import com.e.driver.models.TimeSlote.TimeSloteResponse;
import com.e.driver.models.assigendServices.AssignedServicesResponse;
import com.e.driver.models.bookings.Bookings;
import com.e.driver.models.cities.CityListResponce;
import com.e.driver.models.completedService.CompletedResponse;
import com.e.driver.models.onGoingService.OngoingResponse;
import com.e.driver.models.pendingServices.PendingResponse;
import com.e.driver.models.submit_otp.LoginMobileOtpResponse;

import okhttp3.ResponseBody;
import retrofit2.Callback;

public class RestClient {
    private static final String TAG = "RestClient";


    public static void getService(Callback<ServiceResponse> callback) {
        RetrofitClient.getClient().getService().enqueue(callback);
    }

    public static void getSubCategory(String category_id, Callback<SubCategoryResponse> callback) {
        com.e.driver.retrofit.RetrofitClient.getClient().getSubCategory(category_id).enqueue(callback);
    }

    public static void otpLogin(String mobileNumber, Callback<LoginMobileNumberResponse> callback) {
        RetrofitClient.getClient().otpLogin(mobileNumber).enqueue(callback);


    }

    public static void enterOtpSubmit(String phonenumber, String otp, Callback<LoginMobileOtpResponse> callback) {
        RetrofitClient.getClient().otpSubmit(phonenumber, otp).enqueue(callback);
    }

    public static void enterEmailId(String email, String password, Callback<LoginEmailResponse> callback) {
        RetrofitClient.getClient().loginEmail(email, password).enqueue(callback);
    }

    public static void timeSlot(Callback<TimeSloteResponse> callback) {
        RetrofitClient.getClient().getTime().enqueue(callback);
    }

    public static void selectState(Callback<StateResponse> callback) {

        RetrofitClient.getClient().getState().enqueue(callback);
    }

    public static void selectCity(String st_id, Callback<CityListResponce> callback) {

        RetrofitClient.getClient().getCity(st_id).enqueue(callback);
    }


    public static void getCustomerBookings(String cust_id, Callback<Bookings> callback) {

        RetrofitClient.getClient().getCustomerBookings(cust_id).enqueue(callback);
    }


    public static void bookService(String cust_id, String cust_name,
                                   String cust_email, String cust_login_mob,
                                   String cust_alter_mob,
                                   String ctype_id, String cust_address,
                                   String cust_landmark, String cust_pincode,
                                   String city_id, String state_id, String price,
                                   String prime_member_discount, String booking_date,
                                   String time_slot_id, String created_by, String modified_by,
                                   String service_category_id, String service_list_id, Callback<ResponseBody> callback) {

        RetrofitClient.getClient().bookNewService(cust_id, cust_name, cust_email, cust_login_mob,
                cust_alter_mob, ctype_id, cust_address, cust_landmark, cust_pincode, city_id, state_id,
                price, prime_member_discount, booking_date, time_slot_id, created_by, modified_by, service_category_id,
                service_list_id).enqueue(callback);

    }

    public static void getAssigned(String id, Callback<AssignedServicesResponse> callback){
        RetrofitClient.getClient().getAssignedResponse(id).enqueue(callback);

    }

    public static void pendingBooking(String id,String order ,String reason, Callback<ResponseBody> callback){
        RetrofitClient.getClient().PendingRequest(id,order,reason).enqueue(callback);

    }public static void completeBooking(String id,String order , Callback<ResponseBody> callback){
        RetrofitClient.getClient().CompleteRequest(id,order).enqueue(callback);

    }public static void processBookin(String id,String order, Callback<ResponseBody> callback){
        RetrofitClient.getClient().ProcessRequest(id,order).enqueue(callback);

    }

    public  static void getOngoing(String id, Callback<OngoingResponse>callback){
        RetrofitClient.getClient().getOngoingResponse(id).enqueue(callback);
    }

    public static void getCompleted(String id, Callback<CompletedResponse> callback){
        RetrofitClient.getClient().getCompletedResponse(id).enqueue(callback);
    }

    public static void getPending(String id, Callback<PendingResponse>callback){

        RetrofitClient.getClient().getPendingResponse(id).enqueue(callback);
    }

    public static void updatePaymentTransaction(String id, Callback<PendingResponse>callback){

        //RetrofitClient.getClient().updatePaymentStatus(id).enqueue(callback);
    }
}