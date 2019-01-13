package ir.sajjadyosefi.kartsokhtcafebaazar.model.response;

/**
 * Created by sajjad on 10/31/2016.
 */
public class ServerResponseBase {
    private ResponseStatus responseStatus ;
    private ResponseStatus  serverStatus;

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }


    public ResponseStatus getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(ResponseStatus serverStatus) {
        this.serverStatus = serverStatus;
    }
}
