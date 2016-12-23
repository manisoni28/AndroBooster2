package com.emre.androbooster;

/**
 * Created by ogrenci on 22.12.2016.
 */

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.dd.CircularProgressButton;
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import java.util.Timer;
import java.util.TimerTask;

public class RAMManagementFragment extends Fragment {

    ArcProgress ramPercentage;
    TextView ram_state,a_ram,total_ram;
    CircularProgressButton cleanRAM;
    Timer timer;
    private InterstitialAd mInterstitialAd;
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View abc =  inflater.inflate(R.layout.ram_management, container, false);
        cleanRAM = (CircularProgressButton) abc.findViewById(R.id.ram_cleaner);
        ramPercentage = (ArcProgress) abc.findViewById(R.id.ram_per);
        ram_state = (TextView) abc.findViewById(R.id.ram_state);
        a_ram = (TextView) abc.findViewById(R.id.avail_ram);
        total_ram = (TextView) abc.findViewById(R.id.total_ram);
        this.mInterstitialAd = new InterstitialAd(context);
        this.mInterstitialAd.setAdUnitId("ca-app-pub-5942424100141990/5832153067");
        this.mInterstitialAd.setAdListener(new AdListener() {
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("4b5d467c88b7bd63").addTestDevice("04157df47a383a0c").build());
            }
        });
        context = getActivity();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
                ActivityManager activityManager = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
                activityManager.getMemoryInfo(mi);
                final long availableMegs = mi.availMem / 1048576L;
                final long toplam = mi.totalMem / 1048576L;
                long yuzde = availableMegs*100/toplam;
                final long sonuc = 100-yuzde;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        a_ram.setText(getString(R.string.avail_ram)+" "+String.valueOf(availableMegs)+" MB");
                        total_ram.setText(getString(R.string.top_ram)+" "+String.valueOf(toplam)+" MB");
                        String sonuc1 = String.valueOf(sonuc);
                        ramPercentage.setProgress(Integer.parseInt(sonuc1));
                        if (sonuc>=85){
                            ram_state.setText(getString(R.string.bad_ram_state));
                        }
                        if (sonuc<85){
                            ram_state.setText(getString(R.string.good_ram_state));
                        }
                    }
                });
            }
        }, 0, 1200);

        cleanRAM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RAMBooster.cleanAllActivities(context);
                showAdWhenLoaded(0);
                mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("4b5d467c88b7bd63").addTestDevice("04157df47a383a0c").build());
                Snackbar.make(view, getString(R.string.ram_CLEANED), Snackbar.LENGTH_LONG).show();
            }
        });

        return abc;
    }
    private void showAdWhenLoaded(int extraDelay) {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    showAdWhenLoaded(0);
                }
            }
        }, (long) (extraDelay + 350));
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        timer.cancel();
    }
}