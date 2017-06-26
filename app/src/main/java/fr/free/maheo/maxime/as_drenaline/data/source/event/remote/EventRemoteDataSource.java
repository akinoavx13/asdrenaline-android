package fr.free.maheo.maxime.as_drenaline.data.source.event.remote;

import java.util.List;

import fr.free.maheo.maxime.as_drenaline.data.model.Event;
import fr.free.maheo.maxime.as_drenaline.data.source.Injection;
import fr.free.maheo.maxime.as_drenaline.data.source.event.EventDataSource;
import io.reactivex.Observable;

/**
 * Created by mmaheo on 26/06/2017.
 */

public class EventRemoteDataSource implements EventDataSource {

    private static EventRemoteDataSource INSTANCE = null;

    private EventDataSource networkProvider;

    public EventRemoteDataSource(EventDataSource networkProvider) {
        this.networkProvider = networkProvider;
    }

    public static EventRemoteDataSource getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new EventRemoteDataSource(Injection.provideEventNetworkManager());
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<Event>> getEvents() {
        return this.networkProvider.getEvents();
    }
}
