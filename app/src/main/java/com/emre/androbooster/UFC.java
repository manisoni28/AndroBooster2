package com.emre.androbooster;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/**
 * Created by emre on 24.12.2016.
 */

public class UFC extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openUFC();
        finish();
    }
    private void openUFC() {
        Intent intent = getPackageManager().getLaunchIntentForPackage("com.slickways.quickcharge");
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("market://details?id=" + "com.slickways.quickcharge"));
            startActivity(intent);
        }
    }
}
