package com.idata.filedemo.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class SilentInstallerApk {
    private static String TAG = "Installer";
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void silentInstallMethod(final Context context, String apkFilePath, String apkName) {
        File apkFile = new File(apkFilePath);
        long apkFileLength = apkFile.length();
        PackageManager pm = context.getPackageManager();
        PackageInstaller packageInstaller = pm.getPackageInstaller();
        packageInstaller.registerSessionCallback(new PackageInstaller.SessionCallback() {
            @Override
            public void onCreated(int sessionId) {
                Log.e(TAG, "Install Start sessionId-> " + sessionId);
            }
            @Override
            public void onBadgingChanged(int sessionId) {}

            @Override
            public void onActiveChanged(int sessionId, boolean active) {}

            @Override
            public void onProgressChanged(int sessionId, float progress) {}

            @Override
            public void onFinished(int sessionId, boolean success) {
                if (success) {
                    Toast.makeText(context,"Silent Install success",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context,"Silent Install Fail",Toast.LENGTH_SHORT).show();
                }
            }
        });

        int count;
        int sessionId;
        byte[] buffer = new byte[65536];

        InputStream inputStream;
        OutputStream outputStream;
        PackageInstaller.Session session = null;
        PackageInstaller.SessionParams sessionParams;

        try {
            sessionParams = new PackageInstaller.SessionParams(PackageInstaller.SessionParams.MODE_FULL_INSTALL);
            sessionId =  packageInstaller.createSession(sessionParams);
            session = packageInstaller.openSession(sessionId);

            inputStream = new FileInputStream(apkFile);
            outputStream = session.openWrite(apkName, 0, apkFileLength);

            while((count = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, count);
                float progress = ((float)count / (float)apkFileLength);
                session.setStagingProgress(progress);
            }
            session.fsync(outputStream);

            inputStream.close();
            outputStream.flush();
            outputStream.close();

            Intent intent = new Intent();
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
            session.commit(pendingIntent.getIntentSender());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.abandon();
            }
        }
    }
}

