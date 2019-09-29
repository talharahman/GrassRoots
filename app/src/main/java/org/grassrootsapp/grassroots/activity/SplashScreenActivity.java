package org.grassrootsapp.grassroots.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import org.grassrootsapp.grassroots.R;
import org.grassrootsapp.grassroots.activity.user.UserAuthorization;
import org.grassrootsapp.grassroots.utils.PetitionsFeedInterface;

public class SplashScreenActivity extends AppCompatActivity {
    private Thread splashTread;
    private ImageView imageView;
    private ImageView imageViewtext;
    private PetitionsFeedInterface mListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imageView = findViewById(R.id.splash);
        StartAnimations();
    }

    private void StartAnimations() {
//        mListener = (PetitionsFeedInterface) this;

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
//        ConstraintLayout constraintLayout=findViewById(R.id.lin_lay);
//        constraintLayout.clearAnimation();
//        constraintLayout.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        imageView.clearAnimation();
        imageView.startAnimation(anim);
     //   imageViewtext.clearAnimation();
     //   imageViewtext.startAnimation(anim);
        Intent intent=new Intent(this, UserAuthorization.class);
        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2500);

                }catch (InterruptedException e) {

                    e.printStackTrace();
                } finally {
                    startActivity(intent);
                    //mListener.openMainfeed();
                    // finish():
                }

            }
        };
        splashTread.start();

    }

}
