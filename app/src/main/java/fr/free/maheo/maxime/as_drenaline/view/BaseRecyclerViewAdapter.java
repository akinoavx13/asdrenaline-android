package fr.free.maheo.maxime.as_drenaline.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

/**
 * Created by mmaheo on 23/06/2017.
 */

public abstract class BaseRecyclerViewAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RecyclerViewListener.OnItemClickListener itemClickListener;

    public void setOnItemClickListener(@NonNull RecyclerViewListener.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    /**
     * Override onBindViewHolder to support OnItemClick listener.
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (itemClickListener != null) {
            viewHolder.itemView.setOnClickListener(view -> itemClickListener.OnItemClick(view, i));
        }
    }

}
