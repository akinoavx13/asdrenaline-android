package fr.free.maheo.maxime.as_drenaline.view.category;

import java.util.List;

import fr.free.maheo.maxime.as_drenaline.data.model.Category;
import fr.free.maheo.maxime.as_drenaline.view.BasePresenter;
import fr.free.maheo.maxime.as_drenaline.view.BaseView;

/**
 * Created by mmaheo on 21/06/2017.
 */

public interface CategoryContract {

    interface View extends BaseView<Presenter> {

        void setCategories(final List<Category> categories);

        void error();

        void startLoadingIndicator();

        void stopLoadingIndicator();
    }

    interface Presenter extends BasePresenter {

        void onNext(final List<Category> categories);

        void onError(final Throwable error);

    }

}
