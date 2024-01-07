package com.jainshobhit.coldstar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.os.VibratorManager;
import android.util.Log;
import android.widget.FrameLayout;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity {

    SmoothBottomBar bottomBar;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        setContentView(R.layout.activity_main);
        bottomBar=findViewById(R.id.mainBottomNav);
        frameLayout=findViewById(R.id.mainFrameLayout);

        final VibrationEffect[] vibrationEffect = new VibrationEffect[1];






        bottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {
                if(i==0){
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        vibrationEffect[0] = VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE);
                        vibrator.cancel();
                        vibrator.vibrate(vibrationEffect[0]);
                    }
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.mainFrameLayout, new HomeFragment());
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                }
                else if(i==1){
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        vibrationEffect[0] = VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE);
                        vibrator.cancel();
                        vibrator.vibrate(vibrationEffect[0]);
                    }
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.mainFrameLayout, new SearchFragment());
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                }
                else if(i==2){
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        vibrationEffect[0] = VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE);
                        vibrator.cancel();
                        vibrator.vibrate(vibrationEffect[0]);
                    }


                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.mainFrameLayout, new TrendingFragment());
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                }
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrameLayout, new HomeFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(getSupportFragmentManager().getFragments().isEmpty()) finish();
    }
}