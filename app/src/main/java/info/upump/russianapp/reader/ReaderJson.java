package info.upump.russianapp.reader;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import info.upump.russianapp.db.CoverDao;
import info.upump.russianapp.db.TaleDao;
import info.upump.russianapp.model.Author;
import info.upump.russianapp.model.Cover;
import info.upump.russianapp.model.Tale;

/**
 * Created by explo on 04.02.2018.
 */

public class ReaderJson {
    private Context context;

    public ReaderJson(Context context) {
        this.context = context;
    }

    public void starr() throws JSONException {
        List<Cover> listCoverAndTales = createListCoverAndTales();
        writeInDB(listCoverAndTales);
    }
    private void writeInDB(List<Cover> cover){
        CoverDao coverDao = new CoverDao(context);
        TaleDao taleDao = new TaleDao(context);
        for(Cover c: cover){
            Cover save = coverDao.save(c);

            Tale tale = c.getTale();
            tale.setCover(save);
            taleDao.save(tale);
        }


    }


    private List<Cover> createListCoverAndTales() throws JSONException {
        List<Cover> coverList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(readJsonToString());
     /*   private long id;
        private String title;
        private int rate;
        private boolean favorite;
        private boolean read;
        private Author author;
        private Tale tale;
        private String img;*/

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);

            Cover cover = new Cover();
            cover.setTitle(jsonObject.getString("title"));
            cover.setFavorite(false);
            cover.setRead(false);
            cover.setAuthor(new Author(1));
            //  cover.setRate(jsonObject.getInt("rate"));
            cover.setRate(1);

            JSONObject taleJSON = jsonObject.getJSONObject("tale");
            Tale tale = new Tale();
            tale.setText(taleJSON.getString("text"));
            cover.setTale(tale);
            coverList.add(cover);
        }
        return coverList;
    }

    private String readJsonToString() {
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(

                    new InputStreamReader(context.getAssets().open("data.json"), "UTF-8"));
            String line;


            while ((line = reader.readLine()) != null) {
                text.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text.toString();

    }
}
