package com.emre.androbooster;

import android.content.Context;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by emre on 06.01.2017.
 */

public class ModeManager {
    Context context;
    public ModeManager(Context context1){
        context = context1;
    }
    public void saveMode(int mode) {
        try {
            FileOutputStream fOut = context.openFileOutput("mode",  context.MODE_PRIVATE);
            String b = String.valueOf(mode);
            fOut.write(b.getBytes());
            fOut.flush();
            fOut.close();
        }
        catch (Exception e) {
            //Log.e("Hata kodu3", "File write failed: " + e.toString());
        }
    }
    public int getMode() {
        File file = new File("/data/data/com.emre.androbooster/files/mode");
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
            }
            br.close();
        } catch (IOException e) {

        }
        return Integer.parseInt(text.toString());
    }
}
