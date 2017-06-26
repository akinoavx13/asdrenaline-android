package fr.free.maheo.maxime.as_drenaline.view.event;

import java.util.List;

import fr.free.maheo.maxime.as_drenaline.data.model.Event;
import fr.free.maheo.maxime.as_drenaline.data.source.event.EventDataSource;
import fr.free.maheo.maxime.as_drenaline.util.scheduler.BaseSchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by mmaheo on 26/06/2017.
 */

public class EventPresenter implements EventContract.Presenter {

    private EventContract.View view;

    private CompositeDisposable subscription;

    private BaseSchedulerProvider schedulerProvider;

    private EventDataSource eventDataSource;

    private List<Event> caches;

    public EventPresenter(EventContract.View view, BaseSchedulerProvider schedulerProvider, EventDataSource eventDataSource) {
        this.view = view;
        this.schedulerProvider = schedulerProvider;
        this.eventDataSource = eventDataSource;

        subscription = new CompositeDisposable();
        view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        view.startLoadingIndicator();

        subscription.add(eventDataSource
                .getEvents()
                .subscribeOn(schedulerProvider.computation())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                        this::onNext,
                        this::onError,
                        this::onComplete
                )
        );
    }

    @Override
    public void unsubscribe() {
        subscription.clear();
    }

    @Override
    public void onNext(List<Event> events) {
        caches = events;

        view.setEvents(events);
    }

    @Override
    public void onError(Throwable error) {
        view.stopLoadingIndicator();

        view.error();
    }

    @Override
    public void onComplete() {
        view.stopLoadingIndicator();
    }

    @Override
    public void getAddress(int position) {
        view.showAddress(caches.get(position));
    }
}
