package info.upump.russianapp;


import android.os.Bundle;
import android.support.v4.content.Loader;

import java.util.List;

import info.upump.russianapp.loader.LoaderCover;
import info.upump.russianapp.model.Cover;

public class CoverFragment extends AbstractCoverFragment {


    public CoverFragment() {
        // Required empty public constructor
    }

    public static CoverFragment newInstance() {
        CoverFragment fragment = new CoverFragment();
        return fragment;
    }

    @Override
    void setTitleAndImg() {
        System.out.println("history");
        int imgIdent  = getResources().getIdentifier("history", "drawable", getContext().getPackageName());
        iControllerfragment.setTitle(getString(R.string.cover_title_history), imgIdent);
    }

    @Override
    public Loader<List<Cover>> onCreateLoader(int id, Bundle args) {
        return new LoaderCover(getContext());
    }

    @Override
    void notifyFavorite(int positionItem) {
        adapterCover.notifyItemChanged(positionItem);
    }


}
