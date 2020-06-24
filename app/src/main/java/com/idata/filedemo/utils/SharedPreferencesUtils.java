package com.idata.filedemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences数据持久化
 */
public class SharedPreferencesUtils {

    /**
     * 保存String数据
     */
    public static void setStringPref(Context context,String tableName, String name, String value) {
        SharedPreferences preferences = context.getSharedPreferences(tableName, Context.MODE_WORLD_READABLE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(name , value);
        editor.commit();
    }
    /**
     * 获取String
     */
    public static String getStringPref(Context context,String tableName, String name) {
        SharedPreferences preferences = context.getSharedPreferences(tableName, Context.MODE_PRIVATE);
        String result = preferences.getString(name , null);
        return result;
    }

    /**
     * 保存boolean数据，主要是开关状态
     */
    public static void setBooleanPref(Context context,String tableName, String name, boolean value) {
        SharedPreferences preferences = context.getSharedPreferences(tableName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(name , value);
        editor.commit();
    }
    /**
     * 获取boolean
     */
    public static boolean getBooleanPref(Context context,String tableName, String name) {
        SharedPreferences preferences = context.getSharedPreferences(tableName, Context.MODE_PRIVATE);
        boolean result = preferences.getBoolean(name , false);
        return result;
    }

    /**
     * 保存Int数据
     */
    public static void setIntPref(Context context,String tableName, String name, int value) {
        SharedPreferences preferences = context.getSharedPreferences(tableName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(name , value);
        editor.commit();
    }
    /**
     * 获取Int
     */
    public static int getIntPref(Context context,String tableName, String name) {
        SharedPreferences preferences = context.getSharedPreferences(tableName, Context.MODE_PRIVATE);
        int result = preferences.getInt(name , 0);
        return result;
    }

    /**
     * 保存Int数据
     */
    public static void setLongPref(Context context,String tableName, String name, long value) {
        SharedPreferences preferences = context.getSharedPreferences(tableName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(name , value);
        editor.commit();
    }
    /**
     * 获取Int
     */
    public static Long getLongPref(Context context,String tableName, String name) {
        SharedPreferences preferences = context.getSharedPreferences(tableName, Context.MODE_PRIVATE);
        long result = preferences.getLong(name , 0);
        return result;
    }
}