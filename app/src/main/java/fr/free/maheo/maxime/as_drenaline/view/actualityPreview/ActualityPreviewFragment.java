package fr.free.maheo.maxime.as_drenaline.view.actualityPreview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fr.free.maheo.maxime.as_drenaline.R;
import fr.free.maheo.maxime.as_drenaline.data.model.Actuality;
import fr.free.maheo.maxime.as_drenaline.util.ui.DividerItemDecoration;
import fr.free.maheo.maxime.as_drenaline.view.actuality.ActualityActivity;

/**
 * Created by mmaheo on 24/06/2017.
 */

public class ActualityPreviewFragment extends Fragment implements ActualityPreviewContract.View {

    public static final String TAG = ActualityPreviewFragment.class.getSimpleName();

    public static final String EXTRA_ACTUALITY = ActualityPreviewFragment.class.getPackage().getName() + ".ACTUALITY";

    private ActualityPreviewContract.Presenter presenter;

    private Unbinder unbinder;

    private ActualityPreviewAdapter adapter;

    @BindView(R.id.actuality_recycler)
    RecyclerView recyclerView;

    @BindView(R.id.actuality_refresh)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.actualities_empty)
    TextView actualitiesEmpty;

    public static ActualityPreviewFragment newInstance() {
        return new ActualityPreviewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_actualities, container, false);

        unbinder = ButterKnife.bind(this, root);

        adapter = new ActualityPreviewAdapter(new ArrayList<>());
        adapter.setOnItemClickListener((view, position) -> presenter.getActuality(position));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        refreshLayout.setOnRefreshListener(() -> presenter.subscribe());

        actualitiesEmpty.setVisibility(View.GONE);

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
    public void setPresenter(ActualityPreviewContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setActualities(List<Actuality> actualities) {
        if (actualities.size() == 0) {
            actualitiesEmpty.setVisibility(View.VISIBLE);
        } else {
            actualitiesEmpty.setVisibility(View.GONE);
            adapter.replaceData(actualities);
        }
    }

    @Override
    public void error() {
        Toast.makeText(getContext(), "Une erreur est survenue, réessayez plur tard", Toast.LENGTH_LONG).show();
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

    @Override
    public void showActuality(Actuality actuality) {
        Intent intent = new Intent(getContext(), ActualityActivity.class);
        intent.putExtra(EXTRA_ACTUALITY, actuality);
        startActivity(intent);
    }
}
