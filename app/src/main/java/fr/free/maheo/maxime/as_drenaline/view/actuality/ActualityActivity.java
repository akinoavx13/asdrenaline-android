package fr.free.maheo.maxime.as_drenaline.view.actuality;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.free.maheo.maxime.as_drenaline.R;
import fr.free.maheo.maxime.as_drenaline.data.source.Injection;
import fr.free.maheo.maxime.as_drenaline.util.ActivityUtil;
import fr.free.maheo.maxime.as_drenaline.view.category.CategoryFragment;

public class ActualityActivity extends AppCompatActivity {

    private ActualityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actuality);

        Intent intent = getIntent();
        String categoryName = intent.getStringExtra(CategoryFragment.EXTRA_CATEGORY_NAME);
        String categoryId = intent.getStringExtra(CategoryFragment.EXTRA_CATEGORY_ID);

        setTitle(categoryName);

        ActualityFragment actualityFragment = (ActualityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_actuality);

        if(actualityFragment == null) {
            actualityFragment = ActualityFragment.newInstance();

            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), actualityFragment, R.id.fragment_actuality);
        }

        presenter = new ActualityPresenter(actualityFragment, Injection.provideSchedulerProvider(), Injection.provideActualityRepository(), categoryId);

        actualityFragment.setPresenter(presenter);
    }
}
