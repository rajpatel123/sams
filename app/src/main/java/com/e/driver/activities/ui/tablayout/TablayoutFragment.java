package com.e.driver.activities.ui.tablayout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.driver.R;
import com.e.driver.activities.ui.tabLayoutFragment.OngoingFragment;
import com.e.driver.activities.ui.tabLayoutFragment.TabLayoutFragmentThree;
import com.e.driver.activities.ui.tabLayoutFragment.TabLayoutFragmentTwo;

public class TablayoutFragment extends Fragment {
    private Context context;
    TabLayout tabLayout;
    OngoingFragment onGoingFragment;
    TabLayoutFragmentTwo tabLayoutFragmentTwo;
    TabLayoutFragmentThree tabLayoutFragmentThree;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tablayout, container, false);
        context = getActivity();
        tabLayout=root.findViewById(R.id.tabLayout);
        setUpTablayout();



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

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
        onGoingFragment = new OngoingFragment();
        tabLayoutFragmentTwo = new TabLayoutFragmentTwo();
        tabLayoutFragmentThree = new TabLayoutFragmentThree();
        tabLayout.addTab(tabLayout.newTab().setText("Pending"));
        tabLayout.addTab(tabLayout.newTab().setText("Ongoing"));
        tabLayout.addTab(tabLayout.newTab().setText("Completed"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);




    }
    public  void setCurrentTabFragment(int tabPosition){
        switch(tabPosition){
            case 0:

                break;


            case 1:
                break;


            case 2:
                break;




        }


    }
}



