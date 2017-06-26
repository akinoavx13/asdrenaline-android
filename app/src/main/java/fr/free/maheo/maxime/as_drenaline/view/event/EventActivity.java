package fr.free.maheo.maxime.as_drenaline.view.event;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.free.maheo.maxime.as_drenaline.R;
import fr.free.maheo.maxime.as_drenaline.data.source.Injection;
import fr.free.maheo.maxime.as_drenaline.util.ActivityUtil;

public class EventActivity extends AppCompatActivity {

    private EventPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

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
