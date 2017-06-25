package fr.free.maheo.maxime.as_drenaline.view.actualityPreview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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

        @BindView(R.id.actuality_title)
        TextView actualityTitle;

        @BindView(R.id.actuality_content_preview)
        TextView actualityContentPreview;

        @BindView(R.id.actuality_background)
        ImageView actualityBackground;

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

        holder.actualityTitle.setText(actuality.getTitle());

        String contentPreview = actuality.getContent().replace('\n', ' ').substring(0, actuality.getContent().length() > 150 ? 150 : actuality.getContent().length()) + " ...";
        holder.actualityContentPreview.setText(contentPreview);

        if(!actuality.getImageUrl().equals("")) {
            holder.actualityBackground.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(AndroidApplication.getAppContext())
                    .load(actuality.getImageUrl())
                    .into(holder.actualityBackground);
        } else {
            holder.actualityBackground.setScaleType(ImageView.ScaleType.FIT_CENTER);
            holder.actualityBackground.setImageResource(R.drawable.logo);
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
