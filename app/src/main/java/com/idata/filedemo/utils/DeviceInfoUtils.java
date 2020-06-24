package com.idata.filedemo.utils;

import java.util.Locale;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class DeviceInfoUtils {
    /*** 获取设备宽度（px）**/
    public static int getDeviceWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /*** 获取设备高度（px）*/
    public static int getDeviceHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /*** 获取设备的唯一标识， 需要 “android.permission.READ_Phone_STATE”权限*/
    public static String getIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context,new String[]{Manifest.permission.READ_PHONE_STATE},1);
        }
        String deviceId = tm.getDeviceId();
        if (deviceId == null) {
            return "UnKnown";
        } else {
            return deviceId;
        }
    }

    /*** 获取厂商名* **/
    public static String getDeviceManufacturer() {
        return android.os.Build.MANUFACTURER;
    }

    /*** 获取产品名* **/
    public static String getDeviceProduct() {
        return android.os.Build.PRODUCT;
    }

    /*** 获取手机品牌*/
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /*** 获取手机型号*/
    public static String getDeviceModel() {
        return android.os.Build.MODEL;
    }

    /*** 获取手机主板名*/
    public static String getDeviceBoard() {
        return android.os.Build.BOARD;
    }

    /*** 设备名* **/
    public static String getDeviceDevice() {
        return android.os.Build.DEVICE;
    }

    /***** fingerprit 信息* **/
    public static String getDeviceFubgerprint() {
        return android.os.Build.FINGERPRINT;
    }

    /*** 硬件名** **/
    public static String getDeviceHardware() {
        return android.os.Build.HARDWARE;
    }

    /*** 主机** **/
    public static String getDeviceHost() {
        return android.os.Build.HOST;
    }

    /**** 显示ID* **/
    public static String getDeviceDisplay() {
        return android.os.Build.DISPLAY;
    }

    /*** ID** **/
    public static String getDeviceId() {
        return android.os.Build.ID;
    }

    /*** 获取手机用户名** **/
    public static String getDeviceUser() {
        return android.os.Build.USER;
    }

    /*** 获取手机 硬件序列号* **/
    public static String getDeviceSerial() {
        return android.os.Build.SERIAL;
    }

    /*** 获取手机Android 系统SDK** @return*/
    public static int getDeviceSDK() {
        return android.os.Build.VERSION.SDK_INT;
    }

    /*** 获取手机Android 版本** @return*/
    public static String getDeviceAndroidVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /*** 获取当前手机系统语言。*/
    public static String getDeviceDefaultLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /*** 获取当前系统上的语言列表(Locale列表)*/
    public static String getDeviceSupportLanguage() {
        return Locale.getAvailableLocales().toString();
    }
    /***获取安全patch时间*/
    public static String getDeviceSecurityPatch(){
        return android.os.Build.VERSION.SECURITY_PATCH;
    }

    public static String getDeviceAllInfo(Context context) {
        return  "1. IMEI:" + getIMEI(context) + "\n\n" +
                "2. 设备宽度:" + getDeviceWidth(context) + "\n\n" +
                "3. 设备高度:" + getDeviceHeight(context) + "\n\n" +
                "4. 系统默认语言:" + getDeviceDefaultLanguage() + "\n\n" +
                "5. 硬件序列号(设备名):" + getDeviceSerial() + "\n\n" +
                "6. 手机型号:" + getDeviceModel() + "\n\n" +
                "7. 生产厂商:" + getDeviceManufacturer() + "\n\n" +
                "8. 手机Fingerprint标识:" + getDeviceFubgerprint() + "\n\n" +
                "9. Android 版本:" + getDeviceAndroidVersion() + "\n\n" +
                "10. Android SDK版本:" + getDeviceSDK() + "\n\n" +
                "11. 安全patch 时间:" + getDeviceSecurityPatch() + "\n\n" +
                "12. 版本类型:" + android.os.Build.TYPE + "\n\n" +
                "13. 用户名:" + getDeviceUser() + "\n\n" +
                "14. 产品名:" + getDeviceProduct() + "\n\n" +
                "15. ID:" + getDeviceId() + "\n\n" +
                "16. 显示ID:" + getDeviceDisplay() + "\n\n" +
                "17. 硬件名:" + getDeviceHardware() + "\n\n" +
                "18. 产品名:" + getDeviceDevice() + "\n\n" +
                "19. Bootloader:" + android.os.Build.BOOTLOADER + "\n\n" +
                "20. 主板名:" + getDeviceBoard() + "\n\n" +
                "21. CodeName:" + android.os.Build.VERSION.CODENAME + "\n\n" +
                "22. 手机品牌:" + getDeviceBrand() + "\n\n" +
                "23. 主机:" + getDeviceHost() + "\n\n" +
                "24. 语言支持:" + getDeviceSupportLanguage();
    }
}
