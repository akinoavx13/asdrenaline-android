package fr.free.maheo.maxime.as_drenaline.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by mmaheo on 26/06/2017.
 */

public class FireBaseCloudMessagingService extends FirebaseInstanceIdService {

    private static final String TAG = FireBaseCloudMessagingService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        try {
            Log.d(TAG, "Try to send token");

            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormBody.Builder()
                    .add("token", refreshedToken != null ? refreshedToken : "")
                    .build();

            Request request = new Request.Builder()
                    .url("http://151.80.57.80/api/token")
                    .post(formBody)
                    .build();

           client.newCall(request).enqueue(new Callback() {
               @Override
               public void onFailure(Call call, IOException e) {
                   Log.d(TAG, "Fail to send token " + e.getMessage());
                   e.printStackTrace();
               }

               @Override
               public void onResponse(Call call, Response response) throws IOException {
                   if (response.isSuccessful()){
                       Log.d(TAG, "Token sent");
                   } else {
                       Log.d(TAG, "Token not sent : " + response);
                   }
               }
           });

        } catch (Exception ignored) {

        }
    }

}
