package fr.free.maheo.maxime.as_drenaline.data.source.actuality;

import java.util.List;

import fr.free.maheo.maxime.as_drenaline.data.model.Actuality;
import io.reactivex.Observable;
import retrofit2.http.Path;

/**
 * Created by mmaheo on 21/06/2017.
 */

public class ActualityRepository implements ActualityDataSource {

    private static ActualityRepository INSTANCE = null;

    private final ActualityDataSource remoteDataSource;

    public ActualityRepository(final ActualityDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public static ActualityRepository getInstance(final ActualityDataSource remoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new ActualityRepository(remoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<Actuality>> getActualities(@Path("categoryId") String categoryId) {
        return getRemoteActualities(categoryId);
    }

    private Observable<List<Actuality>> getRemoteActualities(@Path("categoryId") String categoryId) {
        return remoteDataSource.getActualities(categoryId);
    }
}
