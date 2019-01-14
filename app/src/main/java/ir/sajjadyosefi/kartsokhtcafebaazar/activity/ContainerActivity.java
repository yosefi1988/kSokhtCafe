package ir.sajjadyosefi.kartsokhtcafebaazar.activity;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import ir.sajjadyosefi.kartsokhtcafebaazar.R;
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
            Fragment newFragment = new FragmentMelli();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.output, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        }else if (type == 2){
            Fragment newFragment = new FragmentMelli();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.output, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }else {
            Fragment newFragment = new FragmentMelli();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.output, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}