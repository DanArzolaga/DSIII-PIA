package com.example.sqliteandroidstudiojava;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    Handler handle = new Handler();
    long delay = 200;
    ImageView imageLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash );

        imageLogo = findViewById( R.id.imageLogo );

        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder( imageLogo,
                PropertyValuesHolder.ofFloat( "scaleX", 1.2f ),
                PropertyValuesHolder.ofFloat( "ScaleY", 1.2f )
        );

        //cambiar la duracion
        objectAnimator.setDuration( 500 );

        //repetir la cuenta
        objectAnimator.setRepeatCount( ValueAnimator.INFINITE );

        //cambiar el modo de repeticion
        objectAnimator.setRepeatMode( ValueAnimator.REVERSE );

        //inicia la animacion
        objectAnimator.start();

        //inicializando el handler
        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                //redireccionar al login
                startActivity( new Intent(SplashActivity.this, LoginActivity.class)
                        .setFlags( Intent.FLAG_ACTIVITY_NEW_TASK ) );

                //terminar la actividad
                finish();
            }
        }, 4000 );

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            handle.postDelayed(runnable, delay);
        }
    };


}