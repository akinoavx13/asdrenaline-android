package fr.free.maheo.maxime.as_drenaline.view.actuality;

import fr.free.maheo.maxime.as_drenaline.data.model.Actuality;

/**
 * Created by mmaheo on 25/06/2017.
 */

public class ActualityPrensenter implements ActualityContract.Presenter {

    private ActualityContract.View view;

    private Actuality actuality;

    public ActualityPrensenter(ActualityContract.View view, Actuality actuality) {
        this.view = view;
        this.actuality = actuality;

        view.setPresenter(this);
    }

    @Override
    public void subscribe() {
        if(actuality != null) {
            onNext(actuality);
        } else {
            onError(new Throwable("Can't find actuality"));
        }

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void onNext(Actuality actuality) {
        view.setActuality(actuality);
    }

    @Override
    public void onError(Throwable error) {
        view.error();
    }
}
