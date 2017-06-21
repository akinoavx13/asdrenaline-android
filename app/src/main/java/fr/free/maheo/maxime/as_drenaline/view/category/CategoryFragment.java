package fr.free.maheo.maxime.as_drenaline.view.category;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import fr.free.maheo.maxime.as_drenaline.R;

/**
 * Created by mmaheo on 21/06/2017.
 */

public class CategoryFragment extends Fragment implements CategoryContract.View {

    private CategoryContract.Presenter presenter;

    private Unbinder unbinder;

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_category, container, false);

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
    public void setPresenter(CategoryContract.Presenter presenter) {
        this.presenter = presenter;
    }

}
