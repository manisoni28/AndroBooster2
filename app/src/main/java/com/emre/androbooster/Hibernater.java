package com.emre.androbooster;

import android.os.Build;

import java.io.DataOutputStream;
import java.io.IOException;


/**
 * Created by emre on 20.02.2016.
 */
public class Hibernater {
    public static void ForceStopPackage(String packageName) {
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                RunAsRoot(new String[]{"am force-stop --user current " + packageName});
            }else {
                RunAsRoot(new String[]{"am force-stop " + packageName});
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void RunAsRoot(String[] cmds) throws IOException {
        DataOutputStream os = new DataOutputStream(Runtime.getRuntime().exec("su").getOutputStream());
        for (String tmpCmd : cmds) {
            os.writeBytes(tmpCmd + "\n");
        }
        os.writeBytes("exit\n");
        os.flush();
    }
}
/*
  public static void hibernate(String packagename) {
        //system setenforce 0
        if (Build.VERSION.SDK_INT >= 21) {

            try {
                CommandCapture setGovernor5 = new CommandCapture(0,
                        "am force-stop --user current " + packagename);
                RootTools.getShell(true).add(setGovernor5);
            } catch (RootDeniedException | IOException rde) {
                rde.printStackTrace();

            } catch (TimeoutException te) {
                te.printStackTrace();
            }
        } else {
            try {
                CommandCapture setGovernor5 = new CommandCapture(0,
                        "am force-stop " + packagename);
                RootTools.getShell(true).add(setGovernor5);
            } catch (RootDeniedException | IOException rde) {
                rde.printStackTrace();

            } catch (TimeoutException te) {
                te.printStackTrace();

            }
        }

       // -----------------------------------------------------------------------

        }

 */