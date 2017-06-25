package fr.free.maheo.maxime.as_drenaline.view.actualityPreview;

import java.util.List;

import fr.free.maheo.maxime.as_drenaline.data.model.Actuality;
import fr.free.maheo.maxime.as_drenaline.view.base.BasePresenter;
import fr.free.maheo.maxime.as_drenaline.view.base.BaseView;

/**
 * Created by mmaheo on 24/06/2017.
 */

public interface ActualityPreviewContract {

    interface View extends BaseView<ActualityPreviewContract.Presenter> {

        void error();

        void startLoadingIndicator();

        void stopLoadingIndicator();

        void setActualities(List<Actuality> actualities);

        void showActuality(Actuality actuality);
    }

    interface Presenter extends BasePresenter {

        void onNext(final List<Actuality> actualities);

        void onError(final Throwable error);

        void onComplete();

        void getActuality(int position);
    }

}
