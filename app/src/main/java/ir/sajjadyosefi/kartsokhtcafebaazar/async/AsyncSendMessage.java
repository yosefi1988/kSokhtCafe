package ir.sajjadyosefi.kartsokhtcafebaazar.async;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;
import ir.sajjadyosefi.kartsokhtcafebaazar.model.Message;
import ir.sajjadyosefi.kartsokhtcafebaazar.model.response.MessageResponse;
import ir.sajjadyosefi.kartsokhtcafebaazar.networkLayout.RestApiHelper;
import ir.sajjadyosefi.kartsokhtcafebaazar.utility.CommonClass;

/**
 * Created by Other on 10/23/2016.
 */
public class AsyncSendMessage extends AsyncTask {
    private static DilatingDotsProgressBar PBSjd;
    private final Context mContext;
    private final Message mMessage;

    public AsyncSendMessage(Context context, DilatingDotsProgressBar PBSjd, Message message) {
        this.mContext = context;
        this.mMessage = message;
        this.PBSjd = PBSjd ;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//        if (mProgressBar != null)
//            mProgressBar.showNow();
    }


    @Override
    protected MessageResponse doInBackground(Object[] params) {
        if (CommonClass.isNetworkConnected(mContext)) {
            RestApiHelper httpUrlHelper = new RestApiHelper();
            MessageResponse changePasswordResponse = httpUrlHelper.SendMessage(mContext,mMessage);
            return changePasswordResponse;
        }else
            return null;
    }

    @Override
    protected void onPostExecute(Object response) {
        MessageResponse registerResponse = (MessageResponse) response;

        Toast.makeText(mContext,registerResponse.getServerStatus().getMessage(),Toast.LENGTH_LONG).show();
        if(registerResponse.getServerStatus().getCode() == 204){

//        else {
//            //ok
//
//            Global.mUser = registerResponse.getUser();
//
            //((Activity)mContext).finish();
//            Toast.makeText(mContext,registerResponse.getUser().getUserName(), Toast.LENGTH_SHORT).show();
        }

        PBSjd.hide();
     }
}
