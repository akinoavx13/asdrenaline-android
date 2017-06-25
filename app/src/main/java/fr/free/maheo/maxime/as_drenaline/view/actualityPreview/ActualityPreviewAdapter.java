package fr.free.maheo.maxime.as_drenaline.view.actualityPreview;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.free.maheo.maxime.as_drenaline.R;
import fr.free.maheo.maxime.as_drenaline.data.model.Actuality;
import fr.free.maheo.maxime.as_drenaline.util.AndroidApplication;
import fr.free.maheo.maxime.as_drenaline.view.base.BaseRecyclerViewAdapter;

/**
 * Created by mmaheo on 21/06/2017.
 */

public class ActualityPreviewAdapter extends BaseRecyclerViewAdapter<ActualityPreviewAdapter.ActualityAViewHolder> {

    class ActualityAViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.actuality_preview_title)
        TextView actualityPreviewTitle;

        @BindView(R.id.actuality_preview_content_preview)
        TextView actualityPreviewContentPreview;

        @BindView(R.id.actuality_preview_background)
        ImageView actualityPreviewBackground;

        @BindView(R.id.actuality_preview_progress_bar)
        ProgressBar actualityPreviewProgressBar;

        public ActualityAViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    private List<Actuality> actualities;

    public ActualityPreviewAdapter(List<Actuality> actualities) {
        this.actualities = actualities;
    }

    @Override
    public ActualityAViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_actuality, parent, false);
        return new ActualityAViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        ActualityAViewHolder holder = (ActualityAViewHolder) viewHolder;
        Actuality actuality = actualities.get(i);

        holder.actualityPreviewTitle.setText(actuality.getTitle());

        String contentPreview = actuality.getContent().replace('\n', ' ').substring(0, actuality.getContent().length() > 150 ? 150 : actuality.getContent().length()) + " ...";
        holder.actualityPreviewContentPreview.setText(contentPreview);

        if(!actuality.getImageUrl().equals("")) {
            holder.actualityPreviewBackground.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(AndroidApplication.getAppContext())
                    .load(actuality.getImageUrl())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            holder.actualityPreviewBackground.setScaleType(ImageView.ScaleType.FIT_CENTER);
                            holder.actualityPreviewBackground.setImageResource(R.drawable.logo);
                            holder.actualityPreviewProgressBar.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            holder.actualityPreviewBackground.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            holder.actualityPreviewProgressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(holder.actualityPreviewBackground);
        } else {
            holder.actualityPreviewBackground.setScaleType(ImageView.ScaleType.FIT_CENTER);
            holder.actualityPreviewBackground.setImageResource(R.drawable.logo);
            holder.actualityPreviewProgressBar.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return actualities.size();
    }

    public void replaceData(List<Actuality> actualities) {
        this.actualities.clear();
        this.actualities.addAll(actualities);
        notifyDataSetChanged();
    }
}
