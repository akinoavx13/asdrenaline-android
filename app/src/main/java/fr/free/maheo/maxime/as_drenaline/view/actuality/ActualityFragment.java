package fr.free.maheo.maxime.as_drenaline.view.actuality;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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
                    .into(actualityBackground);
        } else {
            actualityBackground.setScaleType(ImageView.ScaleType.FIT_CENTER);
            actualityBackground.setImageResource(R.drawable.logo);
        }
    }

    @Override
    public void error() {

    }
}
