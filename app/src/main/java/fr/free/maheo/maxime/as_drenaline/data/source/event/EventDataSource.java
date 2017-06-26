package fr.free.maheo.maxime.as_drenaline.data.source.event;

import java.util.List;

import fr.free.maheo.maxime.as_drenaline.data.model.Actuality;
import fr.free.maheo.maxime.as_drenaline.data.model.Category;
import fr.free.maheo.maxime.as_drenaline.data.model.Event;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by mmaheo on 21/06/2017.
 */

public interface EventDataSource {

    @GET("events")
    Observable<List<Event>> getEvents();

}
