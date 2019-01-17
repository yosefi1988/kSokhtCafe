package ir.sajjadyosefi.kartsokhtcafebaazar.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;
import ir.sajjadyosefi.kartsokhtcafebaazar.R;
import ir.sajjadyosefi.kartsokhtcafebaazar.async.AsyncSendMessage;
import ir.sajjadyosefi.kartsokhtcafebaazar.model.Message;
import ir.sajjadyosefi.kartsokhtcafebaazar.view.PersianTextView;


@SuppressLint("ValidFragment")
public class FragmentContactUs extends Fragment {


    //Bundle String list
    public static final String Type = "TYPE";

    //Create New Activity List
    public static final int CONTACT_US              = 5;
    public static final int ORDER_APP               = 6;
    public static final int SUGGESTION              = 7;
    public static int messageType = 0;
    DilatingDotsProgressBar PBSjd ;


    Context context;
    PersianTextView tvRadio1,tvRadio2,tvRadio3,tvField1,tvField2;
    TextView tvTitle;
    RadioButton radioButton1,radioButton2,radioButton3;
    TextView tvTitleText02;
    EditText etField1;
    EditText etField2;
    EditText etField3 ;
    Button btn1;
    Button btn2;
    ScrollView svScroll;


    public FragmentContactUs() {

    }


    public static FragmentContactUs newInstance() {
        FragmentContactUs fragmentDemo = new FragmentContactUs();
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
        final View view = inflater.inflate(R.layout.fragment_contactus, container, false);
        context = getContext();




        tvTitleText02 = (TextView) view.findViewById(R.id.tvTitleText02);
        etField1 = (EditText) view.findViewById(R.id.etField1);
        etField2 = (EditText) view.findViewById(R.id.etField2);
        etField3 = (EditText) view.findViewById(R.id.etField3);
        btn1 = (Button) view.findViewById(R.id.btn1);
        radioButton1 = (RadioButton) view.findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) view.findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) view.findViewById(R.id.radioButton3);
        tvRadio1 = (PersianTextView) view.findViewById(R.id.tvRadio1);
        tvRadio2 = (PersianTextView) view.findViewById(R.id.tvRadio2);
        tvRadio3 = (PersianTextView) view.findViewById(R.id.tvRadio3);
        tvField1 = (PersianTextView) view.findViewById(R.id.tvField1);
        tvField2 = (PersianTextView) view.findViewById(R.id.tvField2);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        svScroll = (ScrollView) view.findViewById(R.id.svScroll);
        PBSjd = (DilatingDotsProgressBar) view.findViewById(R.id.PBSjd);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((etField1.getText().toString().length()>10 ||etField1.getText().toString().length()>10 ) && etField3.getText().toString().length()>10 ){
                    Message message = new Message();
                    message.setApplicationID(17);
                    message.setPhoneNumber(etField1.getText().toString());
                    message.setText(message.getPhoneNumber() + " : " + etField3.getText().toString());
                    switch (messageType) {
                        case CONTACT_US:{
                            message.setTitle("تماس با ما");
                            break;
                        }
                        case ORDER_APP:{
                            message.setTitle("سفارش نرم افزار");
                            break;
                        }
                        case SUGGESTION:{
                            message.setTitle("پیشنهادات و انتقادات");
                            break;
                        }
                    }
                    message.setType(messageType + "");
                    PBSjd.show();

                    AsyncSendMessage asyncSendMessage = new AsyncSendMessage(context,PBSjd,message);
                    asyncSendMessage.execute();
                }else
                    Toast.makeText(context,context.getString(R.string.notCorrectInput),Toast.LENGTH_LONG).show();


            }
        });


        //get Checked Radio
        ((RadioGroup)view.findViewById(R.id.rgRadios)).setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.radioButton1:
                                radioButton1.setChecked(true);
                                radioButton2.setChecked(false);
                                radioButton3.setChecked(false);
                                tvTitleText02.setText(context.getResources().getString(R.string.radio1Text));
                                messageType = CONTACT_US;
                                break;
                            case R.id.radioButton2:
                                radioButton1.setChecked(false);
                                radioButton2.setChecked(true);
                                radioButton3.setChecked(false);
                                tvTitleText02.setText(context.getResources().getString(R.string.radio2Text));
                                messageType = ORDER_APP;
                                break;
                            case R.id.radioButton3:
                                radioButton1.setChecked(false);
                                radioButton2.setChecked(false);
                                radioButton3.setChecked(true);
                                tvTitleText02.setText(context.getResources().getString(R.string.radio3Text));
                                messageType = SUGGESTION;
                                break;
                        }
                    }
                }
        );
        return view;
    }







}