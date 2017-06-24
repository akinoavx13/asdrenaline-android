package fr.free.maheo.maxime.as_drenaline.view.actuality;

import android.util.Log;

import java.util.List;

import fr.free.maheo.maxime.as_drenaline.data.model.Actuality;
import fr.free.maheo.maxime.as_drenaline.data.source.actuality.ActualityDataSource;
import fr.free.maheo.maxime.as_drenaline.util.scheduler.BaseSchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.http.Path;

/**
 * Created by mmaheo on 24/06/2017.
 */

public class ActualityPresenter implements ActualityContract.Presenter {

    public static final String TAG = ActualityPresenter.class.getSimpleName();

    private ActualityContract.View view;

    private CompositeDisposable subscription;

    private BaseSchedulerProvider schedulerProvider;

    private ActualityDataSource actualityDataSource;

    private List<Actuality> caches;

    private String categoryId;

    public ActualityPresenter(ActualityContract.View view, BaseSchedulerProvider baseSchedulerProvider, ActualityDataSource actualityDataSource, String categoryId) {
        this.view = view;
        this.schedulerProvider = baseSchedulerProvider;
        this.actualityDataSource = actualityDataSource;
        this.categoryId = categoryId;

        subscription = new CompositeDisposable();
        view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        view.startLoadingIndicator();

        subscription.add(actualityDataSource
                .getActualities(categoryId)
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
    public void onNext(List<Actuality> actualities) {
        caches = actualities;

        Log.d(TAG, "actualities : " + caches.size());
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
}
