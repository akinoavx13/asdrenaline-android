package fr.free.maheo.maxime.as_drenaline.view.actuality;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import fr.free.maheo.maxime.as_drenaline.data.model.Actuality;
import fr.free.maheo.maxime.as_drenaline.util.ui.DividerItemDecoration;

/**
 * Created by mmaheo on 24/06/2017.
 */

public class ActualityFragment extends Fragment implements ActualityContract.View {

    public static final String TAG = ActualityFragment.class.getSimpleName();

    private ActualityContract.Presenter presenter;

    private Unbinder unbinder;

    private ActualityAdapter adapter;

    @BindView(R.id.actuality_recycler)
    RecyclerView actualityRecyclerView;

    @BindView(R.id.actuality_refresh)
    SwipeRefreshLayout refreshLayout;

    public static ActualityFragment newInstance() {
        return new ActualityFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_actuality, container, false);

        unbinder = ButterKnife.bind(this, root);

        adapter = new ActualityAdapter(new ArrayList<>());
        adapter.setOnItemClickListener((view, position) -> Log.d(TAG, "click on item position : " + position));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        actualityRecyclerView.setLayoutManager(layoutManager);
        actualityRecyclerView.setAdapter(adapter);
        actualityRecyclerView.setItemAnimator(new DefaultItemAnimator());

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(8);
        actualityRecyclerView.addItemDecoration(dividerItemDecoration);

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
    public void setPresenter(ActualityContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setActualities(List<Actuality> actualities) {
        adapter.replaceData(actualities);
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
