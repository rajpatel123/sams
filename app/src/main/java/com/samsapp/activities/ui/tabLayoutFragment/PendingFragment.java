package com.samsapp.activities.ui.tabLayoutFragment;

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
import android.widget.Toast;

import com.samsapp.R;
import com.samsapp.activities.UpdateBookingRequestStatusActivity;
import com.samsapp.adapters.PendingAdapter;
import com.samsapp.interfaces.OnBookingClickListener;
import com.samsapp.models.pendingServices.PendingResponse;
import com.samsapp.retrofit.RestClient;
import com.samsapp.utils.Constants;
import com.samsapp.utils.SamsPrefs;
import com.samsapp.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingFragment extends Fragment implements OnBookingClickListener {
    private Context context;

    String emp_id;
    private RecyclerView pendingRecyclerView;
    private PendingResponse pendingResponse;
    private PendingAdapter pendingAdapter;
    TextView pendingText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tablayout_two, container, false);
        context = getActivity();

        pendingRecyclerView = root.findViewById(R.id.pendingRecyclerView);
        pendingText=root.findViewById(R.id.pendingText);
        emp_id = SamsPrefs.getString(context, Constants.CUST_ID);

        getPending();

        return root;
    }

    private void getPending() {

        Utils.showProgressDialog(context);
        RestClient.getPending(emp_id, new Callback<PendingResponse>() {
            @Override
            public void onResponse(Call<PendingResponse> call, Response<PendingResponse> response) {
                Utils.dismissProgressDialog();

                if (response.code() == 200) {
                    pendingResponse = response.body();
                    if (pendingResponse.getStatusType().equalsIgnoreCase("Success") &&
                            pendingResponse.getData().getPendingSerivceList() != null) {
                        pendingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        pendingAdapter = new PendingAdapter(getActivity(), pendingResponse.getData().getPendingSerivceList());
                        pendingAdapter.setOnBookingClick(PendingFragment.this);
                        pendingRecyclerView.setAdapter(pendingAdapter);
                        pendingText.setVisibility(View.GONE);
                    }
                } else{
                    pendingRecyclerView.setVisibility(View.GONE);
                    pendingText.setVisibility(View.VISIBLE);
                    //handle visibility
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

