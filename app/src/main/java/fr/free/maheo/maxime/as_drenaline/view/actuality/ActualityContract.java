package fr.free.maheo.maxime.as_drenaline.view.actuality;

import java.util.List;

import fr.free.maheo.maxime.as_drenaline.data.model.Actuality;
import fr.free.maheo.maxime.as_drenaline.view.base.BasePresenter;
import fr.free.maheo.maxime.as_drenaline.view.base.BaseView;
import retrofit2.http.Path;

/**
 * Created by mmaheo on 24/06/2017.
 */

public interface ActualityContract {

    interface View extends BaseView<ActualityContract.Presenter> {

        void error();

        void startLoadingIndicator();

        void stopLoadingIndicator();

        void setActualities(List<Actuality> actualities);

    }

    interface Presenter extends BasePresenter {

        void onNext(final List<Actuality> actualities);

        void onError(final Throwable error);

        void onComplete();
    }

}
