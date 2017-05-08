package com.emre.androbooster;

/**
 * Created by Emre (metahex) on 23.12.2016.
 */
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import com.dd.CircularProgressButton;
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class FirstScreen extends Fragment {

    CircularProgressButton boost_now;
    Context context;
    ThemeManager themeManager;
    SwitchCompat c_n_s, change_color_switch,gamer_theme_switch;
    int level = 100;
    ArcProgress batteryPer;
    private AdView mAdView;
    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
          batteryPer.setProgress(level);
        }
    };
    FastCharge fastCharge;
    private Button facebook;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View abc =  inflater.inflate(R.layout.first_screen, container, false);
        context = getActivity();
        themeManager = new ThemeManager(context);
        batteryPer = (ArcProgress) abc.findViewById(R.id.batteryPer);
        change_color_switch = (SwitchCompat) abc.findViewById(R.id.darh_theme_switch);
        gamer_theme_switch = (SwitchCompat) abc.findViewById(R.id.gamer_theme_switch);
        c_n_s = (SwitchCompat) abc.findViewById(R.id.charge_notif_switch);
        fastCharge = new FastCharge(context);
        try {
             c_n_s.setChecked(fastCharge.dontShow());
        }catch (Exception e) {
            fastCharge.saveState(0);
        }
        boost_now = (CircularProgressButton) abc.findViewById(R.id.boost_now);
        facebook = (Button) abc.findViewById(R.id.face);

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(getOpenFacebookIntent(context));
            }
        });
        mAdView = (AdView) abc.findViewById(R.id.adViewa);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);
        context.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        boost_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        boostNow(view);
                    }
                }).start();
            }
        });

        try {
            if (themeManager.isDark()) {
                change_color_switch.setChecked(true);
            }
            if (!themeManager.isDark()) {
                change_color_switch.setChecked(false);
            }
            if (themeManager.isGamerTheme()) {
                gamer_theme_switch.setChecked(true);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    batteryPer.setUnfinishedStrokeColor(context.getColor(R.color.gamer_green));
                    batteryPer.setFinishedStrokeColor(context.getColor(R.color.gamer_green_dark));
                    batteryPer.setTextColor(context.getColor(R.color.gamer_green));
                    batteryPer.setTextColor(context.getColor(R.color.gamer_green));
                }else {
                    batteryPer.setUnfinishedStrokeColor(getResources().getColor(R.color.gamer_green));
                    boost_now.setBackground(getResources().getDrawable(R.drawable.gamer_selector));
                    batteryPer.setFinishedStrokeColor(getResources().getColor(R.color.gamer_green_dark));
                    batteryPer.setTextColor(getResources().getColor(R.color.gamer_green));
                }

            }
            
			gamer_theme_switch.setChecked(themeManager.isGamerTheme());
            
        }catch (Exception e) {
            fastCharge.saveState(0);
        }
        c_n_s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    fastCharge.saveState(0);
                }
                if (!b){
                    fastCharge.saveState(1);
                }
            }
        });
        change_color_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    themeManager.saveColor(1);
                    gamer_theme_switch.setChecked(false);
                }
                if (!b){
                    themeManager.saveColor(0);
                    gamer_theme_switch.setChecked(false);
                }
            }
        });
        gamer_theme_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    themeManager.saveColor(2);
                    change_color_switch.setChecked(false);
                }
                if (!b){
                    themeManager.saveColor(0);
                    change_color_switch.setChecked(false);
                }
            }
        });
        return abc;
    }
    public static Intent getOpenFacebookIntent(Context context) {

        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/274447682935043"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/corneliantech"));
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mAdView != null) {
            mAdView.destroy();
        }
    }
    private void  makeToast(String abc,View view){
        Snackbar.make(view, abc, Snackbar.LENGTH_LONG).show();
    }
    private void boostNow(View view){
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        final long availableMegs = mi.availMem / 1048576L;
        final long toplam = mi.totalMem / 1048576L;
        long yuzde = availableMegs*100/toplam;
        final long sonuc = 100-yuzde;
        if (sonuc<55){
            makeToast(getString(R.string.no_need_For_boost),view);
        }else if (sonuc>=55) {
            Intent i = new Intent(context,BoostingActivity.class);
            startActivity(i);
        }
    }
}
