package fr.free.maheo.maxime.as_drenaline.view.category;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fr.free.maheo.maxime.as_drenaline.R;
import fr.free.maheo.maxime.as_drenaline.data.model.Category;
import fr.free.maheo.maxime.as_drenaline.util.ui.DividerItemDecoration;

/**
 * Created by mmaheo on 21/06/2017.
 */

public class CategoryFragment extends Fragment implements CategoryContract.View {

    private final String TAG = this.getClass().getSimpleName();

    private CategoryContract.Presenter presenter;

    private Unbinder unbinder;

    private CategoryAdapter adapter;

    @BindView(R.id.category_recycler)
    RecyclerView categoryRecyclerView;

    @BindView(R.id.category_refresh)
    SwipeRefreshLayout refreshLayout;

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_category, container, false);

        unbinder = ButterKnife.bind(this, root);

        adapter = new CategoryAdapter(new ArrayList<>());
        adapter.setOnItemClickListener((view, position) -> Log.d(TAG, "Click on item position : " + position));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryRecyclerView.setAdapter(adapter);
        categoryRecyclerView.setItemAnimator(new DefaultItemAnimator());

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(8);
        categoryRecyclerView.addItemDecoration(dividerItemDecoration);

        refreshLayout.setOnRefreshListener(() -> presenter.subscribe());

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

    @Override
    public void setCategories(List<Category> categories) {
        adapter.replaceData(categories);
    }

    @Override
    public void error() {

    }

    @Override
    public void startLoadingIndicator() {
        if (!refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void stopLoadingIndicator() {
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }

}
