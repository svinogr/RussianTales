package info.upump.russianapp;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import info.upump.russianapp.adapter.AdapterCover;
import info.upump.russianapp.callback.SwipeCallback;
import info.upump.russianapp.db.CoverDao;
import info.upump.russianapp.model.Cover;

/**
 * Created by explo on 08.02.2018.
 */

public abstract class AbstractCoverFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Cover>>, ISwipeController {
    protected List<Cover> listCover = new ArrayList<>();
    protected AdapterCover adapterCover;
    protected IControllerFragment iControllerfragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_cover, container, false);
        RecyclerView recyclerView = inflate.findViewById(R.id.cover_fragment_recycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        adapterCover = new AdapterCover(listCover);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterCover);

        ItemTouchHelper.Callback itemTouchHelperCallback = new SwipeCallback(this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        FloatingActionButton floatingActionButton = getActivity().findViewById(R.id.fab);
        floatingActionButton.setVisibility(View.INVISIBLE);
        setTitleAndImg();

        AdView mNativeAd = inflate.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                               .build();

        mNativeAd.loadAd(adRequest);
        return inflate;
    }

    abstract void setTitleAndImg();

    @Override
    abstract public Loader<List<Cover>> onCreateLoader(int id, Bundle args);

    @Override
    public void onLoadFinished(Loader<List<Cover>> loader, List<Cover> data) {
        listCover.clear();
        listCover.addAll(data);
        adapterCover.notifyDataSetChanged();

    }

    @Override
    public void onLoaderReset(Loader<List<Cover>> loader) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getLoaderManager().initLoader(0, null, this);
        iControllerfragment = (IControllerFragment) context;
    }


    @Override
    public void favorite(int positionItem) {
        Cover cover = listCover.get(positionItem);
        cover.setFavorite(!cover.isFavorite());
        CoverDao coverDao = new CoverDao(getContext());
        String text = null;
        if (!coverDao.update(cover)) {
            cover.setFavorite(!cover.isFavorite());

        } else {
            if (cover.isFavorite()) {
                text = getString(R.string.toast_favorite_add);
            } else text = getString(R.string.toast_favorite_remove);
        }
        notifyFavorite(positionItem);
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();

    }

    abstract void notifyFavorite(int positionItem);

    @Override
    public void read(int positionItem) {
        Cover cover = listCover.get(positionItem);
        cover.setRead(!cover.isRead());
        CoverDao coverDao = new CoverDao(getContext());
        String text = null;
        if (!coverDao.update(cover)) {
            cover.setRead(!cover.isRead());
        } else {
            if (cover.isRead()) {
                text = getString(R.string.toast_read_add);
            } else text = getString(R.string.toast_read_remove);
        }
        adapterCover.notifyItemChanged(positionItem);
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();

    }
}
