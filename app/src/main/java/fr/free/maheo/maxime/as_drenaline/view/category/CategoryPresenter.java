package fr.free.maheo.maxime.as_drenaline.view.category;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by mmaheo on 21/06/2017.
 */

public class CategoryPresenter implements CategoryContract.Presenter {

    private CategoryContract.View view;

    private CompositeDisposable subscription;

    public CategoryPresenter(CategoryContract.View view) {
        this.view = view;

        subscription = new CompositeDisposable();
        view.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        subscription.clear();
    }

}
