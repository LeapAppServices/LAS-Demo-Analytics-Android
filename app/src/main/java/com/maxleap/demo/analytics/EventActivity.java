package com.maxleap.demo.analytics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.maxleap.MLAnalytics;
import com.maxleap.MLLog;

import java.util.HashMap;
import java.util.Map;

public class EventActivity extends AppCompatActivity {

    public static final String TAG = EventActivity.class.getName();
    int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        findViewById(R.id.send_event_button).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Map<String, String> dimensions = new HashMap<String, String>();
                dimensions.put("code", "404");
                dimensions.put("message", "page not found");
                mCount++;
                MLAnalytics.logEvent("error", mCount, dimensions);

                MLLog.t("trigger event " + mCount + " times");
            }
        });

        final EditText eventKeyEditText = (EditText) findViewById(R.id.event_key_edit_text);
        final EditText eventValueEditText = (EditText) findViewById(R.id.event_value_edit_text);
        findViewById(R.id.send_custom_event_button).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(eventKeyEditText.getText())
                        || TextUtils.isEmpty(eventValueEditText.getText())) {
                    return;
                }

                Map<String, String> dimensions = new HashMap<String, String>();
                dimensions.put("eventKey", eventKeyEditText.getText().toString());
                dimensions.put("eventValue", eventValueEditText.getText().toString());
                MLAnalytics.logEvent("customEvent", dimensions);
            }
        });

    }
}
