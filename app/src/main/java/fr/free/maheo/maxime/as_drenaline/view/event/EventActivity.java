package fr.free.maheo.maxime.as_drenaline.view.event;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.free.maheo.maxime.as_drenaline.R;
import fr.free.maheo.maxime.as_drenaline.data.source.Injection;
import fr.free.maheo.maxime.as_drenaline.util.ActivityUtil;
import fr.free.maheo.maxime.as_drenaline.view.actualityPreview.ActualityPreviewActivity;
import fr.free.maheo.maxime.as_drenaline.view.category.CategoryActivity;

public class EventActivity extends AppCompatActivity {

    private EventPresenter presenter;

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_actualities:
                Intent intent = new Intent(this, CategoryActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;
            case R.id.navigation_events:
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_events);

        setTitle("Événements");

        EventFragment eventFragment = (EventFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_event);

        if(eventFragment == null) {
            eventFragment = EventFragment.newInstance();

            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), eventFragment, R.id.fragment_event);
        }

        presenter = new EventPresenter(eventFragment, Injection.provideSchedulerProvider(), Injection.provideEventRepository());

        eventFragment.setPresenter(presenter);
    }
}
