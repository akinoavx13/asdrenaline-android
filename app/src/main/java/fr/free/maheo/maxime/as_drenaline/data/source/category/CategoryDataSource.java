package fr.free.maheo.maxime.as_drenaline.data.source.category;

import java.util.List;

import fr.free.maheo.maxime.as_drenaline.data.model.Category;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by mmaheo on 21/06/2017.
 */

public interface CategoryDataSource {

    @GET("categories")
    Observable<List<Category>> getCategories();

}
