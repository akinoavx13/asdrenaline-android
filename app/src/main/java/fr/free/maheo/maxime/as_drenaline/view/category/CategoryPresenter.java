package fr.free.maheo.maxime.as_drenaline.view.category;

import android.util.Log;

import java.util.List;

import fr.free.maheo.maxime.as_drenaline.data.model.Category;
import fr.free.maheo.maxime.as_drenaline.data.source.category.CategoryDataSource;
import fr.free.maheo.maxime.as_drenaline.util.scheduler.BaseSchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by mmaheo on 21/06/2017.
 */

public class CategoryPresenter implements CategoryContract.Presenter {

    private CategoryContract.View view;

    private CompositeDisposable subscription;

    private BaseSchedulerProvider schedulerProvider;

    private CategoryDataSource categoryDataSource;

    private List<Category> caches;

    public CategoryPresenter(CategoryContract.View view, BaseSchedulerProvider baseSchedulerProvider, CategoryDataSource categoryDataSource) {
        this.view = view;
        this.schedulerProvider = baseSchedulerProvider;
        this.categoryDataSource = categoryDataSource;

        subscription = new CompositeDisposable();
        view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        view.startLoadingIndicator();

        subscription.add(categoryDataSource
        .getCategories()
        .subscribeOn(schedulerProvider.computation())
        .observeOn(schedulerProvider.ui())
        .subscribe(
                this::onNext,
                this::onError,
                this::onComplete
        ));
    }

    @Override
    public void unsubscribe() {
        subscription.clear();
    }

    @Override
    public void onNext(List<Category> categories) {
        caches = categories;

        view.setCategories(categories);
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
    public void getCategory(int position) {
        view.showActualities(caches.get(position));
    }
}
