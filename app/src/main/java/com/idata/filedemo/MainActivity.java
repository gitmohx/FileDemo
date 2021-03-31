package com.idata.filedemo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.idata.filedemo.activity.FileActivity;
import com.idata.filedemo.activity.Graph;
import com.idata.filedemo.activity.KeyCodeCount;
import com.idata.filedemo.activity.SilentInstallActivity;
import com.idata.filedemo.activity.StuyBendLineActivity;
import com.idata.filedemo.activity.SystemProp;
import com.idata.filedemo.activity.UpTime;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = "MainActivity";
    private Button mGotoFile;
    private Button mGotoKeyCode;
    private Button mGotoProp;
    private Button mGotoSilentInsatll;
    private Button mGraph;

    private Button mLine;
    private Button mUpTime;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mGotoFile = findViewById(R.id.button_file);
        mGotoFile.setOnClickListener(this);
        mGotoKeyCode = findViewById(R.id.button_keycode);
        mGotoKeyCode.setOnClickListener(this);
        mGotoProp = findViewById(R.id.button_prop);
        mGotoProp.setOnClickListener(this);
        mGotoSilentInsatll = findViewById(R.id.button_install);
        mGotoSilentInsatll.setOnClickListener(this);

        mGraph = findViewById(R.id.button_graph);
        mGraph.setOnClickListener(this);

        mLine = findViewById(R.id.button_line);
        mLine.setOnClickListener(this);
        mUpTime = findViewById(R.id.button_uptime);
        mUpTime.setOnClickListener(this);
    }

    private static final String ACTION_LEDS = "com.idata.set_leds_action";
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_file:
                Intent mIntent = new Intent(MainActivity.this, FileActivity.class);
                this.startActivity(mIntent);
                String st = Build.SERIAL;
                Log.d("mo","St == " + st);
                break;
            case R.id.button_keycode:
                Intent intent = new Intent(MainActivity.this, KeyCodeCount.class);
                this.startActivity(intent);
                break;
            case R.id.button_prop:
//                Intent i = new Intent(MainActivity.this, SystemProp.class);
//                this.startActivity(i);
                Intent open = new Intent();
                open.setAction(ACTION_LEDS);
                open.putExtra("LED_RED_OPEN",true);
                this.sendBroadcast(open);
                break;
            case R.id.button_install:
//                Intent install = new Intent(MainActivity.this, SilentInstallActivity.class);
//                this.startActivity(install);
                Intent close = new Intent();
                close.setAction(ACTION_LEDS);
                close.putExtra("LED_RED_CLOSE",true);
                this.sendBroadcast(close);
                break;
            case R.id.button_graph:
                Intent intentGraph = new Intent(MainActivity.this, Graph.class);
                this.startActivity(intentGraph);
                break;
            case R.id.button_line:
                Intent intentLine = new Intent(MainActivity.this, StuyBendLineActivity.class);
                this.startActivity(intentLine);
                break;
            case R.id.button_uptime:
                Intent intentTime = new Intent(MainActivity.this, UpTime.class);
                this.startActivity(intentTime);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}