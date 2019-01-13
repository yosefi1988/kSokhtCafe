package ir.sajjadyosefi.kartsokhtcafebaazar.networkLayout.retrofit;

import android.content.Context;
import okhttp3.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;
import java.net.URL;

import static ir.sajjadyosefi.kartsokhtcafebaazar.networkLayout.Url.REST_API_IP_ADDRESS;


/**
 * Created by sajjad on 11/7/2018.
 */

public class RetrofitHelper {

    private static samaniumRestApi service;
    private static RetrofitHelper retrofitHelper;

    private RetrofitHelper() {
        try {
//          URL url = new URL("https://www.sb24.com");
            URL url = new URL(REST_API_IP_ADDRESS);
            String serverHostname = url.getHost();
            HostSelectionInterceptor interceptor = new HostSelectionInterceptor();

            Dispatcher dispatcher = new Dispatcher();
            dispatcher.setMaxRequests(1);

            // OkHttp 3
            OkHttpClient client =
                    new OkHttpClient().newBuilder()
                            .addInterceptor(interceptor)
                            .build();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(REST_API_IP_ADDRESS)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(client)
                    .build();

            service = retrofit.create(samaniumRestApi.class);

            int a = 4 ;
            a++;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
//    private RetrofitHelper() {
//        try {
////          URL url = new URL("https://www.sb24.com");
//            URL url = new URL(Url.REST_API_IP_ADDRESS);
//            String serverHostname = url.getHost();
//            HostSelectionInterceptor interceptor = new HostSelectionInterceptor();
//
//            Dispatcher dispatcher = new Dispatcher();
//            dispatcher.setMaxRequests(1);
//
////            Interceptor interceptor = new Interceptor() {
////                @Override
////                public Response intercept(Chain chain) throws IOException {
////                    SystemClock.sleep(1000);
////                    return chain.proceed(chain.request());
////                }
////            };
//
//
//            // OkHttp 3
//            OkHttpClient client =
//                    new OkHttpClient().newBuilder()
//                            .sslSocketFactory(TrustKit.getInstance().getSSLSocketFactory(serverHostname),TrustKit.getInstance().getTrustManager(serverHostname))
//                            .addInterceptor(interceptor)
//                            .build();
//
//
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl(REST_API_IP_ADDRESS)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .client(client)
//                    .build();
//
//            service = retrofit.create(samaniumRestApi.class);
//
//            int a = 4 ;
//            a++;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    public static RetrofitHelper getInstance(Context mContext) {
        if (retrofitHelper == null) {
            retrofitHelper = new RetrofitHelper();
        }
        return retrofitHelper;
    }

    public static final class HostSelectionInterceptor implements Interceptor {
        private volatile String host;

        public void setHost(String host) {
            this.host = host;
        }

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String host = this.host;
            if (host != null) {
                //HttpUrl newUrl = request.url().newBuilder()
                //    .host(host)
                //    .build();
                HttpUrl newUrl = HttpUrl.parse(host);
                request = request.newBuilder()
                        .url(newUrl)
                        .build();
            }

//            SystemClock.sleep(1000 * 10);

            return chain.proceed(request);
        }
    }

    public void callPelackservice(String platesearch, String xtype, String plateusage, String platecode, Callback<String> callback) {
        Call<String> userCall = service.reportGetUserCount(platesearch,xtype,plateusage,platecode);
        userCall.enqueue(callback);
    }


//compileSdkVersion
//    public void databaseKey(ConfigRequest search, Callback<ConnectionCheckResponse> callback) {
//        Call<ConnectionCheckResponse> userCall = service.databaseKey(search);
//        userCall.enqueue(callback);
//    }

//    public void config(ConfigRequest search, Callback<ConfigResponse> callback) {
//        Call<ConfigResponse> userCall = service.config(search);
//        userCall.enqueue(callback);
//    }

}
