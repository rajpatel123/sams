package com.e.driver.activities.ui.tabLayoutFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.driver.R;
import com.e.driver.adapters.AssignedAdapter;
import com.e.driver.models.assigendServices.AssignedServicesResponse;
import com.e.driver.retrofit.RestClient;
import com.e.driver.utils.Constants;
import com.e.driver.utils.SamsPrefs;
import com.e.driver.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssignedFragment extends Fragment {

    private Context context;
    String emp_id;
    private RecyclerView assignRecyclerView;
    AssignedAdapter assignedAdapter;
    AssignedServicesResponse assignedServicesResponse;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tablayout_assigned, container, false);
        context = getActivity();

        emp_id=SamsPrefs.getString(context,"cType");

        assignRecyclerView=root.findViewById(R.id.assignRecyclerView);


        getAssignedService();

        return  root;
    }

    private void getAssignedService() {

        Utils.showProgressDialog(context);

        RestClient.getAssigned(emp_id, new Callback<AssignedServicesResponse>() {
            @Override
            public void onResponse(Call<AssignedServicesResponse> call, Response<AssignedServicesResponse> response) {

                if (response.code()==200){
                    Utils.dismissProgressDialog();

                    assignedServicesResponse=response.body();
                    if (assignedServicesResponse.getStatusType().equalsIgnoreCase("Success")&& assignedServicesResponse.getData().getNewServiceList()!=null){

                        assignRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        assignedAdapter=new AssignedAdapter(getActivity(),assignedServicesResponse.getData().getNewServiceList());
                        assignRecyclerView.setAdapter(assignedAdapter);
                    }



                }



                Log.d("Response",response.body().toString());

            }

            @Override
            public void onFailure(Call<AssignedServicesResponse> call, Throwable t) {

            }
        });




    }
}
