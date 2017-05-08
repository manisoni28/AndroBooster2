package com.emre.androbooster;

import android.os.Build;
import java.io.DataOutputStream;
import java.io.IOException;
import com.emre.androbooster.TerminalCommand;


/**
 * Created by emre on 20.02.2016.
 */
 
public class Hibernater {
    public static void ForceStopPackage(String packageName) {
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                TerminalCommand.RunAsRoot(new String[]{"am force-stop --user current " + packageName});
            }else {
                TerminalCommand.RunAsRoot(new String[]{"am force-stop " + packageName});
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

