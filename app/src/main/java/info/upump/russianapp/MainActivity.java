package info.upump.russianapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IControllerFragment {

    private static final String MOON = "moon";
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private ImageView imageView;
    private Menu menuTool;
    public IVolumeControl iVolumeControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (savedInstanceState == null) {
            SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

            boolean moon = sharedPref.getBoolean(MOON, false);
            // Set the local night mode to some value
            if (moon) {
                getDelegate().setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES);
            } else getDelegate().setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
            // Now recreate for it to take effect
            recreate();
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        collapsingToolbarLayout = findViewById(R.id.main_collapsing);
        appBarLayout = findViewById(R.id.main_appbar);
        imageView = findViewById(R.id.main_backdrop);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setVisibility(View.INVISIBLE);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            CoverFragment coverFragment = CoverFragment.newInstance();
            createFragment(coverFragment);
        }
        MobileAds.initialize(this, "ca-app-pub-7715449191385617~5078694184");


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (getSupportFragmentManager().getBackStackEntryCount() < 1) {
            finish();
        }
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menuTool = menu;
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if (id == R.id.action_settings) {
            int mode = AppCompatDelegate.getDefaultNightMode();
            if (mode == AppCompatDelegate.MODE_NIGHT_NO) {

                getDelegate().setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES);
                editor.putBoolean("moon", true);

            } else {
                getDelegate().setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO);
                editor.putBoolean(MOON, false);
            }


            editor.apply();//студия посоветовала вместо комит???

            recreate();


            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        int id = item.getItemId();

        if (id == R.id.nav_history) {
            fragment = CoverFragment.newInstance();

        } else if (id == R.id.nav_favorite) {
            fragment = FavoriteCoverFragment.newInstance();

        } else if (id == R.id.nav_send_history) {
            Intent email = new Intent(Intent.ACTION_SEND);
            email.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.mail_to)});
            email.putExtra(Intent.EXTRA_SUBJECT, "CreepyApp");
            email.putExtra(Intent.EXTRA_TEXT, "");
            email.setType("plain/text");
            startActivity(Intent.createChooser(email, "Choose an Email client :"));

        }
        if (fragment != null) {
            createFragment(fragment);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void createFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        System.out.println(getSupportFragmentManager().getBackStackEntryCount());
        if (!(fragment instanceof CoverFragment)) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    @Override
    public void setTitle(String title, int imgIdent) {
        collapsingToolbarLayout.setTitle(title);
        appBarLayout.setExpanded(true);
        Picasso.with(this).load(imgIdent).into(imageView);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if( getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof IVolumeControl) {
            iVolumeControl = (IVolumeControl) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            System.out.println(iVolumeControl);
        }
        switch (keyCode) {

            case KeyEvent.KEYCODE_VOLUME_UP:
                if(iVolumeControl != null){
                    iVolumeControl.Up();
                    Toast.makeText(this, "UP", Toast.LENGTH_SHORT)
                            .show();
                }


                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if(iVolumeControl != null){
                    iVolumeControl.Down();
                }

                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
