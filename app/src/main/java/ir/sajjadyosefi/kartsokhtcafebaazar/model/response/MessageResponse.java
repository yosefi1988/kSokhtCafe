package ir.sajjadyosefi.kartsokhtcafebaazar.model.response;


import ir.sajjadyosefi.kartsokhtcafebaazar.model.Message;

/**
 * Created by sajjad on 3/2/2017.
 */
public class MessageResponse extends ServerResponseBase {
    private Message message ;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }



}

