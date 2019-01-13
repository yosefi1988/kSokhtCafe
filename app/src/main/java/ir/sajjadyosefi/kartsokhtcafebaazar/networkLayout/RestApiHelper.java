package ir.sajjadyosefi.kartsokhtcafebaazar.networkLayout;

import android.content.Context;
import ir.sajjadyosefi.kartsokhtcafebaazar.model.Message;
import ir.sajjadyosefi.kartsokhtcafebaazar.model.response.MessageResponse;

public class RestApiHelper {
    HttpUrlconnectionHelper httpUrlconnectionHelper = new HttpUrlconnectionHelper();


    //OK
    public MessageResponse SendMessage(Context context, Message message) {
        MessageResponse aaaaaaaaaaaaaaa = (MessageResponse) httpUrlconnectionHelper.PostJsonToServer(
                "http://test.sajjadyosefi.ir/Messaging/ContactUs",
                "ApplicationId=17&" +
                        "phoneNumber=" +            message.getPhoneNumber() + "&" +
                        "title=" +                  message.getTitle() + "&" +
                        "text=" +                   message.getText() + "&" +
                        "type=" +                   message.getType(),
                MessageResponse.class
                );
        return  aaaaaaaaaaaaaaa;
    }

}
