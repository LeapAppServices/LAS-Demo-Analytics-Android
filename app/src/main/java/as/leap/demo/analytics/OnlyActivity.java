package as.leap.demo.analytics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;

import as.leap.LASAnalytics;
import as.leap.LASConfig;

public class OnlyActivity extends AppCompatActivity {

    private static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only);

        count++;

        findViewById(R.id.open_new_page_button).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                it.setClass(OnlyActivity.this, OnlyActivity.class);
                startActivity(it);
            }
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("activity " + count);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        
        //session
        LASAnalytics.onResume(this);

        //page view
        LASAnalytics.onPageStart("activity " + count);
    }

    @Override
    protected void onPause() {
        super.onPause();

        //session
        LASAnalytics.onPause(this);

        //page view
        LASAnalytics.onPageEnd("activity " + count);
    }
}
