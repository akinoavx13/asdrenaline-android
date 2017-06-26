package fr.free.maheo.maxime.as_drenaline.view.event;

import java.util.List;

import fr.free.maheo.maxime.as_drenaline.data.model.Actuality;
import fr.free.maheo.maxime.as_drenaline.data.model.Event;
import fr.free.maheo.maxime.as_drenaline.view.base.BasePresenter;
import fr.free.maheo.maxime.as_drenaline.view.base.BaseView;

/**
 * Created by mmaheo on 26/06/2017.
 */

public interface EventContract {

    interface View extends BaseView<EventContract.Presenter> {

        void error();

        void startLoadingIndicator();

        void stopLoadingIndicator();

        void setEvents(List<Event> events);

        void showAddress(Event event);

    }

    interface Presenter extends BasePresenter {

        void onNext(final List<Event> events);

        void onError(final Throwable error);

        void onComplete();

        void getAddress(int position);

    }

}
