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

import com.e.driver.R;
import com.e.driver.activities.UpdateBookingRequestStatusActivity;
import com.e.driver.adapters.CompletedAdapter;
import com.e.driver.interfaces.OnBookingClickListener;
import com.e.driver.models.completedService.CompletedResponse;
import com.e.driver.retrofit.RestClient;
import com.e.driver.retrofit.RetrofitClient;
import com.e.driver.utils.Constants;
import com.e.driver.utils.SamsPrefs;
import com.e.driver.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompletedFragment extends Fragment implements OnBookingClickListener {

    private Context context;
    String emp_id;
    private RecyclerView comletedRecyclerView;
    CompletedResponse completedResponse;
    CompletedAdapter completedAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tablayout_three, container, false);
        context = getActivity();
        comletedRecyclerView = root.findViewById(R.id.completedRecyclerView);
        emp_id = SamsPrefs.getString(context, Constants.CUST_ID);
        getCompleted();

        return root;
    }

    private void getCompleted() {
        Utils.showProgressDialog(context);
        RestClient.getCompleted(emp_id, new Callback<CompletedResponse>() {
            @Override
            public void onResponse(Call<CompletedResponse> call, Response<CompletedResponse> response) {

                if (response.code() == 200) {
                    Utils.dismissProgressDialog();

                    completedResponse = response.body();
                    if (completedResponse.getStatusType().equalsIgnoreCase("Success") &&
                            completedResponse.getData().getCompleteServiceList() != null) {
                        comletedRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                        completedAdapter = new CompletedAdapter(getActivity(), completedResponse.getData().getCompleteServiceList());
                        completedAdapter.setOnBookingClick(CompletedFragment.this);

                        comletedRecyclerView.setAdapter(completedAdapter);


                    }

                }

            }

            @Override
            public void onFailure(Call<CompletedResponse> call, Throwable t) {
                Utils.dismissProgressDialog();
            }
        });

    }

    @Override
    public void onBookingClick(int position) {
        Intent intent = new Intent(getActivity(), UpdateBookingRequestStatusActivity.class);
        intent.putExtra("serviceName", completedResponse.getData().getCompleteServiceList().get(position).getServiceName());
        intent.putExtra("serviceName", completedResponse.getData().getCompleteServiceList().get(position).getOrderNo());
        getActivity().startActivity(intent);
    }
}

