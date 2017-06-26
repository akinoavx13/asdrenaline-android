package fr.free.maheo.maxime.as_drenaline.data.source.event;

import java.util.List;

import fr.free.maheo.maxime.as_drenaline.data.model.Category;
import fr.free.maheo.maxime.as_drenaline.data.model.Event;
import io.reactivex.Observable;

/**
 * Created by mmaheo on 26/06/2017.
 */

public class EventRepository implements EventDataSource {

    private static EventRepository INSTANCE = null;

    private final EventDataSource remoteDataSource;

    public EventRepository(EventDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public static EventRepository getInstance(final EventDataSource remoteDataSource) {
        if(INSTANCE == null) {
            INSTANCE = new EventRepository(remoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<Event>> getEvents() {
        return getRemoteEvents();
    }

    private Observable<List<Event>> getRemoteEvents() {
        return remoteDataSource.getEvents();
    }
}
