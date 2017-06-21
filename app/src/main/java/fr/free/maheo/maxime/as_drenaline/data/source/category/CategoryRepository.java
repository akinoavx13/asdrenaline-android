package fr.free.maheo.maxime.as_drenaline.data.source.category;

import java.util.List;

import fr.free.maheo.maxime.as_drenaline.data.model.Category;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created by mmaheo on 21/06/2017.
 */

public class CategoryRepository implements CategoryDataSource {

    private static CategoryRepository INSTANCE = null;

    private final CategoryDataSource remoteDataSource;

    public CategoryRepository(final CategoryDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public static CategoryRepository getInstance(final CategoryDataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new CategoryRepository(localDataSource);
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<Category>> getCategories() {
        return getRemoteCategories();
    }

    private Observable<List<Category>> getRemoteCategories() {
        return remoteDataSource.getCategories();
    }
}
