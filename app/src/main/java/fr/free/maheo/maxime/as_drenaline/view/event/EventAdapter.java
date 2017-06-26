package fr.free.maheo.maxime.as_drenaline.view.event;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.free.maheo.maxime.as_drenaline.R;
import fr.free.maheo.maxime.as_drenaline.data.model.Event;
import fr.free.maheo.maxime.as_drenaline.view.base.BaseRecyclerViewAdapter;

/**
 * Created by mmaheo on 26/06/2017.
 */

public class EventAdapter extends BaseRecyclerViewAdapter<EventAdapter.EventViewHolder> {

    class EventViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.event_title)
        TextView title;

        @BindView(R.id.event_location)
        TextView location;

        @BindView(R.id.event_date)
        TextView date;

        public EventViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    private List<Event> events;

    public EventAdapter(List<Event> events) {
        this.events = events;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        EventViewHolder holder = (EventViewHolder) viewHolder;
        Event event = events.get(i);

        holder.title.setText(event.getTitle());
        holder.location.setText(event.getLocation());
        holder.date.setText(event.getDate());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public void replaceData(List<Event> events) {
        this.events.clear();
        this.events.addAll(events);
        notifyDataSetChanged();
    }

}
