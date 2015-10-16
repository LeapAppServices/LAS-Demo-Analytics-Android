package com.maxleap.demo.analytics;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maxleap.MLAnalytics;

public class SimpleFragment extends Fragment {

    private int mNum;
    private String mPageName;

    private static final String EXTRA_PAGE_NAME = "pageName";
    private static final String EXTRA_NUM = "num";

    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mNum = bundle.getInt(EXTRA_NUM);
            mPageName = bundle.getString(EXTRA_PAGE_NAME, "Page " + mNum);
        } else {
            mNum = 1;
            mPageName = "Page " + mNum;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_simple, container, false);
        TextView tvTitle = (TextView) v.findViewById(android.R.id.text1);
        tvTitle.setText("Page No." + mNum);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        //page view
        MLAnalytics.onPageStart(mPageName);
    }

    @Override
    public void onPause() {
        super.onPause();
        //page view
        MLAnalytics.onPageEnd(mPageName);
    }

    public static SimpleFragment newInstance(int num, String pageName) {
        SimpleFragment fragment = new SimpleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_NUM, num);
        bundle.putString(EXTRA_PAGE_NAME, pageName);
        fragment.setArguments(bundle);
        return fragment;
    }
}
