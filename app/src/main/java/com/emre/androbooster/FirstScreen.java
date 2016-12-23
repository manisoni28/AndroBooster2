package com.emre.androbooster;

/**
 * Created by ogrenci on 23.12.2016.
 */

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.dd.CircularProgressButton;

public class FirstScreen extends Fragment {

    CircularProgressButton boost_now, clearCache;
    Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View abc =  inflater.inflate(R.layout.first_screen, container, false);
        context = getActivity();
        clearCache = (CircularProgressButton) abc.findViewById(R.id.go_clear_cache);
        boost_now = (CircularProgressButton) abc.findViewById(R.id.boost_now);
        clearCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,ClearCache.class);
                startActivity(i);
            }
        });
        boost_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boostNow();
            }
        });

        return abc;

    }
    private void  makeToast(String abc){
        Toast.makeText(context,abc,Toast.LENGTH_LONG).show();
    }
    private void boostNow(){
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        final long availableMegs = mi.availMem / 1048576L;
        final long toplam = mi.totalMem / 1048576L;
        long yuzde = availableMegs*100/toplam;
        final long sonuc = 100-yuzde;
        if (sonuc<55){
            makeToast("No need for cleanin RAM, your device seems stable now");
        }else if (sonuc>=55) {
            Intent i = new Intent(context,BoostingActivity.class);
            startActivity(i);
        }
    }
}