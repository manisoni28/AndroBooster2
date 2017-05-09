package com.emre.androbooster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.*;


/**
 * Created by emre on 25.04.2016.
 */
public class Splash extends AppCompatActivity {
	
    
    private ThemeManager themeManager;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.splash);
       
   
        themeManager = new ThemeManager(Splash.this);
        
        themeManager.saveColor(0);
      
        SharedPreferences sharedPrefs = getSharedPreferences("buy", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt("h", 13);
        editor.commit();
        Button sn = (Button) findViewById(R.id.start_now);
        sn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Splash.this, MainActivity.class);
                Splash.this.startActivity(myIntent);
				finish();
            }
        });




    }
}
