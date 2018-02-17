package info.upump.russianapp.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

import info.upump.russianapp.db.CoverDao;
import info.upump.russianapp.db.IData;
import info.upump.russianapp.model.Cover;

/**
 * Created by explo on 30.01.2018.
 */

public class LoaderCover extends AsyncTaskLoader<List<Cover>> {

    public LoaderCover(Context context) {
        super(context);
    }

    @Override
    public List<Cover> loadInBackground() {
       IData<Cover> coverDao = new CoverDao(getContext());
        return coverDao.getAll();
    }
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();

    }

    @Override
    public void deliverResult(List<Cover> data) {
        super.deliverResult(data);
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
    }
}
