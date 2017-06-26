package fr.free.maheo.maxime.as_drenaline.data.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.free.maheo.maxime.as_drenaline.data.source.actuality.ActualityDataSource;
import fr.free.maheo.maxime.as_drenaline.data.source.category.CategoryDataSource;
import fr.free.maheo.maxime.as_drenaline.data.source.event.EventDataSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mmaheo on 21/06/2017.
 */

public class NetworkProvider {

    private static NetworkProvider INSTANCE = null;

    private static final String BASE_URL = "http://151.80.57.80/api/";

    private CategoryDataSource categoryDataSource;

    private ActualityDataSource actualityDataSource;

    private EventDataSource eventDataSource;

    public NetworkProvider() {
        categoryDataSource = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CategoryDataSource.class);

        actualityDataSource = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ActualityDataSource.class);

        eventDataSource = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EventDataSource.class);
    }

    public static NetworkProvider getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NetworkProvider();
        }
        return INSTANCE;
    }

    public CategoryDataSource getCategoryManager() {
        return categoryDataSource;
    }

    public ActualityDataSource getActualityManager() {
        return actualityDataSource;
    }

    public EventDataSource getEventManager() {
        return eventDataSource;
    }
}
