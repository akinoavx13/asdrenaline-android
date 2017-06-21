package fr.free.maheo.maxime.as_drenaline.data.source.category;

import fr.free.maheo.maxime.as_drenaline.data.network.NetworkProvider;
import fr.free.maheo.maxime.as_drenaline.data.source.category.CategoryDataSource;
import fr.free.maheo.maxime.as_drenaline.data.source.category.remote.CategoryRemoteDataSource;
import fr.free.maheo.maxime.as_drenaline.util.scheduler.BaseSchedulerProvider;
import fr.free.maheo.maxime.as_drenaline.util.scheduler.SchedulerProvider;

/**
 * Created by mmaheo on 21/06/2017.
 */

public class Injection {

    public static BaseSchedulerProvider provideSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }

    public static CategoryDataSource provideNetworkManager() {
        return NetworkProvider.getInstance().getManager();
    }

    public static CategoryDataSource provideCategoryRepository() {
        return CategoryRepository.getInstance(CategoryRemoteDataSource.getInstance());
    }

}
