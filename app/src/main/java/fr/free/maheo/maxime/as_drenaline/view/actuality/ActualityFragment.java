package fr.free.maheo.maxime.as_drenaline.view.actuality;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import fr.free.maheo.maxime.as_drenaline.R;

/**
 * Created by mmaheo on 24/06/2017.
 */

public class ActualityFragment extends Fragment implements ActualityContract.View {

    private ActualityContract.Presenter presenter;

    private Unbinder unbinder;

    public static ActualityFragment newInstance() {
        return new ActualityFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_actuality, container, false);

        unbinder = ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unsubscribe();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setPresenter(ActualityContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void error() {

    }

    @Override
    public void startLoadingIndicator() {

    }

    @Override
    public void stopLoadingIndicator() {

    }
}
