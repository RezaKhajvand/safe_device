package com.xamdesign.safe_device.Rooted;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class GreaterThan23 implements CheckApiVersion {
    @Override
    public boolean checkRooted() {
        return checkRootMethod1() || checkRootMethod2();
    }

    private boolean checkRootMethod1() {
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
            if (new File(path).exists()) return true;
        }
        return false;
    }

    private boolean checkRootMethod2() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[] { "/system/xbin/which", "su" });
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            if (in.readLine() != null) return true;
            return false;
        } catch (Throwable t) {
            return false;
        } finally {
            if (process != null) process.destroy();
        }
    }
       private String asciiDecode(String ascii) {
        StringBuilder sb = new StringBuilder();
        for (String code : ascii.split(" ")) {
            sb.append((char) Integer.parseInt(code));
        }
        return sb.toString();
    }
}
