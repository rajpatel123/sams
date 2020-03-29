package com.samsapp.retrofit;


import com.samsapp.models.Category.ServiceResponse;
import com.samsapp.models.GetState.StateResponse;
import com.samsapp.models.LoginEmail.LoginEmailResponse;
import com.samsapp.models.LoginEmail.User;
import com.samsapp.models.LoginMobile.LoginMobileNumberResponse;
import com.samsapp.models.SubCategory.SubCategoryResponse;
import com.samsapp.models.TimeSlote.TimeSloteResponse;
import com.samsapp.models.assigendServices.AssignedServicesResponse;
import com.samsapp.models.bookings.Bookings;
import com.samsapp.models.cities.CityListResponce;
import com.samsapp.models.completedService.CompletedResponse;
import com.samsapp.models.onGoingService.OngoingResponse;
import com.samsapp.models.paymentTransaction.paymentTransactionRequest;
import com.samsapp.models.paymentTransaction.primePaymentTransaction;
import com.samsapp.models.pendingServices.PendingResponse;
import com.samsapp.models.primeMember.PrimeOrderResponse;
import com.samsapp.models.primePayment.PrimePaymentResponse;
import com.samsapp.models.submit_otp.LoginMobileOtpResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("api/Account/LoginByMobile")
    Call<LoginMobileNumberResponse> otpLogin(@Query("PhoneNumber") String mobileNumber);

    @GET("api/Account/EnterOtp")
    Call<LoginMobileOtpResponse> otpSubmit(@Query("PhoneNumber") String phonenumber, @Query("Otp") String otp);


    @Headers({"AuthToken: 12345", "LoginID: 1", "MobNo:  8586818454"})
    @POST("api/Customer/GetCategories")
    Call<ServiceResponse> getService();

    @GET("api/Customer/GetService")
    Call<SubCategoryResponse> getSubCategory(@Query("category_id") String category_id);


    @GET("api/Account/Login")
    Call<User> loginEmail(@Query("email") String email, @Query("password") String password);


    @GET("api/Booking/BookingHistory")
    Call<Bookings> getCustomerBookings(@Query("id") String id);

    @GET("api/Customer/GetTimeSlot")
    Call<TimeSloteResponse> getTime();

    @GET("api/Customer/GetState")
    Call<StateResponse> getState();

    @GET("api/Customer/GetCity")
    Call<CityListResponce> getCity(@Query("state_id") String st_id);

    @GET("api/Booking/BookService")
    Call<ResponseBody> bookNewService(@Query("cust_id") String cust_id,
                                      @Query("cust_name") String cust_name,
                                      @Query("cust_email") String cust_email,
                                      @Query("cust_login_mob") String cust_login_mob,
                                      @Query("cust_alter_mob") String cust_alter_mob,
                                      @Query("ctype_id") String ctype_id,
                                      @Query("cust_address") String cust_address,
                                      @Query("cust_landmark") String cust_landmark,
                                      @Query("cust_pincode") String cust_pincode,
                                      @Query("city_id") String city_id,
                                      @Query("state_id") String state_id,
                                      @Query("price") String price,
                                      @Query("prime_member_discount") String prime_member_discount,
                                      @Query("booking_date") String booking_date,
                                      @Query("time_slot_id") String time_slot_id,
                                      @Query("created_by") String created_by,
                                      @Query("modified_by") String modified_by,
                                      @Query("service_category_id") String service_category_id,
                                      @Query("service_list_id") String service_list_id,
                                      @Query("primeCount") String primeCount);

    @GET("api/Employee/NewRequestList")
    Call<AssignedServicesResponse> getAssignedResponse(@Query("id") String id);

    @GET("/api/Employee/ProcessRequestList")
    Call<OngoingResponse> getOngoingResponse(@Query("id") String id);

    @GET("api/Employee/CompleteRequestList")
    Call<CompletedResponse> getCompletedResponse(@Query("id") String id);

    @GET("api/Employee/PendingRequestList")
    Call<PendingResponse> getPendingResponse(@Query("id") String id);

    @GET("api/Employee/PendingRequest")
    Call<ResponseBody> PendingRequest(@Query("id") String id,
                                      @Query("orderno") String order,
                                      @Query("reason") String message);

    @GET("api/Employee/CompleteRequest")
    Call<ResponseBody> CompleteRequest(@Query("id") String id,
                                       @Query("orderno") String order);

    @GET("api/Employee/ProcessRequest")
    Call<ResponseBody> ProcessRequest(@Query("id") String id,
                                      @Query("orderno") String order);


    @GET("api/Payment/PaymentDetails")
    Call<paymentTransactionRequest> updatePaymentStatus(
            @Query("MID") String mid,
    @Query("TXNID") String txnid,
    @Query("ORDERID") String orderId,
    @Query("BANKTXNID") String banktxnid,
    @Query("TXNAMOUNT") String txnAmount,
    @Query("CURRENCY") String currency,
    @Query("STATUS") String status,
    @Query("RESPCODE") String response,
    @Query("RESPMSG") String resMSG,
    @Query("Date") String date,
    @Query("GATEWAYNAME") String gatewayName,
    @Query("BANKNAME") String bankName,
    @Query("PAYTMCHECKSUM") String checksum,
    @Query("PAYMENTMODE") String payMode,
    @Query("email") String email,
    @Query("orderno") String orderNo);

    @GET("api/order/getorderno")
    Call<PrimeOrderResponse>getGenOrderNum();


    @GET("api/Payment/PrimePaymentDetails")
    Call<PrimePaymentResponse> updatePrimePaymentStatus(
            @Query("MID") String mid,
            @Query("TXNID") String txnid,
            @Query("ORDERID") String orderId,
            @Query("BANKTXNID") String banktxnid,
            @Query("TXNAMOUNT") String txnAmount,
            @Query("CURRENCY") String currency,
            @Query("STATUS") String status,
            @Query("RESPCODE") String response,
            @Query("RESPMSG") String resMSG,
            @Query("Date") String date,
            @Query("GATEWAYNAME") String gatewayName,
            @Query("BANKNAME") String bankName,
            @Query("PAYTMCHECKSUM") String checksum,
            @Query("PAYMENTMODE") String payMode,
            @Query("email") String email,
            @Query("cust_id") String cust_id,
            @Query("cust_name") String cust_name,
            @Query("cust_login_mob") String cust_login_mob,
            @Query("cust_alter_mob") String cust_alter_mob,
            @Query("cust_address") String cust_address,
            @Query("cust_landmark") String cust_landmark,
            @Query("cust_pincode") String cust_pincode,
            @Query("city_id") String city_id,
            @Query("state_id") String state_id);

}