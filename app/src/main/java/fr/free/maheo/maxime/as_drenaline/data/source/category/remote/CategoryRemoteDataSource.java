package fr.free.maheo.maxime.as_drenaline.data.source.category.remote;

import java.util.List;

import fr.free.maheo.maxime.as_drenaline.data.model.Category;
import fr.free.maheo.maxime.as_drenaline.data.source.category.Injection;
import fr.free.maheo.maxime.as_drenaline.data.source.category.CategoryDataSource;
import io.reactivex.Observable;

/**
 * Created by mmaheo on 21/06/2017.
 */

public class CategoryRemoteDataSource implements CategoryDataSource {

    private static CategoryRemoteDataSource INSTANCE = null;

    private CategoryDataSource networkProvider;

    public CategoryRemoteDataSource(CategoryDataSource networkProvider) {
        this.networkProvider = networkProvider;
    }

    public static CategoryRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CategoryRemoteDataSource(Injection.provideNetworkManager());
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<Category>> getCategories() {
        return this.networkProvider.getCategories();
    }
}
