package com.idata.filedemo;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class KeyCodeCount extends AppCompatActivity {
    private TextView tvCount1;
    private TextView tvCount2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.keycode_layout);

        tvCount1 = findViewById(R.id.tv_count1);
        tvCount2 = findViewById(R.id.tv_count2);
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constant.IDATA_KEYCODE_COUNT);
        this.registerReceiver(mBroadcast,filter);
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
        int scan1 = SharedPreferencesUtils.getIntPref(KeyCodeCount.this, Constant.KEYCODE_SCAN_1, Constant.KEYCODE_SCAN_1);
        int scan2 = SharedPreferencesUtils.getIntPref(KeyCodeCount.this, Constant.KEYCODE_SCAN_2, Constant.KEYCODE_SCAN_2);
        tvCount1.setText("Scan_1:" + scan1 +"次");
        tvCount2.setText("Scan_2:" + scan2 +"次");
    }
    @SuppressLint("SetTextI18n")
    private void makeCount(String keycode) {
        int num = SharedPreferencesUtils.getIntPref(KeyCodeCount.this, keycode, keycode);
        int sum = 0;
        if (num != 0) {
            sum = num;
        }
        sum++;
        if(sum > 10000){
            sum = 0;
            SharedPreferencesUtils.setIntPref(KeyCodeCount.this, keycode, keycode, 0);
        }
        SharedPreferencesUtils.setIntPref(KeyCodeCount.this, keycode, keycode, sum);
        if(keycode.equals(Constant.KEYCODE_SCAN_1)){
            tvCount1.setText("Scan_1:" + sum +"次");
        }else if(keycode.equals(Constant.KEYCODE_SCAN_2)){
            tvCount2.setText("Scan_2:" + sum +"次");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcast);
    }
}
