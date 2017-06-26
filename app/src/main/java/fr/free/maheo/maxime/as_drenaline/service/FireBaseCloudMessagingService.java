package fr.free.maheo.maxime.as_drenaline.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by mmaheo on 26/06/2017.
 */

public class FireBaseCloudMessagingService extends FirebaseInstanceIdService {

    private static final String TAG = FireBaseCloudMessagingService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
    }

}
