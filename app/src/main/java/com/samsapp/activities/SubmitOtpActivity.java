package com.samsapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.samsapp.R;
import com.samsapp.models.submit_otp.LoginMobileOtpResponse;
import com.samsapp.retrofit.RestClient;
import com.samsapp.utils.Constants;
import com.samsapp.utils.SamsPrefs;
import com.samsapp.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitOtpActivity extends AppCompatActivity {

    String mobileNumber;
    @BindView(R.id.enterOtp)
    EditText submitOtp;
    @BindView(R.id.otp_submit)
    Button submitOtpBtn;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_otp);
        ButterKnife.bind(this);
//        getSupportActionBar().hide();


        submitOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                submitOtpApi();
            }
        });

    }

    private boolean submitOtpApi() {
        mobileNumber = SamsPrefs.getString(getApplicationContext(), Constants.MOBILE_NUMBER);
        String newOtp = submitOtp.getText().toString().trim();


        if (TextUtils.isEmpty(newOtp.trim()) || newOtp.length() < 6) {
            submitOtp.setError("Please Enter Valid OTP");
            return false;
        }
        if (!TextUtils.isEmpty(mobileNumber) && !TextUtils.isEmpty(newOtp)) {
            Utils.showProgressDialog(this);
            RestClient.enterOtpSubmit(mobileNumber, newOtp, new Callback<LoginMobileOtpResponse>() {

                @Override
                public void onResponse(Call<LoginMobileOtpResponse> call, Response<LoginMobileOtpResponse> response) {
                    Utils.dismissProgressDialog();


                    if (response.code()==200 && !response.body().getStatusType().equalsIgnoreCase("Fail")){
                        LoginMobileOtpResponse loginMobileOtpResponse = response.body();

                        intent = new Intent(SubmitOtpActivity.this, DashBoardNewactivity.class);
                        SamsPrefs.putBoolean(SubmitOtpActivity.this, Constants.LOGGEDIN, true);
                        SamsPrefs.putString(SubmitOtpActivity.this, Constants.MOBILE_NUMBER, loginMobileOtpResponse.getData().getUser().getMobileNo());
                        SamsPrefs.putString(SubmitOtpActivity.this, Constants.ROLE, loginMobileOtpResponse.getData().getUser().getRoleID());
                        SamsPrefs.putString(getApplicationContext(), Constants.CTYPE_ID, loginMobileOtpResponse.getData().getUser().getCustomerTypeID());
                        SamsPrefs.putString(getApplicationContext(), Constants.ISPRIME, loginMobileOtpResponse.getData().getUser().getPrime());
                        SamsPrefs.putString(getApplicationContext(), Constants.ISPRIME_DATE, loginMobileOtpResponse.getData().getUser().getPrimeEndDate());
                        SamsPrefs.putString(getApplicationContext(), Constants.PRIME_DISCOUNT, loginMobileOtpResponse.getData().getUser().getPrime_Membership_Discount());
                        SamsPrefs.putString(getApplicationContext(), Constants.NAME, loginMobileOtpResponse.getData().getUser().getUserName());
                        SamsPrefs.putString(getApplicationContext(),Constants.EMAIL,loginMobileOtpResponse.getData().getUser().getUserEmail());
                        SamsPrefs.putString(getApplicationContext(), Constants.CUST_ID, loginMobileOtpResponse.getData().getUser().getLoginID());
                        SamsPrefs.putString(getApplicationContext(), Constants.ADDRESSS,  loginMobileOtpResponse.getData().getUser().getAddress());
                        SamsPrefs.putString(getApplicationContext(), Constants.LANDMARK,  loginMobileOtpResponse.getData().getUser().getLandmark());


                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(SubmitOtpActivity.this, "Unable to login, try again later!", Toast.LENGTH_LONG).show();

                    }


                }

                @Override
                public void onFailure(Call<LoginMobileOtpResponse> call, Throwable t) {
                    Utils.dismissProgressDialog();
                    Toast.makeText(SubmitOtpActivity.this, "Unable to login, try again later!", Toast.LENGTH_LONG).show();

                }
            });
        } else {
            Toast.makeText(SubmitOtpActivity.this, "Unable to login, try again later!", Toast.LENGTH_LONG).show();
        }

        return true;


    }
}