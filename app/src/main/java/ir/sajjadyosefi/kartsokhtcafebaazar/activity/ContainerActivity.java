package ir.sajjadyosefi.kartsokhtcafebaazar.activity;


import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import ir.sajjadyosefi.kartsokhtcafebaazar.R;
import ir.sajjadyosefi.kartsokhtcafebaazar.fragment.FragmentAzad;
import ir.sajjadyosefi.kartsokhtcafebaazar.fragment.FragmentContactUs;
import ir.sajjadyosefi.kartsokhtcafebaazar.fragment.FragmentMelli;

public class ContainerActivity extends AppCompatActivity {

    public static Context mContext;
    public static int type = 0 ;


    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        mContext = this;

        if (type == 1){
//            Fragment newFragment = new FragmentMelli();
//            FragmentTransaction transaction = getFragmentManager().beginTransaction();
//            transaction.replace(R.id.output, newFragment);
//            transaction.addToBackStack(null);
//            transaction.commit();

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            FragmentMelli fragmentDemo = FragmentMelli.newInstance();
            ft.replace(R.id.output, fragmentDemo);
            ft.commit();

        }else if (type == 2){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            FragmentContactUs fragmentDemo = FragmentContactUs.newInstance();
            ft.replace(R.id.output, fragmentDemo);
            ft.commit();
        }else {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            FragmentAzad fragmentDemo = FragmentAzad.newInstance();
            ft.replace(R.id.output, fragmentDemo);
            ft.commit();
        }


    }

    public static boolean saveVip(boolean isVip){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("android.sajjadyosefi.ir.ksokht" , Context.MODE_PRIVATE);
        String key = "android.sajjadyosefi.ir.ksokht";
        return sharedPreferences.edit().putBoolean(key,isVip).commit();
    }

}