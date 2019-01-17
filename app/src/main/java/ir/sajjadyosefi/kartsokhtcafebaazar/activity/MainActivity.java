package ir.sajjadyosefi.kartsokhtcafebaazar.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import ir.sajjadyosefi.kartsokhtcafebaazar.R;

public class MainActivity extends AppCompatActivity {

    public static Context mContext;
    ImageButton imageButton1,imageButton2,imageButton3,imageButton4;



    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        Fabric.with(this, new Crashlytics());

        imageButton1 = findViewById(R.id.imageButton1);
        imageButton2 = findViewById(R.id.imageButton2);
        imageButton3 = findViewById(R.id.imageButton3);
        imageButton4 = findViewById(R.id.imageButton4);



        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContainerActivity.type = 1 ;
                mContext.startActivity(new Intent(mContext, ContainerActivity.class));

            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContainerActivity.type = 2 ;
                mContext.startActivity(new Intent(mContext, ContainerActivity.class));

            }
        });
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContainerActivity.type = 3 ;
                mContext.startActivity(new Intent(mContext, ContainerActivity.class));

            }
        });

        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"در حال آماده سازی هستیم" , Toast.LENGTH_LONG).show();
            }
        });
    }

    public static boolean isVip(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("android.sajjadyosefi.ir.ksokht" , Context.MODE_PRIVATE);
        String key = "android.sajjadyosefi.ir.ksokht";
        return sharedPreferences.getBoolean(key,false);
    }


}