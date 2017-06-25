package fr.free.maheo.maxime.as_drenaline.view.actuality;

import fr.free.maheo.maxime.as_drenaline.data.model.Actuality;
import fr.free.maheo.maxime.as_drenaline.view.base.BasePresenter;
import fr.free.maheo.maxime.as_drenaline.view.base.BaseView;

/**
 * Created by mmaheo on 25/06/2017.
 */

public interface ActualityContract {

    interface View extends BaseView<ActualityContract.Presenter> {

        void error();

        void startLoadingIndicator();

        void stopLoadingIndicator();

    }

    interface Presenter extends BasePresenter {

        void onNext(final Actuality actuality);

        void onError(final Throwable error);

        void onComplete();

    }

}
