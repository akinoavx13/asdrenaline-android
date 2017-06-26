package fr.free.maheo.maxime.as_drenaline.view.category;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import fr.free.maheo.maxime.as_drenaline.R;
import fr.free.maheo.maxime.as_drenaline.data.source.Injection;
import fr.free.maheo.maxime.as_drenaline.util.ActivityUtil;
import fr.free.maheo.maxime.as_drenaline.view.actualityPreview.ActualityPreviewActivity;
import fr.free.maheo.maxime.as_drenaline.view.event.EventActivity;

public class CategoryActivity extends AppCompatActivity {

    private CategoryPresenter presenter;

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_actualities:
                return true;
            case R.id.navigation_events:
                Intent intent = new Intent(this, EventActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_actualities);

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
