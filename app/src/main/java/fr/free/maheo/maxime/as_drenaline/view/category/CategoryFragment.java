package fr.free.maheo.maxime.as_drenaline.view.category;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fr.free.maheo.maxime.as_drenaline.R;

/**
 * Created by mmaheo on 21/06/2017.
 */

public class CategoryFragment extends Fragment implements CategoryContract.View {

    private CategoryContract.Presenter presenter;

    private Unbinder unbinder;

    private CategoryAdapter adapter;

    @BindView(R.id.category_recycler)
    RecyclerView categoryRecyclerView;

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_category, container, false);

        unbinder = ButterKnife.bind(this, root);

        adapter = new CategoryAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryRecyclerView.setAdapter(adapter);
        categoryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //adapter.setOnItemClickListener(
        //        (view, position) -> presenter.getQuestion(adapter.getItem(position).getId()));

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
