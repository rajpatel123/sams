package com.e.driver.activities.ui.tabLayoutFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.e.driver.R;
import com.e.driver.activities.UpdateBookingRequestStatusActivity;
import com.e.driver.adapters.OngoingAdapter;
import com.e.driver.interfaces.OnBookingClickListener;
import com.e.driver.models.onGoingService.OngoingResponse;
import com.e.driver.retrofit.RestClient;
import com.e.driver.utils.Constants;
import com.e.driver.utils.SamsPrefs;
import com.e.driver.utils.Utils;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OngoingFragment extends Fragment implements OnBookingClickListener {

    private Context context;
    String emp_id;
    private RecyclerView ongoingRecyclerView;
    OngoingResponse ongoingResponse;
    OngoingAdapter ongoingAdapter;
    TextView ongoingText;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tablayout_one, container, false);
        context = getActivity();

        emp_id= SamsPrefs.getString(context,Constants.CUST_ID);
        ongoingRecyclerView=root.findViewById(R.id.ongoingRecyclerView);
        ongoingText=root.findViewById(R.id.ongoingText);
        getOngoingService();

        return  root;
    }

    private void getOngoingService() {
        Utils.showProgressDialog(context);
        RestClient.getOngoing(emp_id, new Callback<OngoingResponse>() {
            @Override
            public void onResponse(Call<OngoingResponse> call, Response<OngoingResponse> response) {
                Utils.dismissProgressDialog();

                if (response.code()==200){
                    ongoingResponse=response.body();
                    if (ongoingResponse.getStatusType().equalsIgnoreCase("Success")&&
                            ongoingResponse.getData().getOngoingServiceList()!=null){
                        ongoingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        ongoingAdapter= new OngoingAdapter(getActivity(),ongoingResponse.getData().getOngoingServiceList());
                        ongoingAdapter.setOnBookingListener(OngoingFragment.this);
                        ongoingRecyclerView.setAdapter(ongoingAdapter);
                        ongoingText.setVisibility(View.GONE);
                    }
                    else {
                        ongoingRecyclerView.setVisibility(View.GONE);
                        ongoingText.setVisibility(View.VISIBLE);
                        //handle visibility
                    }
                }
            }

            @Override
            public void onFailure(Call<OngoingResponse> call, Throwable t) {
                Utils.dismissProgressDialog();
            }
        });



    }
    @Override
    public void onBookingClick(int position) {
        Intent intent = new Intent(getActivity(), UpdateBookingRequestStatusActivity.class);
        intent.putExtra("serviceName", ongoingResponse.getData().getOngoingServiceList().get(position).getServiceName());
        intent.putExtra("serviceName", ongoingResponse.getData().getOngoingServiceList().get(position).getOrderNo());
        getActivity().startActivity(intent);
    }
}
