package fr.free.maheo.maxime.as_drenaline.util.ui;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by mmaheo on 23/06/2017.
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public DividerItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = space;
    }
}
