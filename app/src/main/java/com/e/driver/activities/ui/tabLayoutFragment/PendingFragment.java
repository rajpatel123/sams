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
import android.widget.Toast;

import com.e.driver.R;
import com.e.driver.activities.UpdateBookingRequestStatusActivity;
import com.e.driver.adapters.PendingAdapter;
import com.e.driver.interfaces.OnBookingClickListener;
import com.e.driver.models.pendingServices.PendingResponse;
import com.e.driver.retrofit.RestClient;
import com.e.driver.utils.Constants;
import com.e.driver.utils.SamsPrefs;
import com.e.driver.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingFragment extends Fragment implements OnBookingClickListener {
    private Context context;

    String emp_id;
    private RecyclerView pendingRecyclerView;
    private PendingResponse pendingResponse;
    private PendingAdapter pendingAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tablayout_two, container, false);
        context = getActivity();

        pendingRecyclerView = root.findViewById(R.id.pendingRecyclerView);
        emp_id = SamsPrefs.getString(context, Constants.CUST_ID);

        getPending();

        return root;
    }

    private void getPending() {

        Utils.showProgressDialog(context);
        RestClient.getPending(emp_id, new Callback<PendingResponse>() {
            @Override
            public void onResponse(Call<PendingResponse> call, Response<PendingResponse> response) {
                if (response.code() == 200) {
                    Utils.dismissProgressDialog();

                    pendingResponse = response.body();
                    if (pendingResponse.getStatusType().equalsIgnoreCase("Success") &&
                            pendingResponse.getData().getPendingSerivceList() != null) {

                        pendingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        pendingAdapter = new PendingAdapter(getActivity(), pendingResponse.getData().getPendingSerivceList());
                        pendingAdapter.setOnBookingClick(PendingFragment.this);
                        pendingRecyclerView.setAdapter(pendingAdapter);
                    }
                } else if (response.code() == 400) {
                    Utils.dismissProgressDialog();
                    Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<PendingResponse> call, Throwable t) {
                Utils.dismissProgressDialog();
            }
        });


    }


    @Override
    public void onBookingClick(int position) {
        Intent intent = new Intent(getActivity(), UpdateBookingRequestStatusActivity.class);
        intent.putExtra("serviceName", pendingResponse.getData().getPendingSerivceList().get(position).getServiceName());
        intent.putExtra("orderId", pendingResponse.getData().getPendingSerivceList().get(position).getOrderNo());
        getActivity().startActivity(intent);
    }
}

