package com.idata.filedemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = "MainActivity";
    private Button mGotoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mGotoFile = findViewById(R.id.button_file);
        mGotoFile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_file:
                Intent mIntent = new Intent();
                try {
                    mIntent.setClass(MainActivity.this, Class.forName("com.idata.filedemo.FileActivity"));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                this.startActivity(mIntent);
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