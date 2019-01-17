package ir.sajjadyosefi.kartsokhtcafebaazar.fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.zarinpal.ewallets.purchase.OnCallbackRequestPaymentListener;
import com.zarinpal.ewallets.purchase.PaymentRequest;
import com.zarinpal.ewallets.purchase.ZarinPal;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;
import ir.sajjadyosefi.kartsokhtcafebaazar.R;
import ir.sajjadyosefi.kartsokhtcafebaazar.activity.MainActivity;
import ir.sajjadyosefi.kartsokhtcafebaazar.activity.ResultActivity;
import ir.sajjadyosefi.kartsokhtcafebaazar.adapter.SpinnerAdapterA;
import ir.sajjadyosefi.kartsokhtcafebaazar.classes.Global;
import ir.sajjadyosefi.kartsokhtcafebaazar.model.config.Configuration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@SuppressLint("ValidFragment")
public class FragmentAzad extends Fragment {
    public static Context mContext;
    private static EditText editText1 ;
    private static String editText1Value ;
    private static String editTextXValue ;
    private static boolean goToVipValue = false;
    DilatingDotsProgressBar PBSjd ;



    Spinner listView;
    private Button button ,buttonContactUS;
    String[] countryNames = {"منطقه", "کیش", "چابهار", "قشم", };
    int selectedChar;
    String[] countryArray = {"India", "Pakistan", "USA", "UK"};

    public static final String ARG_PAGE = "ARG_PAGE";


    public FragmentAzad() {

    }


    public static FragmentAzad newInstance() {
        FragmentAzad fragmentDemo = new FragmentAzad();
        Bundle args = new Bundle();
//        args.putInt("someInt", someInt);
//        args.putString("someTitle", someTitle);
        fragmentDemo.setArguments(args);
        return fragmentDemo;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_azad, container, false);
        mContext = getContext();


        editText1 = (EditText) view.findViewById(R.id.editText1);
        PBSjd = (DilatingDotsProgressBar) view.findViewById(R.id.PBSjd);
        editText1 = (EditText) view.findViewById(R.id.editText1);
        button = (Button) view.findViewById(R.id.button);
        listView = (Spinner) view.findViewById(R.id.spinner2);


        ArrayAdapter adapter = new ArrayAdapter<String>(mContext, R.layout._custom_spinner_text, R.id.textview, countryArray);
        final SpinnerAdapterA spinnerAdapter = new SpinnerAdapterA(mContext, countryNames);
        listView.setAdapter(spinnerAdapter);
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedChar = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedChar = 0;
            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PBSjd.show();
                button.setEnabled(false);
                Global.retrofit2.config(new Callback<Configuration>() {
                    @Override
                    public void onResponse(Call<Configuration> call, Response<Configuration> response) {
                        if (validData()) {
                            editText1Value = editText1.getText().toString();
                            editTextXValue = listView.getSelectedItemPosition() + "";
                            if (error == false) {
                                if ((MainActivity.isVip())){
                                    callService();
                                }else {
                                    if (checkResult(response)) {
                                        ShowSelectSturceDialog(mContext);
                                    } else {
                                        callService();
                                    }
                                }
                            } else {
                                callService();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Configuration> call, Throwable t) {
                        Toast.makeText(mContext,"عملیات با خطا مواجه شد",Toast.LENGTH_SHORT).show();
                        Toast.makeText(mContext,"اینترنت خود را چک کنید",Toast.LENGTH_SHORT).show();
                        //error = true;

                        if (PBSjd != null)
                            PBSjd.hide();

                        if (button != null)
                            button.setEnabled(true);
                    }
                });



            }
        });

        return view;
    }



    public void ShowSelectSturceDialog(final Context context){
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.pay_layout, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialoglayout);
        final AlertDialog alertDialog1 = builder.create();
//        alertDialog1.getWindow().getAttributes().windowAnimations = R.style.alertDialogAnimation;
        alertDialog1.setCancelable(false);

        final Button btn500 = (Button) dialoglayout.findViewById(R.id.button500);
        final Button btn10 = (Button) dialoglayout.findViewById(R.id.button10);
