package info.upump.russianapp;

import android.support.v4.app.Fragment;

/**
 * Created by explo on 30.01.2018.
 */

public interface IControllerFragment {
    void createFragment(Fragment fragment);
    void setTitle(String title, int imgIdent);
}
