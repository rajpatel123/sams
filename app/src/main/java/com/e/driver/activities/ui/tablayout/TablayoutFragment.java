package com.e.driver.activities.ui.tablayout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.driver.R;
import com.e.driver.activities.DashBoardNewactivity;
import com.e.driver.activities.ui.tabLayoutFragment.AssignedFragment;
import com.e.driver.activities.ui.tabLayoutFragment.OngoingFragment;
import com.e.driver.activities.ui.tabLayoutFragment.CompletedFragment;
import com.e.driver.activities.ui.tabLayoutFragment.PendingFragment;

public class TablayoutFragment extends Fragment {
    private Context context;
    TabLayout tabLayout;
    OngoingFragment onGoingFragment;
    PendingFragment pendingFragment;
    CompletedFragment completedFragment;
    DashBoardNewactivity dashBoardNewactivity;
    AssignedFragment assignedFragment;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dashBoardNewactivity = (DashBoardNewactivity) getActivity();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tablayout, container, false);
        context = getActivity();
        tabLayout=root.findViewById(R.id.tabLayout);
        setUpTablayout();
        replaceFragmentNew(assignedFragment);



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return root;



    }
    private void setUpTablayout(){
        assignedFragment = new AssignedFragment();
        onGoingFragment = new OngoingFragment();
        pendingFragment = new PendingFragment();
        completedFragment = new CompletedFragment();
        tabLayout.addTab(tabLayout.newTab().setText("Assigned"));
        tabLayout.addTab(tabLayout.newTab().setText("Ongoing"));
        tabLayout.addTab(tabLayout.newTab().setText("pending"));
        tabLayout.addTab(tabLayout.newTab().setText("Completed"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);




    }
    public  void setCurrentTabFragment(int tabPosition){
        switch(tabPosition){
            case 0:

           replaceFragmentNew(assignedFragment);
                break;


            case 1:
                replaceFragmentNew(onGoingFragment);
                break;


            case 2:
                replaceFragmentNew(pendingFragment);
                break;
            case 3:
                replaceFragmentNew(completedFragment);
                break;



        }





    }
    private void replaceFragmentNew(Fragment fragment) {
        final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_inside_home, fragment, "NewFragmentTag");
        ft.commit();
    }
}



