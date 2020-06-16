package com.idata.filedemo;

import android.util.Log;
import java.io.DataOutputStream;

public class RootCmd {

    //翻译并执行相应的adb命令
    public static boolean exusecmd(String command) {
        Process process = null;
        DataOutputStream os = null;
        try {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(command + "\n");
            os.writeBytes("exit\n");
            os.flush();
            Log.e("updateFile", "======000==writeSuccess======");
            process.waitFor();
        } catch (Exception e) {
            Log.e("updateFile", "======111=writeError======" + e.toString());
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (process != null) {
                    process.destroy();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    //移动文件
    public static void moveFileToSystem(String filePath, String sysFilePath) {
        exusecmd("mount -o rw,remount /system/etc");
        exusecmd("chmod 777 /system/etc");
        exusecmd("cp  " + filePath + " " + sysFilePath);
    }
}
