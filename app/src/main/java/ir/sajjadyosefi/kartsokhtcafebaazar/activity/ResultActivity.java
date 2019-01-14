package ir.sajjadyosefi.kartsokhtcafebaazar.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import ir.sajjadyosefi.kartsokhtcafebaazar.R;
import ir.sajjadyosefi.kartsokhtcafebaazar.fragment.FragmentAzad;
import ir.sajjadyosefi.kartsokhtcafebaazar.fragment.FragmentMelli;


public class ResultActivity extends AppCompatActivity {

    public Context mContext ;
    public Button btn ;
    public TextView textView ;

    public static String result ;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_result);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        textView = (TextView) findViewById(R.id.textView);

        if (result.contains("خارج از کشور")){
            textView.setText("در صورتی که فیلتر شکن دارید آن را خاموش کنید");

            FragmentAzad.error = true;
            FragmentMelli.error = true;
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                textView.setText(Html.fromHtml(result, Html.FROM_HTML_MODE_COMPACT));
            } else {
                textView.setText(Html.fromHtml(result));
            }
            FragmentAzad.error = false;
            FragmentMelli.error = false;
        }


        getSupportActionBar().hide();

    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onDestroy() {
        super.onDestroy();

        result = "";
    }
}
