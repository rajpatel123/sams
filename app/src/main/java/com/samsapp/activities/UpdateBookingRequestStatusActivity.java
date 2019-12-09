package com.samsapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.samsapp.R;
import com.samsapp.activities.ui.tabLayoutFragment.AssignedFragment;
import com.samsapp.adapters.AssignedAdapter;
import com.samsapp.models.assigendServices.AssignedServicesResponse;
import com.samsapp.retrofit.RestClient;
import com.samsapp.utils.Constants;
import com.samsapp.utils.SamsPrefs;
import com.samsapp.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateBookingRequestStatusActivity extends AppCompatActivity {
    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.statusup)
    Spinner statusupSpinner;

    @BindView(R.id.buttonupdate)
    TextView buttonupdate;

    @BindView(R.id.messageup)
    EditText messageup;
    private String status;
    private String orderid;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_booking_request_status);
        ButterKnife.bind(this);

        userId = SamsPrefs.getString(this, Constants.CUST_ID);
        if (getIntent().hasExtra("serviceName")) {
            title.setText("" + getIntent().getStringExtra("serviceName"));
            orderid = getIntent().getStringExtra("orderId");

        }


        statusupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] stausArray = getResources().getStringArray(R.array.status);

                status = stausArray[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch (status) {
                    case "Pending":
                        if (!TextUtils.isEmpty(messageup.getText().toString().trim())) {
                            String message = messageup.getText().toString();
                            Utils.showProgressDialog(UpdateBookingRequestStatusActivity.this);

                            RestClient.pendingBooking(userId, orderid, message, new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                    if (response.code() == 200) {
                                        Utils.dismissProgressDialog();
                                        try {
                                            JSONObject jsonObject = new JSONObject(response.body().string());
                                            if (jsonObject.has("Status_Type") &&
                                                    jsonObject.getString("Status_Type").equalsIgnoreCase("success")){
                                                Toast.makeText(UpdateBookingRequestStatusActivity.this, "Booking updated successfully", Toast.LENGTH_LONG).show();

                                            }

                                            finish();

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Utils.dismissProgressDialog();
                                }
                            });
                        }else{
                            Toast.makeText(UpdateBookingRequestStatusActivity.this, "Please add remarks", Toast.LENGTH_LONG).show();
                        }
                            break;

                            case "Complete":
                                RestClient.completeBooking(userId,orderid, new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                        if (response.code() == 200) {
                                            Utils.dismissProgressDialog();
                                            try {
                                                JSONObject jsonObject = new JSONObject(response.body().string());
                                                if (jsonObject.has("Status_Type") &&
                                                        jsonObject.getString("Status_Type").equalsIgnoreCase("success")){
                                                    Toast.makeText(UpdateBookingRequestStatusActivity.this, "Booking updated successfully", Toast.LENGTH_LONG).show();
                                                    finish();

                                                }

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        Utils.dismissProgressDialog();
                                    }
                                });
                                break;

                            case "Process":
                                RestClient.processBookin(userId,orderid, new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                        if (response.code() == 200) {
                                            Utils.dismissProgressDialog();
                                            try {
                                                JSONObject jsonObject = new JSONObject(response.body().string());
                                                if (jsonObject.has("Status_Type") &&
                                                        jsonObject.getString("Status_Type").equalsIgnoreCase("success")){
                                                    Toast.makeText(UpdateBookingRequestStatusActivity.this, "Booking updated successfully", Toast.LENGTH_LONG).show();
                                                    finish();

                                                }

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        Utils.dismissProgressDialog();
                                    }
                                });
                                break;

                        }




                }
            });
        }
    }
