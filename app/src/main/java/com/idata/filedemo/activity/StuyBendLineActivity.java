package com.idata.filedemo.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import com.idata.filedemo.R;
import com.idata.filedemo.widget.StudyBendLine;

import java.util.ArrayList;

public class StuyBendLineActivity extends Activity {


    private LinearLayout mLine;
    private StudyBendLine mStudyBendLine;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stuy);
        mLine = (LinearLayout)findViewById(R.id.line);
        mStudyBendLine = new StudyBendLine(this);

        initData();

    }
    private void initData(){

        ArrayList<Integer> data = new ArrayList<>();

        data.add(60);
        data.add(30);
        data.add(80);
        data.add(40);
        data.add(60);
        data.add(30);
        data.add(90);

        ArrayList<String> data2 = new ArrayList<>();
        data2.add("1");
        data2.add("2");
        data2.add("3");
        data2.add("4");
        data2.add("5");
        data2.add("6");
        data2.add("7");


        if(mStudyBendLine != null){
            mStudyBendLine.updateTime(data, data2);
        }
        mLine.addView(mStudyBendLine);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

        }
    };
}
