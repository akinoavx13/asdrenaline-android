package fr.free.maheo.maxime.as_drenaline.data.source.actuality.remote;

import java.util.List;
import java.util.Stack;

import fr.free.maheo.maxime.as_drenaline.data.model.Actuality;
import fr.free.maheo.maxime.as_drenaline.data.source.Injection;
import fr.free.maheo.maxime.as_drenaline.data.source.actuality.ActualityDataSource;
import io.reactivex.Observable;
import retrofit2.http.Path;

/**
 * Created by mmaheo on 24/06/2017.
 */

public class ActualityRemoteDataSource implements ActualityDataSource {

    private static ActualityRemoteDataSource INSTANCE = null;

    private ActualityDataSource networkProvider;

    public ActualityRemoteDataSource(ActualityDataSource networkProvider) {
        this.networkProvider = networkProvider;
    }

    public static ActualityRemoteDataSource getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ActualityRemoteDataSource(Injection.provideActualityNetworkManager());
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<Actuality>> getActualities(@Path("categoryId") String categoryId) {
        return this.networkProvider.getActualities(categoryId);
    }
}
