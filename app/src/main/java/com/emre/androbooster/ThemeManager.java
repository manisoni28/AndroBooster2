package com.emre.androbooster;

import android.content.Context;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

/**
 * Created by emre on 19.01.2017.
 */

public class ThemeManager {
    Context context;
    public ThemeManager(Context context1){
        context = context1;
    }
    public void saveColor(int mode) {
        try {
            FileOutputStream fOut = context.openFileOutput("theme",  context.MODE_PRIVATE);
            String b = String.valueOf(mode);
            fOut.write(b.getBytes());
            fOut.flush();
            fOut.close();
        }
        catch (Exception e) {
            //Log.e("Hata kodu3", "File write failed: " + e.toString());
        }
    }
    public boolean isDark() {
        File file = new File("/data/data/com.emre.androbooster/files/theme");
        boolean abc = false;
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
            }
            br.close();
        } catch (Exception e) {

        }
        if (Integer.parseInt(text.toString())==1){
            abc = true;
        }
        return abc;
    }
    public boolean isGamerTheme() {
        File file = new File("/data/data/com.emre.androbooster/files/theme");
        boolean abc=false;
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
            }
            br.close();
        } catch (Exception e) {

        }
        if (Integer.parseInt(text.toString())==2){
            abc = true;
        }
        return abc;
    }
}
