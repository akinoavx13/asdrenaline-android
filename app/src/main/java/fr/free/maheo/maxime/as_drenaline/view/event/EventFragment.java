package fr.free.maheo.maxime.as_drenaline.view.event;

import android.content.Intent;
import android.net.Uri;
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
import fr.free.maheo.maxime.as_drenaline.data.model.Event;

/**
 * Created by mmaheo on 26/06/2017.
 */

public class EventFragment extends Fragment implements EventContract.View {

    private EventContract.Presenter presenter;

    private Unbinder unbinder;

    private EventAdapter adapter;

    @BindView(R.id.event_recycler)
    RecyclerView recyclerView;

    @BindView(R.id.event_refresh)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.event_empty)
    TextView eventEmpty;

    public static EventFragment newInstance() {
        return new EventFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_event, container, false);

        unbinder = ButterKnife.bind(this, root);

        adapter = new EventAdapter(new ArrayList<>());
        adapter.setOnItemClickListener((view, position) -> presenter.getAddress(position));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        refreshLayout.setOnRefreshListener(() -> presenter.subscribe());

        eventEmpty.setVisibility(View.GONE);

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
    public void setPresenter(EventContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setEvents(List<Event> events) {
        if (events.size() == 0) {
            eventEmpty.setVisibility(View.VISIBLE);
        } else {
            eventEmpty.setVisibility(View.GONE);
            adapter.replaceData(events);
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
    public void showAddress(Event event) {
        String map = "http://maps.google.co.in/maps?q=" + event.getLocation();

        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
        startActivity(i);
    }

}
