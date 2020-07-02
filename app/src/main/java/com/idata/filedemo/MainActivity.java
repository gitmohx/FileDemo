package com.idata.filedemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.idata.filedemo.activity.FileActivity;
import com.idata.filedemo.activity.KeyCodeCount;
import com.idata.filedemo.activity.SystemProp;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = "MainActivity";
    private Button mGotoFile;
    private Button mGotoKeyCode;
    private Button mGotoProp;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_file:
                Intent mIntent = new Intent(MainActivity.this, FileActivity.class);
                this.startActivity(mIntent);
                break;
            case R.id.button_keycode:
                Intent intent = new Intent(MainActivity.this, KeyCodeCount.class);
                this.startActivity(intent);
                break;
            case R.id.button_prop:
                Intent i = new Intent(MainActivity.this, SystemProp.class);
                this.startActivity(i);
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