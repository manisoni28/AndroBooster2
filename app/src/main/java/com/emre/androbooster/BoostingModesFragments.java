package com.emre.androbooster;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.dd.CircularProgressButton;
import com.emre.androbooster.boosterengine.BoosterService;
import java.io.IOException;

/**
 * Created by ogrenci on 22.12.2016.
 */

public class BoostingModesFragments extends Fragment {

    Context context;
    CircularProgressButton ultra,no_boost,high_boost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View abc =  inflater.inflate(R.layout.booster_modes, container, false);
        context = getActivity();
        ultra = (CircularProgressButton) abc.findViewById(R.id.ultra_game_mode);
        high_boost = (CircularProgressButton) abc.findViewById(R.id.high_mode);
        no_boost = (CircularProgressButton) abc.findViewById(R.id.no_boost);
        ultra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                no_boost.setProgress(0);
                high_boost.setProgress(0);
                startBoosting(2);
            }
        });
        no_boost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                high_boost.setProgress(0);
                startBoosting(0);
            }
        });
        high_boost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (high_boost.getProgress() == 0) {
                    high_boost.setProgress(50);
                }
                if (high_boost.getProgress() == 50) {
                    high_boost.setProgress(100);
                    simulateSuccessProgress(high_boost);
                }
                if (high_boost.getProgress() == 100) {
                    high_boost.setProgress(0);
                }
                no_boost.setProgress(0);
                ultra.setProgress(0);
                startBoosting(1);
            }
        });
        return abc;
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
        }
        if (boost_mode==1){
            intent.putExtra("mode",1);
        }
        if (boost_mode==2){
            intent.putExtra("mode",2);
        }
        context.startService(intent);
    }
}
