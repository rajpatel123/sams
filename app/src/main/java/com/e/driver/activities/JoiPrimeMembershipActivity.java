package com.e.driver.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.e.driver.R;
import com.e.driver.utils.Constants;

public class JoiPrimeMembershipActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joi_prime_membership);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getIntent().hasExtra(Constants.PRIME_MEMBER)){
            toolbar.setTitle(getIntent().getStringExtra(Constants.PRIME_MEMBER));
        }

    }
}
