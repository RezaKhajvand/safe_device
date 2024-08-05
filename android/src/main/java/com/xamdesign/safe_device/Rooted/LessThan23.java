package com.xamdesign.safe_device.Rooted;

import java.io.File;

public class LessThan23 implements CheckApiVersion {

    @Override
    public boolean checkRooted() {
        return canExecuteCommand("/system/xbin/which su") || isSuperuserPresent();
    }

    // executes a command on the system
    private static boolean canExecuteCommand(String command) {
        boolean executeResult;
        try {
            Process process = Runtime.getRuntime().exec(command);
            if(process.waitFor() == 0) {
                executeResult = true;
            } else {
                executeResult = false;
            }
        } catch (Exception e) {
            executeResult = false;
        }

        return executeResult;
    }

    private static boolean isSuperuserPresent() {
        // Check if /system/app/Superuser.apk is present
        String[] paths = {
                asciiDecode("47 115 121 115 116 101 109 47 97 112 112 47 83 117 112 101 114 117 115 101 114 46 97 112 107"),
                asciiDecode("47 115 98 105 110 47 115 117"),
                asciiDecode("47 115 121 115 116 101 109 47 98 105 110 47 115 117"),
                asciiDecode("47 115 121 115 116 101 109 47 120 98 105 110 47 115 117"),
                asciiDecode("47 100 97 116 97 47 108 111 99 97 108 47 120 98 105 110 47 115 117"),
                asciiDecode("47 100 97 116 97 47 108 111 99 97 108 47 98 105 110 47 115 117"),
                asciiDecode("47 115 121 115 116 101 109 47 115 100 47 120 98 105 110 47 115 117"),
                asciiDecode("47 115 121 115 116 101 109 47 98 105 110 47 102 97 105 108 115 97 102 101 47 115 117"),
                asciiDecode("47 100 97 116 97 47 108 111 99 97 108 47 115 117")
        };

        for (String path : paths) {
            if (new File(path).exists()) {
                return true;
            }
        }

        return false;
    }

}
