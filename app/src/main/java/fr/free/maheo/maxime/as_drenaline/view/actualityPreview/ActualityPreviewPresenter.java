package fr.free.maheo.maxime.as_drenaline.view.actualityPreview;

import java.util.List;

import fr.free.maheo.maxime.as_drenaline.data.model.Actuality;
import fr.free.maheo.maxime.as_drenaline.data.source.actuality.ActualityDataSource;
import fr.free.maheo.maxime.as_drenaline.util.scheduler.BaseSchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by mmaheo on 24/06/2017.
 */

public class ActualityPreviewPresenter implements ActualityPreviewContract.Presenter {

    public static final String TAG = ActualityPreviewPresenter.class.getSimpleName();

    private ActualityPreviewContract.View view;

    private CompositeDisposable subscription;

    private BaseSchedulerProvider schedulerProvider;

    private ActualityDataSource actualityDataSource;

    private List<Actuality> caches;

    private String categoryId;

    public ActualityPreviewPresenter(ActualityPreviewContract.View view, BaseSchedulerProvider baseSchedulerProvider, ActualityDataSource actualityDataSource, String categoryId) {
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

        view.setActualities(actualities);
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
    public void getActuality(int position) {
        view.showActuality(caches.get(position));
    }

}
