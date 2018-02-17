package info.upump.russianapp.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import info.upump.russianapp.db.IData;
import info.upump.russianapp.db.TaleDao;
import info.upump.russianapp.model.Cover;
import info.upump.russianapp.model.Tale;

/**
 * Created by explo on 03.02.2018.
 */

public class LoaderTale extends AsyncTaskLoader<Tale> {
    private Cover cover;

    public LoaderTale(Context context, Cover cover) {
        super(context);
        this.cover = cover;
    }

    @Override
    public Tale loadInBackground() {
        IData taleDao = new TaleDao(getContext());
        Tale tale = (Tale) taleDao.getByParent(cover);
        return tale;
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
    public void deliverResult(Tale data) {
        super.deliverResult(data);
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
    }
}
