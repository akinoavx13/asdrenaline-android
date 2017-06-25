package fr.free.maheo.maxime.as_drenaline.view.actuality;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import fr.free.maheo.maxime.as_drenaline.R;
import fr.free.maheo.maxime.as_drenaline.data.model.Actuality;
import fr.free.maheo.maxime.as_drenaline.data.source.Injection;
import fr.free.maheo.maxime.as_drenaline.util.ActivityUtil;
import fr.free.maheo.maxime.as_drenaline.view.actualityPreview.ActualityPreviewFragment;
import fr.free.maheo.maxime.as_drenaline.view.category.CategoryFragment;

public class ActualityActivity extends AppCompatActivity {

    private ActualityPrensenter prensenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actuality);

        Intent intent = getIntent();
        Actuality actuality = (Actuality) intent.getSerializableExtra(ActualityPreviewFragment.EXTRA_ACTUALITY);

        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        ActualityFragment actualityFragment = (ActualityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_actuality);

        if(actualityFragment == null) {
            actualityFragment = ActualityFragment.newInstance();

            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), actualityFragment, R.id.fragment_actuality);
        }

        prensenter = new ActualityPrensenter(actualityFragment, actuality);

        actualityFragment.setPresenter(prensenter);
    }
}
