package fr.free.maheo.maxime.as_drenaline.view.actuality;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fr.free.maheo.maxime.as_drenaline.R;
import fr.free.maheo.maxime.as_drenaline.data.model.Actuality;
import fr.free.maheo.maxime.as_drenaline.util.AndroidApplication;

/**
 * Created by mmaheo on 25/06/2017.
 */

public class ActualityFragment extends Fragment implements ActualityContract.View {

    private ActualityContract.Presenter presenter;

    private Unbinder unbinder;

    @BindView(R.id.actuality_title)
    TextView actualityTitle;

    @BindView(R.id.actuality_author)
    TextView actualityAuthor;

    @BindView(R.id.actuality_date)
    TextView actualityDate;

    @BindView(R.id.actuality_content)
    TextView actualityContent;

    @BindView(R.id.actuality_background)
    ImageView actualityBackground;

    @BindView(R.id.actuality_progress_bar)
    ProgressBar actualityProgressBar;

    public static ActualityFragment newInstance() {
        return new ActualityFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_actuality, container, false);

        unbinder = ButterKnife.bind(this, root);

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
    public void setActuality(Actuality actuality) {
        actualityTitle.setText(actuality.getTitle());
        actualityAuthor.setText(actuality.getAuthor());
        actualityDate.setText(actuality.getDate());
        actualityContent.setText(actuality.getContent());

        if(!actuality.getImageUrl().equals("")) {
            actualityBackground.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(AndroidApplication.getAppContext())
                    .load(actuality.getImageUrl())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            actualityBackground.setScaleType(ImageView.ScaleType.FIT_CENTER);
                            actualityBackground.setImageResource(R.drawable.logo);
                            actualityProgressBar.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            actualityBackground.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            actualityProgressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(actualityBackground);
        } else {
            actualityBackground.setScaleType(ImageView.ScaleType.FIT_CENTER);
            actualityBackground.setImageResource(R.drawable.logo);
            actualityProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void error() {
        Toast.makeText(getContext(), "Une erreur est survenue, réessayez plur tard", Toast.LENGTH_LONG).show();
    }
}
