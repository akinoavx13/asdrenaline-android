package fr.free.maheo.maxime.as_drenaline.data.source.actuality;

import java.util.List;

import fr.free.maheo.maxime.as_drenaline.data.model.Actuality;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by mmaheo on 21/06/2017.
 */

public interface ActualityDataSource {

    @GET("categories/" + "{categoryId}" + "/news")
    Observable<List<Actuality>> getActualities(@Path("categoryId") String categoryId);

}
