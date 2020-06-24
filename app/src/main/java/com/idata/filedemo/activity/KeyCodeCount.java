package com.idata.filedemo.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.idata.filedemo.bean.Constant;
import com.idata.filedemo.R;
import com.idata.filedemo.utils.SharedPreferencesUtils;

public class KeyCodeCount extends AppCompatActivity {
    private TextView tvCount1;
    private TextView tvCount2;
    private Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.keycode_layout);
        mContext = this;

        tvCount1 = findViewById(R.id.tv_count1);
        tvCount2 = findViewById(R.id.tv_count2);
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.IDATA_KEYCODE_COUNT);
        mContext.registerReceiver(mBroadcast,filter);
    }

    private BroadcastReceiver mBroadcast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String keyCode = intent.getStringExtra("keyCode");
            if(intent.getAction().equals(Constant.IDATA_KEYCODE_COUNT)){
                if(keyCode.equals(Constant.KEYCODE_SCAN_1)){
                    makeCount(keyCode);
                }else if(keyCode.equals(Constant.KEYCODE_SCAN_2)){
                    makeCount(keyCode);
                }
            }
        }
    };
    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();
        int scan1 = SharedPreferencesUtils.getIntPref(mContext, Constant.KEYCODE_SCAN_1, Constant.KEYCODE_SCAN_1);
        int scan2 = SharedPreferencesUtils.getIntPref(mContext, Constant.KEYCODE_SCAN_2, Constant.KEYCODE_SCAN_2);
        tvCount1.setText(Constant.KEYCODE_SCAN_1 + ":" + scan1);
        tvCount2.setText(Constant.KEYCODE_SCAN_2 + ":" + scan2);
    }
    @SuppressLint("SetTextI18n")
    private void makeCount(String keycode) {
        int num = SharedPreferencesUtils.getIntPref(mContext, keycode, keycode);
        int sum = 0;
        if (num != 0) {
            sum = num;
        }
        sum++;
        if(sum > 10000){
            sum = 0;
            SharedPreferencesUtils.setIntPref(mContext, keycode, keycode, 0);
        }
        SharedPreferencesUtils.setIntPref(mContext, keycode, keycode, sum);
        if(keycode.equals(Constant.KEYCODE_SCAN_1)){
            tvCount1.setText(keycode + ":" + sum);
        }else if(keycode.equals(Constant.KEYCODE_SCAN_2)){
            tvCount2.setText(keycode + ":" + sum);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcast);
    }
}
