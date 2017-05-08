package com.emre.androbooster.boosterengine;

import android.os.Build;
import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

/**
 * Created by ogrenci on 5.05.2017.
 */

public class CPUCores {
    public int getNumberOfCores() {
        if(Build.VERSION.SDK_INT >= 17) {
            return Runtime.getRuntime().availableProcessors();
        }
        else {
            return getNumCoresOldPhones();
        }
    }

    public int getNumCoresOldPhones() {
        class CpuFilter implements FileFilter {
            @Override
            public boolean accept(File pathname) {
                return Pattern.matches("cpu[0-9]+", pathname.getName());
            }
        }

        try {
            File dir = new File("/sys/devices/system/cpu/");
            File[] files = dir.listFiles(new CpuFilter());
            return files.length;
        } catch(Exception e) {
            return 1;
        }
    }
}
