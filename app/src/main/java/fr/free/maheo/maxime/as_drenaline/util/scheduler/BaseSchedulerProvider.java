package fr.free.maheo.maxime.as_drenaline.util.scheduler;

import io.reactivex.Scheduler;

/**
 * Created by mmaheo on 21/06/2017.
 */

public interface BaseSchedulerProvider {

    Scheduler computation();

    Scheduler io();

    Scheduler ui();

}
