package com.idata.filedemo.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.idata.filedemo.R;
import com.idata.filedemo.utils.SilentInstallerApk;

public class SilentInstallActivity extends Activity {
    private static String TAG = "SilentInstallActivity";
    private Button mSilentInstall;
    private EditText etApkName;
    private Context mContext;
    private Handler mHandler = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slient_install);

        mContext = this;
        mHandler = new Handler();
        mSilentInstall = findViewById(R.id.button_silent_install);
        etApkName = findViewById(R.id.et_apk_name);
        final String apkName = etApkName.getText().toString();

        mSilentInstall.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(apkName)) {
                    Toast.makeText(mContext, "开始安装", Toast.LENGTH_SHORT).show();
                    installApp(mContext, "/storage/sdcard0/qq.apk", apkName);
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void installApp(final Context context, final String apkPath, final String apkName) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SilentInstallerApk.silentInstallMethod(context,apkPath,apkName);
            }
        },1000);
    }
}
