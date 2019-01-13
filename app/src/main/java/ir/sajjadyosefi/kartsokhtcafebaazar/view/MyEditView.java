package ir.sajjadyosefi.kartsokhtcafebaazar.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class MyEditView extends android.support.v7.widget.AppCompatEditText {

    public MyEditView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEditView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "BYekan.ttf");
            setTypeface(tf);
        }
    }

}