package com.emre.androbooster;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import android.widget.*;


public class MainActivity extends AppCompatActivity {
	
    private ThemeManager themeManager;
    private RelativeLayout mainLayout;
    private InterstitialAd mInterstitialAd;
    private FloatingActionButton fab;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themeManager = new ThemeManager(MainActivity.this);
        if (themeManager.isGamerTheme()) {
            setTheme(R.style.GamerTheme);
        }
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

            mainLayout = (RelativeLayout) findViewById(R.id.main_layout);
            fab = (FloatingActionButton) findViewById(R.id.fab_ultra);
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
            tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.main)));
            tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.app)));
            tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.extra)));
            tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.ram_yonetimi)));
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            if (themeManager.isDark()) {
                if (Build.VERSION.SDK_INT >= 23) {
                    mainLayout.setBackgroundColor(getColor(R.color.darkTheme));
                } else {
                    mainLayout.setBackgroundColor(getResources().getColor(R.color.darkTheme));
                }
            }
        this.mInterstitialAd = new InterstitialAd(MainActivity.this);
        this.mInterstitialAd.setAdUnitId("ca-app-pub-5942424100141990/5832153067");
        this.mInterstitialAd.setAdListener(new AdListener() {
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("04157df47a383a0c").build());
            }
        });
		
		
		//Toast.makeText(MainActivity.this,c.minFrequency(),Toast.LENGTH_LONG).show();
		

        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        showAdWhenLoaded(0);

            if (themeManager.isGamerTheme()) {
                if (Build.VERSION.SDK_INT >= 23) {
                    mainLayout.setBackgroundColor(getColor(R.color.darkTheme));
                } else {
                    mainLayout.setBackgroundColor(getResources().getColor(R.color.darkTheme));
                }
            }
			
            final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
            final PagerAdapter adapter = new PagerAdapter
                    (getSupportFragmentManager(), tabLayout.getTabCount());
            viewPager.setAdapter(adapter);
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openUFC();
                }
            });

            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
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
   
    private void openUFC() {
        Intent intent = getPackageManager().getLaunchIntentForPackage("com.emre.sync");
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("market://details?id=" + "com.emre.sync"));
            startActivity(intent);
        }
    }

}
