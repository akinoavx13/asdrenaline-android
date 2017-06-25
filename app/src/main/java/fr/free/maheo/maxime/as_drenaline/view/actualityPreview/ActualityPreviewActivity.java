package fr.free.maheo.maxime.as_drenaline.view.actualityPreview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.free.maheo.maxime.as_drenaline.R;
import fr.free.maheo.maxime.as_drenaline.data.source.Injection;
import fr.free.maheo.maxime.as_drenaline.util.ActivityUtil;
import fr.free.maheo.maxime.as_drenaline.view.category.CategoryFragment;

public class ActualityPreviewActivity extends AppCompatActivity {

    private ActualityPreviewPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualities);

        Intent intent = getIntent();
        String categoryName = intent.getStringExtra(CategoryFragment.EXTRA_CATEGORY_NAME);
        String categoryId = intent.getStringExtra(CategoryFragment.EXTRA_CATEGORY_ID);

        setTitle(categoryName);

        ActualityPreviewFragment actualityPreviewFragment = (ActualityPreviewFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_actuality);

        if(actualityPreviewFragment == null) {
            actualityPreviewFragment = ActualityPreviewFragment.newInstance();

            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), actualityPreviewFragment, R.id.fragment_actuality);
        }

        presenter = new ActualityPreviewPresenter(actualityPreviewFragment, Injection.provideSchedulerProvider(), Injection.provideActualityRepository(), categoryId);

        actualityPreviewFragment.setPresenter(presenter);
    }
}
