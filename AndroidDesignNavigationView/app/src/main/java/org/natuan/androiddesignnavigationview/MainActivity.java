package org.natuan.androiddesignnavigationview;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import org.natuan.androiddesignnavigationview.impls.OnFragmentInteractionListener;

import butterknife.InjectView;

/**
 * Created by Tuan on 7/12/2015.
 */
public class MainActivity extends BaseActivity implements OnFragmentInteractionListener {
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.tabs)
    TabLayout mTabs;
    @InjectView(R.id.drawer_layout)
    android.support.v4.widget.DrawerLayout mDrawerLayout;
    @InjectView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {

    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        setupDrawerLayout(mDrawerLayout);
        setupToolbar(mToolbar);
        setupDrawerContent(navigationView);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    public void setupDrawerContent(NavigationView navigationView) {
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    menuItem.setChecked(true);
                    mDrawerLayout.closeDrawers();
                    return true;
                }
            });
        }
    }

    public void setupToolbar(Toolbar toolbar) {
        if (toolbar != null) {
            toolbar.setTitleTextColor(getResources().getColor(R.color.icons));
            setSupportActionBar(toolbar);
            final ActionBar ab = getSupportActionBar();
            ab.setDisplayShowTitleEnabled(true);
            //ab.setDisplayShowHomeEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
            //ab.setHomeButtonEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setupDrawerLayout(DrawerLayout drawerLayout) {
        if (drawerLayout == null) return;
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
                syncState();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                syncState();
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
}
