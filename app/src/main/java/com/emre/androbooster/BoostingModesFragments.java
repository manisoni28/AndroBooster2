package com.emre.androbooster;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.afollestad.materialdialogs.MaterialDialog;
import com.dd.CircularProgressButton;
import com.emre.androbooster.boosterengine.BoosterService;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.stericson.RootTools.RootTools;
import java.io.IOException;

/**
 * Created by ogrenci on 22.12.2016.
 */

public class BoostingModesFragments extends Fragment {

    Context context;
    private MaterialDialog m;
    CircularProgressButton ultra,no_boost,high_boost,ask;
    private ModeManager modeManager;
    private InterstitialAd mInterstitialAd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View abc =  inflater.inflate(R.layout.booster_modes, container, false);
        context = getActivity();
        modeManager = new ModeManager(context);
        ultra = (CircularProgressButton) abc.findViewById(R.id.ultra_game_mode);
        ask = (CircularProgressButton) abc.findViewById(R.id.ask);
        high_boost = (CircularProgressButton) abc.findViewById(R.id.high_mode);
        no_boost = (CircularProgressButton) abc.findViewById(R.id.no_boost);
        this.mInterstitialAd = new InterstitialAd(context);
        this.mInterstitialAd.setAdUnitId("ca-app-pub-5942424100141990/5832153067");
        this.mInterstitialAd.setAdListener(new AdListener() {
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("4b5d467c88b7bd63").addTestDevice("04157df47a383a0c").build());
            }
        });
        ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    m = new MaterialDialog.Builder(context)
                            .title(R.string.app_name)
                            .content(getString(R.string.sorular))
                            .contentColor(context.getColor(R.color.black))
                            .titleColor(context.getColor(R.color.black))
                            .show();
                }else {
                    m = new MaterialDialog.Builder(context)
                            .title(R.string.app_name)
                            .content(getString(R.string.sorular))
                            .contentColor(getResources().getColor(R.color.black))
                            .titleColor(getResources().getColor(R.color.black))
                            .show();
                }
            }
        });
        ultra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (RootTools.isAccessGiven()){
                    if (ultra.getProgress() == 0) {
                        ultra.setProgress(50);
                    }
                    if (ultra.getProgress() == 50) {
                        ultra.setProgress(100);
                        simulateSuccessProgress(ultra);
                    }
                    if (ultra.getProgress() == 100) {
                        ultra.setProgress(0);
                    }
                    showAdWhenLoaded(0);
                    mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("4b5d467c88b7bd63").addTestDevice("04157df47a383a0c").build());
                    modeManager.saveMode(2);
                    no_boost.setProgress(0);
                    high_boost.setProgress(0);
                    startBoosting(2);
                }else {
                    Snackbar.make(view, getString(R.string.op), Snackbar.LENGTH_LONG).show();
                }
            }
        });
        no_boost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (RootTools.isAccessGiven()){
                if (no_boost.getProgress() == 0) {
                    no_boost.setProgress(50);
                }
                if (no_boost.getProgress() == 50) {
                    no_boost.setProgress(100);
                    simulateSuccessProgress(no_boost);
                }
                if (no_boost.getProgress() == 100) {
                    no_boost.setProgress(0);
                }  
                ultra.setProgress(0);
                modeManager.saveMode(0);
                high_boost.setProgress(0);
                startBoosting(0);
                }else {
                    Snackbar.make(view, getString(R.string.op), Snackbar.LENGTH_LONG).show();
                }
            }
        });
        high_boost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (RootTools.isAccessGiven()){
                if (high_boost.getProgress() == 0) {
                    high_boost.setProgress(50);
                }
                if (high_boost.getProgress() == 50) {
                    high_boost.setProgress(100);
                    simulateSuccessProgress(high_boost);
                }
           //     showAdWhenLoaded(0);
             //   mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("4b5d467c88b7bd63").addTestDevice("04157df47a383a0c").build());

                if (high_boost.getProgress() == 100) {
                    high_boost.setProgress(0);
                }
                no_boost.setProgress(0);
                ultra.setProgress(0);
                startBoosting(1);
                    modeManager.saveMode(1);
                }else {
                    Snackbar.make(view, getString(R.string.op), Snackbar.LENGTH_LONG).show();
                }
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
        }, (long) (extraDelay + 400));
    }
    public void stop() {
        Intent intent = new Intent(context,BoosterService.class);
        context.stopService(intent);
        TerminalCommand.command("start mpdecision");
        try {
            TerminalCommand.RunAsRoot(ModeScripts.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void simulateSuccessProgress(final CircularProgressButton button) {
        ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
        widthAnimation.setDuration(1500);
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setProgress(value);
            }
        });
        widthAnimation.start();
    }
    private void startBoosting(int boost_mode){
        Intent intent = new Intent(context,BoosterService.class);
        if (boost_mode==0){
            stop();
        }else {
            context.startService(intent);
        }
    }
}
