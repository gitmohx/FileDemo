package com.idata.filedemo.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;

import com.idata.filedemo.R;

public class UpTime extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uptime);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("mohx","uptime == " + getUpTime());

        IntentFilter filter = new IntentFilter();
        filter.addAction("com.iData.Scancontext");
        registerReceiver(receiver, filter);





    }
    public String getUpTime() {
        long upTime = SystemClock.elapsedRealtime()/1000;
        return String.valueOf(upTime);//convert(upTime);
    }
    private String convert(long t) {
        int s = (int)(t % 60);
        int m = (int)((t / 60) % 60);
        int h = (int)((t / 3600));

        return h + ":" + pad(m) + ":" + pad(s);
    }
    private String pad(int n) {
        if (n >= 10) {
            return String.valueOf(n);
        } else {
            return "0" + String.valueOf(n);
        }
    }


    private int num = 0;
    private int tal = 0;
    private boolean times = false;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if("com.iData.Scancontext".equals(intent.getAction())){
                ++num;
                if(num == 5){
                    Log.d("mohx","okok");
                }
                Log.d("mohx","num = " + num);
            }
        }
    };
}
