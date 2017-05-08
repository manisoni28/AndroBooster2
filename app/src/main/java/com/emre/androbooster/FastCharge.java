package com.emre.androbooster;

import android.content.Context;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

/**
 * Created by emre on 17.01.2017.
 */

public class FastCharge {
    Context context;
    public FastCharge(Context context1){
        context = context1;
    }
    public void saveState(int mode) {
        try {
            FileOutputStream fOut = context.openFileOutput("fcm",  context.MODE_PRIVATE);
            String b = String.valueOf(mode);
            fOut.write(b.getBytes());
            fOut.flush();
            fOut.close();
        }
        catch (Exception e) {
            //Log.e("Hata kodu3", "File write failed: " + e.toString());
        }
    }
    public boolean dontShow() {
        File file = new File("/data/data/com.emre.androbooster/files/fcm");
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
        if (Integer.parseInt(text.toString())==1){
            abc = true;
        }
        return abc;
    }
}
