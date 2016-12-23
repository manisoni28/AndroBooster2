package com.emre.androbooster;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.ArcProgress;

/**
 * Created by Emre2 on 11.8.2016.
 */
public class ClearCache extends AppCompatActivity {

    ArcProgress sizeOfStorage;
    FloatingActionButton clearCacheOfStorage;
    Context context;

    private int storagePercentage(){
        int yuzde = 0;
        int tum_alan = StorageInfo.getTotalSize();
        int doluluk = tum_alan - StorageInfo.megabytesAvailable();
        yuzde = (doluluk * 100) / tum_alan;
        return yuzde;
    }


    TextView empty;
    TextView total;
    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.clear_cache);
        context = ClearCache.this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.cToolbar);
        setSupportActionBar(toolbar);
        clearCacheOfStorage = (FloatingActionButton) findViewById(R.id.clearCacheStorage);
        sizeOfStorage = (ArcProgress) findViewById(R.id.freeSizeOfStorage);
        empty = (TextView) findViewById(R.id.empty);
        total = (TextView) findViewById(R.id.total);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            sizeOfStorage.setFinishedStrokeColor(getColor(R.color.colorFinished));
            sizeOfStorage.setUnfinishedStrokeColor(getColor(R.color.colorUnfinished));
        }else {
            sizeOfStorage.setFinishedStrokeColor(getResources().getColor(R.color.colorFinished));
            sizeOfStorage.setUnfinishedStrokeColor(getResources().getColor(R.color.colorUnfinished));
        }

        empty.setText(getString(R.string.avalaible_size_of_storage)+String.valueOf(StorageInfo.megabytesAvailable()+" MB"));
        total.setText(getString(R.string.total_size_of_storage)+String.valueOf(StorageInfo.getTotalSize()+" MB"));


        if (Build.VERSION.SDK_INT >= 23) {
            int A = 1;
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
            }else {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, A);
            }
        }

        sizeOfStorage.setProgress(storagePercentage());

        clearCacheOfStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(context,ClearingCache.class);
                startActivity(i);
                finish();
            }
        });

    }



}
