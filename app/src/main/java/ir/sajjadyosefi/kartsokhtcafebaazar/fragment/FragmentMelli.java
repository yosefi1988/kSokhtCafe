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
import com.zarinpal.ewallets.purchase.OnCallbackVerificationPaymentListener;
import com.zarinpal.ewallets.purchase.PaymentRequest;
import com.zarinpal.ewallets.purchase.ZarinPal;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;
//import ir.adad.banner.AdadBannerAd;
import ir.sajjadyosefi.kartsokhtcafebaazar.R;
import ir.sajjadyosefi.kartsokhtcafebaazar.activity.ContainerActivity;
import ir.sajjadyosefi.kartsokhtcafebaazar.activity.MainActivity;
import ir.sajjadyosefi.kartsokhtcafebaazar.activity.ResultActivity;
import ir.sajjadyosefi.kartsokhtcafebaazar.adapter.SpinnerAdapterA;
import ir.sajjadyosefi.kartsokhtcafebaazar.classes.Global;
import ir.sajjadyosefi.kartsokhtcafebaazar.model.config.Configuration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@SuppressLint("ValidFragment")
public class FragmentMelli extends Fragment {
    public static Context mContext;
    private static EditText editText1 ;
    private static String editText1Value ;
    private static EditText editText2 ;
    private static String editText2Value ;
    private static EditText editText3 ;
    private static String editText3Value ;
    private static String editTextXValue ;
    private static boolean goToVipValue = false;
    DilatingDotsProgressBar PBSjd ;


    Spinner listView;
    private Button button ,buttonContactUS;
    String[] countryNames={"حرف","الف","ب","ت","ج","د","س","ص","ط","ع","ق","ل","م","ن","و","ه","ي","ک","ژ"};
    private static int selectedChar ;
    String[] countryArray = {"India", "Pakistan", "USA", "UK"};

    public static final String ARG_PAGE = "ARG_PAGE";


    public FragmentMelli() {

    }

    public static FragmentMelli newInstance() {
        FragmentMelli fragmentDemo = new FragmentMelli();
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
        final View view = inflater.inflate(R.layout.fragment_melli, container, false);
        mContext = getContext();





        editText1 = (EditText) view.findViewById(R.id.editText1);
        editText2 = (EditText) view.findViewById(R.id.editText2);
        editText3 = (EditText) view.findViewById(R.id.editText3);
        PBSjd = (DilatingDotsProgressBar) view.findViewById(R.id.PBSjd);
        button = (Button) view.findViewById(R.id.button);
        listView = (Spinner) view.findViewById(R.id.spinner2);


        ArrayAdapter adapter = new ArrayAdapter<String>(mContext, R.layout._custom_spinner_text,R.id.textview, countryArray);
        final SpinnerAdapterA spinnerAdapter = new SpinnerAdapterA(mContext,countryNames);
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
                CheckFilter();
            }
        });
//        fetchWelcome();


        Uri data = getActivity().getIntent().getData();
        ZarinPal.getPurchase(mContext).verificationPayment(data, new OnCallbackVerificationPaymentListener() {
            @Override
            public void onCallbackResultVerificationPayment(boolean isPaymentSuccess, String refID, PaymentRequest paymentRequest) {
                if (PBSjd != null)
                    PBSjd.show();

                if (button != null)
                    button.setEnabled(false);

                if (editText1Value != null) {
                    if (editText1 != null) {
                        editText1.setText(editText1Value);
                    }
                }

                if (editText2Value != null) {
                    if (editText2 != null) {
                        editText2.setText(editText2Value);
                    }
                }

                if (editText3Value != null) {
                    if (editText3 != null) {
                        editText3.setText(editText3Value);
                    }
                }

                if (editTextXValue != null) {
                    listView.setSelection(Integer.parseInt(editTextXValue));
                }

                if(isPaymentSuccess){
                    error = false;

                    if (goToVipValue){
                        ((ContainerActivity)mContext).saveVip(true);
                    }
                    //payment ok
                    //refID
                    callService();
                    //toast


                }else {
                    //not ok
                    //refID

                    if (refID == null){

                    }else {
                        error = true;
                    }

                    PBSjd.hide();
                    button.setEnabled(true);

                }
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
            payment.setAmount(10500);
            goToVipValue = true ;
        }else {
            payment.setAmount(500);
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

    private void CheckFilter() {

        Global.retrofit.callPelackservice("platesearch","1", "1","66" +  "03" + "25"  + "566" , new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body().contains("خارج از کشور")) {
                    Toast.makeText(mContext,"در صورتی که از فیلتر شکن استفاده می کنید آن را خاموش کنید.",Toast.LENGTH_SHORT).show();
                }else {
                    PBSjd.show();
                    button.setEnabled(false);
                    Global.retrofit2.config(new Callback<Configuration>() {
                        @Override
                        public void onResponse(Call<Configuration> call, Response<Configuration> response) {

                            if (validData()) {
                                editText1Value = editText1.getText().toString();
                                editText2Value = editText2.getText().toString();
                                editText3Value = editText3.getText().toString();
                                editTextXValue = listView.getSelectedItemPosition() + "";
                                if (error == false) {
                                    if (MainActivity.isVip()){
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
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
//                Toast.makeText(mContext,"عملیات با خطا مواجه شد",Toast.LENGTH_SHORT).show();
//                Toast.makeText(mContext,"دوباره تلاش کنید",Toast.LENGTH_SHORT).show();
//                error = true;
//
//                if (PBSjd != null)
//                    PBSjd.hide();
//
//                if (button != null)
//                    button.setEnabled(true);
            }
        });
    }


    private void callService() {

        Global.retrofit.callPelackservice("platesearch","1", "1",editText1Value +  (Integer.parseInt(editTextXValue) <= 9 ? "0" + editTextXValue : editTextXValue) + editText3Value  + editText2Value , new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                ResultActivity.result = response.body();
                Intent intent = new Intent(mContext, ResultActivity.class);
                startActivity(intent);
                error = false ;
                editText1Value = null;
                editText2Value = null;
                editText3Value = null;
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
            if (editText1.getText().toString().length() == 2){
                try {
                    new Integer(Integer.parseInt(editText1.getText().toString()));

                    if (editText2.getText().toString().length() == 3){
                        try {
                            new Integer(Integer.parseInt(editText2.getText().toString()));
                            if (editText3.getText().toString().length() == 2){
                                try {
                                    new Integer(Integer.parseInt(editText3.getText().toString()));
                                    isValid = true;

                                }catch (Exception e){
                                    isValid = false;
                                }
                            }

                        }catch (Exception e){
                            isValid = false;
                        }
                    }

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