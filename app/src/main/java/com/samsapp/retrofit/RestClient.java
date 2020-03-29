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
import retrofit2.Callback;

public class RestClient {
    private static final String TAG = "RestClient";


    public static void getService(Callback<ServiceResponse> callback) {
        RetrofitClient.getClient().getService().enqueue(callback);
    }

    public static void getSubCategory(String category_id, Callback<SubCategoryResponse> callback) {
        com.samsapp.retrofit.RetrofitClient.getClient().getSubCategory(category_id).enqueue(callback);
    }

    public static void otpLogin(String mobileNumber, Callback<LoginMobileNumberResponse> callback) {
        RetrofitClient.getClient().otpLogin(mobileNumber).enqueue(callback);


    }

    public static void enterOtpSubmit(String phonenumber, String otp, Callback<LoginMobileOtpResponse> callback) {
        RetrofitClient.getClient().otpSubmit(phonenumber, otp).enqueue(callback);
    }

    public static void enterEmailId(String email, String password, Callback<User> callback) {
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
                                   String service_category_id, String service_list_id, String primeCount,Callback<ResponseBody> callback) {

        RetrofitClient.getClient().bookNewService(cust_id, cust_name, cust_email, cust_login_mob,
                cust_alter_mob, ctype_id, cust_address, cust_landmark, cust_pincode, city_id, state_id,
                price, prime_member_discount, booking_date, time_slot_id, created_by, modified_by, service_category_id,
                service_list_id,primeCount).enqueue(callback);

    }

    public static void getAssigned(String id, Callback<AssignedServicesResponse> callback) {
        RetrofitClient.getClient().getAssignedResponse(id).enqueue(callback);

    }

    public static void pendingBooking(String id, String order, String reason, Callback<ResponseBody> callback) {
        RetrofitClient.getClient().PendingRequest(id, order, reason).enqueue(callback);

    }

    public static void completeBooking(String id, String order, Callback<ResponseBody> callback) {
        RetrofitClient.getClient().CompleteRequest(id, order).enqueue(callback);

    }

    public static void processBookin(String id, String order, Callback<ResponseBody> callback) {
        RetrofitClient.getClient().ProcessRequest(id, order).enqueue(callback);

    }

    public static void getOngoing(String id, Callback<OngoingResponse> callback) {
        RetrofitClient.getClient().getOngoingResponse(id).enqueue(callback);
    }

    public static void getCompleted(String id, Callback<CompletedResponse> callback) {
        RetrofitClient.getClient().getCompletedResponse(id).enqueue(callback);
    }

    public static void getPending(String id, Callback<PendingResponse> callback) {

        RetrofitClient.getClient().getPendingResponse(id).enqueue(callback);
    }

    public static void updatePaymentTransaction(String id, String txnid,
                                                String orderId, String banktxnid,
                                                String txnAmount, String currency,
                                                String status, String response,
                                                String resMSG, String date,
                                                String gatewayName, String bankName,
                                                String checksum, String payMode,
                                                String email,
                                                String orderNo,Callback<paymentTransactionRequest> callback) {
        RetrofitClient.getClient().updatePaymentStatus(id,txnid,orderId,banktxnid,txnAmount,currency,status,response,resMSG,date,gatewayName,bankName,
                checksum,payMode,email,orderNo).enqueue(callback);


    }

    public static  void genPrimeOrderNum(Callback<PrimeOrderResponse> callback){
                  RetrofitClient.getClient().getGenOrderNum().enqueue(callback);
    }
    public static void updatePrimePaymentTransaction(String id, String txnid,
                                                String orderId, String banktxnid,
                                                String txnAmount, String currency,
                                                String status, String response,
                                                String resMSG, String date,
                                                String gatewayName, String bankName,
                                                String checksum, String payMode,
                                                String email, String cust_id,
                                                     String cust_name, String cust_login_mob,String cust_alter_mob,
                                                     String cust_address,String cust_landmark, String cust_pincode,
                                                     String city_id,String state_id,Callback<PrimePaymentResponse>callback){

        RetrofitClient.getClient().updatePrimePaymentStatus(id,txnid,orderId,banktxnid,txnAmount,currency,status,response,resMSG,date,gatewayName,bankName,
                checksum,payMode,email,cust_id,cust_name,cust_login_mob,cust_alter_mob,cust_address,
                cust_landmark,cust_pincode,city_id,state_id).enqueue(callback);
    }


}
