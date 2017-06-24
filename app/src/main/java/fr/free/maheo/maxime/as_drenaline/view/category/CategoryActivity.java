package fr.free.maheo.maxime.as_drenaline.view.category;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.free.maheo.maxime.as_drenaline.R;
import fr.free.maheo.maxime.as_drenaline.data.source.Injection;
import fr.free.maheo.maxime.as_drenaline.util.ActivityUtil;

public class CategoryActivity extends AppCompatActivity {

    private CategoryPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        setTitle(getResources().getString(R.string.app_name));

        CategoryFragment categoryFragment = (CategoryFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_category);

        if (categoryFragment == null) {
            categoryFragment = CategoryFragment.newInstance();

            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), categoryFragment, R.id.fragment_category);
        }

        presenter = new CategoryPresenter(categoryFragment, Injection.provideSchedulerProvider(), Injection.provideCategoryRepository());

        categoryFragment.setPresenter(presenter);
    }
}
