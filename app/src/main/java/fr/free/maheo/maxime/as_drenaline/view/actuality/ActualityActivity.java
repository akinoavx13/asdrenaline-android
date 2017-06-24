package fr.free.maheo.maxime.as_drenaline.view.actuality;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.free.maheo.maxime.as_drenaline.R;
import fr.free.maheo.maxime.as_drenaline.view.category.CategoryFragment;

public class ActualityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actuality);

        Intent intent = getIntent();
        String categoryName = intent.getStringExtra(CategoryFragment.EXTRA_CATEGORY_NAME);

        setTitle(categoryName);
    }
}
