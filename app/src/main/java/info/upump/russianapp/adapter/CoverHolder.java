package info.upump.russianapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import info.upump.russianapp.IControllerFragment;
import info.upump.russianapp.R;
import info.upump.russianapp.TaleFragment;
import info.upump.russianapp.model.Cover;

/**
 * Created by explo on 30.01.2018.
 */

class CoverHolder extends RecyclerView.ViewHolder{
    private TextView title;
    private ImageView favoriteImg;
    private ImageView readImg;
    private View itemView;
    private Cover cover;


    public CoverHolder(final View itemView) {
        super(itemView);
        this.itemView = itemView;
        title = itemView.findViewById(R.id.cover_item_layout_title);
        favoriteImg = itemView.findViewById(R.id.cover_item_layout_image_favorite);
        readImg = itemView.findViewById(R.id.cover_item_layout_image_read);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IControllerFragment iControllerfragment = (IControllerFragment) itemView.getContext();
                TaleFragment taleFragment  = TaleFragment.newInstance(cover);
                iControllerfragment.createFragment(taleFragment);
            }
        });
    }

    public void bind(Cover cover){
        this.cover = cover;

        title.setText(cover.getTitle());

        if(cover.isFavorite()){
            System.out.println(cover.isFavorite());
            favoriteImg.setImageDrawable(itemView.getContext().getResources().getDrawable(R.drawable.ic_favorite));
        } else favoriteImg.setImageDrawable(null);

        if(cover.isRead()){
            readImg.setImageDrawable(itemView.getContext().getResources().getDrawable(R.drawable.ic_eyed));
        } else readImg.setImageDrawable(null);

    }
}
