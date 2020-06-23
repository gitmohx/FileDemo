package com.idata.filedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
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
        filter.addAction("com.idata.keycode_count");
        this.registerReceiver(mBroadcast,filter);
    }

    private BroadcastReceiver mBroadcast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String keyCode = intent.getStringExtra("keyCode");
            if(intent.getAction().equals("com.idata.keycode_count")){
                if(keyCode.equals("189")){
                    makeCount(keyCode);
                }else if(keyCode.equals("188")){
                    makeCount("188");
                }
            }
        }
    };
    @Override
    protected void onResume() {
        super.onResume();
    }
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
        if(keycode.equals("188")  && keycode != null){
            tvCount1.setText(String.valueOf(sum));
        }else if(keycode.equals("189")  && keycode != null){
            tvCount2.setText(String.valueOf(sum));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcast);
    }
}
