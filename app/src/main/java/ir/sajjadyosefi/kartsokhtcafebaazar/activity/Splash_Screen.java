package ir.sajjadyosefi.kartsokhtcafebaazar.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import ir.sajjadyosefi.kartsokhtcafebaazar.R;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;


public class Splash_Screen extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        this.context = this ;
        getSupportActionBar().hide();


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                context.startActivity(new Intent(context, MainActivity.class));
//                overridePendingTransition(R.anim.fadeout, R.anim.fadein);
                ((Activity)context).finish();

            }
        },1500);
    }

}
