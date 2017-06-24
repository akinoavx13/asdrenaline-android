package fr.free.maheo.maxime.as_drenaline.data.source;

import fr.free.maheo.maxime.as_drenaline.data.network.NetworkProvider;
import fr.free.maheo.maxime.as_drenaline.data.source.actuality.ActualityDataSource;
import fr.free.maheo.maxime.as_drenaline.data.source.actuality.ActualityRepository;
import fr.free.maheo.maxime.as_drenaline.data.source.actuality.remote.ActualityRemoteDataSource;
import fr.free.maheo.maxime.as_drenaline.data.source.category.CategoryDataSource;
import fr.free.maheo.maxime.as_drenaline.data.source.category.CategoryRepository;
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

    public static CategoryDataSource provideCategoryNetworkManager() {
        return NetworkProvider.getInstance().getCategoryManager();
    }

    public static CategoryDataSource provideCategoryRepository() {
        return CategoryRepository.getInstance(CategoryRemoteDataSource.getInstance());
    }

    public static ActualityDataSource provideActualityNetworkManager() {
        return NetworkProvider.getInstance().getActualityManager();
    }

    public static ActualityDataSource provideActualityRepository() {
        return ActualityRepository.getInstance(ActualityRemoteDataSource.getInstance());
    }
}
