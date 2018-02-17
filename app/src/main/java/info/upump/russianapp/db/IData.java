package info.upump.russianapp.db;

import java.util.List;

import info.upump.russianapp.model.Cover;

/**
 * Created by explo on 30.01.2018.
 */

public interface IData<T> {
    List<T> getAll();
    List<T> getByParent(Cover cover);
    T save(T obkect);
    List<T> getAllFavorite();
}
