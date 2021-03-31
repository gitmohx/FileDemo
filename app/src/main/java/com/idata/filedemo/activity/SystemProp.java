package com.idata.filedemo.activity;

import android.content.pm.PackageInstaller;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.idata.filedemo.R;
import com.idata.filedemo.utils.DeviceInfoUtils;

public class SystemProp extends AppCompatActivity {

    private static String TAG = "";
    private TextView tvProp;
    private int mSessionId = -1;
    private PackageInstaller.SessionCallback mSessionCallback;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prop_layout);
        tvProp = findViewById(R.id.tv_prop);
        tvProp.setText(DeviceInfoUtils.getDeviceAllInfo(this));
    }
}
