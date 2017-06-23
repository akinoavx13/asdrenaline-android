package fr.free.maheo.maxime.as_drenaline.view;

import android.view.View;

/**
 * Created by mmaheo on 23/06/2017.
 */

public interface RecyclerViewListener {

    @FunctionalInterface
    interface OnItemClickListener {
        void OnItemClick(View view, int position);
    }

}