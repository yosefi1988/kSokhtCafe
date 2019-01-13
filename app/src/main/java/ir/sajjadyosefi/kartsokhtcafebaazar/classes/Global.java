package ir.sajjadyosefi.kartsokhtcafebaazar.classes;

import android.app.Application;
import android.content.Context;
import com.splunk.mint.Mint;
import ir.sajjadyosefi.kartsokhtcafebaazar.networkLayout.retrofit.RetrofitHelper;
import ir.sajjadyosefi.kartsokhtcafebaazar.networkLayout.retrofit.RetrofitHelper2;


public class Global extends Application {
    public static RetrofitHelper retrofit ;
    public static RetrofitHelper2 retrofit2 ;
    public static Context mContext ;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        retrofit = RetrofitHelper.getInstance(mContext);
        retrofit2 = RetrofitHelper2.getInstance(mContext);
        Mint.initAndStartSession(mContext, "fff2fc78");

    }
}