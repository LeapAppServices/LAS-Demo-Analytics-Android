package com.maxleap.demo.analytics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.maxleap.MLAnalytics;

public class WithFragmentActivity extends AppCompatActivity {

    private int mCount = 1;
    private static final String EXTRA_COUNT = "count";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        findViewById(R.id.open_new_page_button).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mCount++;
                Fragment f = SimpleFragment.newInstance(mCount,
                        "SimpleFragment " + mCount);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment, f)
                        .setTransition(
                                FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(null).commit();

            }
        });

        if (savedInstanceState == null) {
            Fragment f = SimpleFragment.newInstance(mCount, "SimpleFragment "
                    + mCount);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment, f).commit();
        } else {
            mCount = savedInstanceState.getInt(EXTRA_COUNT);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(EXTRA_COUNT, mCount);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //session
        MLAnalytics.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //session
        MLAnalytics.onPause(this);
    }
}
