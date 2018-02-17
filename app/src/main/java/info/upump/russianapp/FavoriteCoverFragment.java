package info.upump.russianapp;

import android.os.Bundle;
import android.support.v4.content.Loader;

import java.util.List;

import info.upump.russianapp.loader.LoaderFavoriteCover;
import info.upump.russianapp.model.Cover;

/**
 * Created by explo on 08.02.2018.
 */

public class FavoriteCoverFragment extends AbstractCoverFragment {
    private LoaderFavoriteCover loaderFavoriteCover;
    @Override
    void setTitleAndImg() {
        int imgIdent  = getResources().getIdentifier("favorite", "drawable", getContext().getPackageName());
        iControllerfragment.setTitle(getString(R.string.cover_title_favorite), imgIdent);
    }

    public FavoriteCoverFragment() {
    }

    public static FavoriteCoverFragment newInstance(){
        return new FavoriteCoverFragment();
    }

    @Override
    public Loader<List<Cover>> onCreateLoader(int id, Bundle args) {
      loaderFavoriteCover = new LoaderFavoriteCover(getContext());
        return loaderFavoriteCover;
    }

    @Override
    void notifyFavorite(int positionItem) {
        listCover.remove(positionItem);
        adapterCover.notifyItemRemoved(positionItem);
        getLoaderManager().restartLoader(0, null, this);
           }


}