//        btn1.setText(btn1Text);
//        btn2.setText(btn2Text);
        btn500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payment(false);
                alertDialog1.dismiss();
            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payment(true);
                alertDialog1.dismiss();
            }
        });

        //titile
//        TextView textViewTitle = (TextView) dialoglayout.findViewById(R.id.textView1);
//        textViewTitle.setText("پرداخت");

        //text
//        TextView textViewText = (TextView) dialoglayout.findViewById(R.id.textView1);
//        textViewText.setText(text);


        try {
            alertDialog1.show();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void payment(boolean goToVip) {
        ZarinPal purches = ZarinPal.getPurchase(mContext);
        PaymentRequest payment = ZarinPal.getPaymentRequest();

        payment.setMerchantID("e8a913e8-f089-11e6-8dec-005056a205be");
        if (goToVip){
            payment.setAmount(100);
            goToVipValue = true ;
        }else {
            payment.setAmount(100);
            goToVipValue = false;
        }
        payment.setDescription("هزینه استعلام کارت سوخت");
        payment.setCallbackURL("return://zarinpalpayment");

        purches.startPayment(payment, new OnCallbackRequestPaymentListener() {
            @Override
            public void onCallbackResultPaymentRequest(int status, String authority, Uri paymentGatewayUri, Intent intent) {

                if (status == 100){
                    //ok
                }else {
                    //error in payment
                }

                startActivity(intent);
            }
        });

    }



    private boolean checkResult(Response<Configuration> response)  {
        Boolean aBoolean = false;

        try {
            PackageInfo pInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            String versionName = pInfo.versionName;
            if (versionName.contains("a")) {
                aBoolean = response.body().getConfiguration().play;

            } else if (versionName.contains("b")) {
                aBoolean = response.body().getConfiguration().bazaar;

            } else if (versionName.contains("m")) {
                aBoolean = response.body().getConfiguration().myket;

            } else if (versionName.contains("i")) {
                aBoolean = response.body().getConfiguration().iranapps;

            } else if (versionName.contains("j")) {
                aBoolean = response.body().getConfiguration().jhobin;

            } else if (versionName.contains("k")) {
                aBoolean = response.body().getConfiguration().kandoo;

            } else {
                aBoolean = response.body().getConfiguration().play;

            }

        }catch (Exception e){
            e.printStackTrace();
            aBoolean = false;
        }
        return aBoolean;
    }

    public static boolean error = false;

    private void callService() {

        Global.retrofit.callPelackservice("platesearch", "2", "1", "0014" +(selectedChar <= 9 ? "0" + selectedChar : selectedChar) + editText1.getText().toString() , new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                ResultActivity.result = response.body();
                Intent intent = new Intent(mContext, ResultActivity.class);
                startActivity(intent);
                error = false ;
                editText1Value = null;
                editTextXValue = null;

                if (PBSjd != null)
                    PBSjd.hide();

                if (button != null)
                    button.setEnabled(true);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(mContext,"عملیات با خطا مواجه شد",Toast.LENGTH_SHORT).show();
                Toast.makeText(mContext,"دوباره تلاش کنید",Toast.LENGTH_SHORT).show();
                error = true;

                if (PBSjd != null)
                    PBSjd.hide();

                if (button != null)
                    button.setEnabled(true);
            }
        });
    }


    private boolean validData() {
        Boolean isValid = false;
        if (selectedChar != 0){
            if (editText1.getText().toString().length() == 5){
                try {
                    new Integer(Integer.parseInt(editText1.getText().toString()));
                    isValid = true;
                }catch (Exception e){
                    isValid = false;
                }
            }
        }

        if (isValid == false){
            Toast.makeText(mContext,"در ورود اطلاعات دقت فرمایید",Toast.LENGTH_SHORT).show();
            PBSjd.hide();
            button.setEnabled(true);
        }
        return isValid;
    }


}


