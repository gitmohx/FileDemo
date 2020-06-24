package com.idata.filedemo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SystemProp extends AppCompatActivity {

    private TextView tvProp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prop_layout);
        tvProp = findViewById(R.id.tv_prop);
        tvProp.setText(DeviceInfoUtils.getDeviceAllInfo(this));
    }
}
