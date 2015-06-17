package natuan.org.androiddesigntablayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.InjectView;
import natuan.org.androiddesigntablayout.fragments.MainFragment;
import natuan.org.androiddesigntablayout.impls.OnFragmentInteractionListener;
import natuan.org.androiddesigntablayout.widgets.adapters.FragmentPageAdapter;


public class MainActivity extends BaseActivity implements OnFragmentInteractionListener {
    @InjectView(R.id.viewpager)
    ViewPager mViewpager;
    @InjectView(R.id.tabs)
    TabLayout mTabs;
    private FragmentPageAdapter pageAdapter;
    String library, recents, favourites, notifications, settings;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {
        library = getString(R.string.library);
        recents = getString(R.string.recents);
        favourites = getString(R.string.favourites);
        notifications = getString(R.string.notifications);
        settings = getString(R.string.settings);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        setupViewPager(mViewpager);
        setupTabLayout(mTabs);
    }

    public void setupViewPager(ViewPager viewPager) {
        pageAdapter = new FragmentPageAdapter(getApplicationContext(), getSupportFragmentManager());
        pageAdapter.addFragment(MainFragment.getInstance(library), library, R.drawable.ic_tabbar_library);
        pageAdapter.addFragment(MainFragment.getInstance(recents), recents, R.drawable.ic_tabbar_recents);
        pageAdapter.addFragment(MainFragment.getInstance(favourites), favourites, R.drawable.ic_tabbar_favorites);
        pageAdapter.addFragment(MainFragment.getInstance(notifications), notifications, R.drawable.ic_tabbar_notifications);
        pageAdapter.addFragment(MainFragment.getInstance(settings), settings, R.drawable.ic_tabbar_settings);
        viewPager.setAdapter(pageAdapter);
    }

    public void setupTabLayout(TabLayout tabLayout) {
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(mViewpager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(pageAdapter.getTabView(i));
        }
        tabLayout.requestFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
