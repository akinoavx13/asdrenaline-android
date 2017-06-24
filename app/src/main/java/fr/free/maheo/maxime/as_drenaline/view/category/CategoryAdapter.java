package fr.free.maheo.maxime.as_drenaline.view.category;

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
import fr.free.maheo.maxime.as_drenaline.data.model.Category;
import fr.free.maheo.maxime.as_drenaline.util.AndroidApplication;
import fr.free.maheo.maxime.as_drenaline.view.base.BaseRecyclerViewAdapter;

/**
 * Created by mmaheo on 21/06/2017.
 */

public class CategoryAdapter extends BaseRecyclerViewAdapter<CategoryAdapter.CategoryViewHolder> {

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.category_title)
        TextView categoryTitle;

        @BindView(R.id.category_background)
        ImageView categoryBackground;

        public CategoryViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    private List<Category> categories;

    public CategoryAdapter(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        CategoryViewHolder holder = (CategoryViewHolder) viewHolder;
        Category category = categories.get(i);
        holder.categoryTitle.setText(category.getName());

        Glide.with(AndroidApplication.getAppContext())
                .load(category.getImageUrl())
                .into(holder.categoryBackground);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void replaceData(List<Category> questions) {
        categories.clear();
        categories.addAll(questions);
        notifyDataSetChanged();
    }
}
