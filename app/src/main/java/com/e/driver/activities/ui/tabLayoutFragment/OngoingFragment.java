package com.e.driver.activities.ui.tabLayoutFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.driver.R;
import com.e.driver.adapters.OngoingAdapter;
import com.e.driver.models.onGoingService.OngoingResponse;
import com.e.driver.retrofit.RestClient;
import com.e.driver.utils.Constants;
import com.e.driver.utils.SamsPrefs;
import com.e.driver.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OngoingFragment extends Fragment {

    private Context context;
    String emp_id;
    private RecyclerView ongoingRecyclerView;
    OngoingResponse ongoingResponse;
    OngoingAdapter ongoingAdapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tablayout_one, container, false);
        context = getActivity();

        emp_id= SamsPrefs.getString(context,Constants.CUST_ID);
        ongoingRecyclerView=root.findViewById(R.id.ongoingRecyclerView);
        getOngoingService();

        return  root;
    }

    private void getOngoingService() {
        Utils.showProgressDialog(context);
        RestClient.getOngoing(emp_id, new Callback<OngoingResponse>() {
            @Override
            public void onResponse(Call<OngoingResponse> call, Response<OngoingResponse> response) {
                if (response.code()==200){
                    Utils.dismissProgressDialog();

                    ongoingResponse=response.body();
                    if (ongoingResponse.getStatusType().equalsIgnoreCase("Success")&&
                            ongoingResponse.getData().getOngoingServiceList()!=null){
                        ongoingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        ongoingAdapter= new OngoingAdapter(getActivity(),ongoingResponse.getData().getOngoingServiceList());
                        ongoingRecyclerView.setAdapter(ongoingAdapter);



                    }

                }


            }

            @Override
            public void onFailure(Call<OngoingResponse> call, Throwable t) {

            }
        });



    }
}
