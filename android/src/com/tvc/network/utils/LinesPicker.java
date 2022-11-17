package com.tvc.network.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LinesPicker {
    private static final String TAG = "LinesPicker";

    private List<String> mServerIps = new ArrayList<>();
    public LinesPicker () {

    }

    public static void pingServer(String serverIp) {
        Process p = null;
        String delay = "";
        try {
            p = Runtime.getRuntime().exec("ping -c 4 -w 20 " + serverIp);
            int status = p.waitFor();
            Log.d(TAG + " " + serverIp, " status: " + status);
            if (status == 0) {
                BufferedReader buf = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String str = new String();
                while ((str = buf.readLine()) != null) {
                    Log.d(TAG + " " + serverIp, " buf.readLine(): " + str);
                    if (str.contains("avg")) {
                        int i = str.indexOf("/", 20);
                        int j = str.indexOf(".", i);
                        delay = str.substring(i + 1, j);
                        break;
                    }
                }
                Log.d(TAG + " " + serverIp, " result: " + delay + " buf:" + buf.toString());
            }
        } catch (InterruptedException e) {
            Log.d(TAG + " " + serverIp, " InterruptedException: " + e);
        } catch (IOException e) {
            Log.d(TAG + " " + serverIp, " IOException: " + e);
        }
    }
}
