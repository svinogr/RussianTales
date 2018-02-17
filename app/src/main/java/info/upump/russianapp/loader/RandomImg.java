package info.upump.russianapp.loader;

import android.content.Context;

import java.util.Random;

/**
 * Created by explo on 06.02.2018.
 */

public class RandomImg {
    private static final String[] img = {"i1", "i2", "i3", "i4", "i5", "i6", "i7", "i8", "i9", "i10", "i11", "i12",};
    private static  final int MIN =0;
    private static  final int MAX =11;

    public static int getRandomIdentForImg(Context context) {
        Random rnd = new Random(System.currentTimeMillis());
        int random = MIN + rnd.nextInt(MAX);
        return context.getResources().getIdentifier(img[random], "drawable", context.getPackageName());
    }
}
