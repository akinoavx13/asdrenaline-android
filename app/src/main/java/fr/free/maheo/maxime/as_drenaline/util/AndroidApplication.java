package fr.free.maheo.maxime.as_drenaline.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by mmaheo on 22/06/2017.
 */

public class AndroidApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        AndroidApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return AndroidApplication.context;
    }

}
