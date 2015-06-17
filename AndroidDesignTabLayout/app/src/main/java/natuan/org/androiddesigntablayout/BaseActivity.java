package natuan.org.androiddesigntablayout;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Tuan Nguyen on 6/1/2015.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    protected abstract int getLayoutResource();
    protected abstract void initVariables(Bundle savedInstanceState);
    protected abstract void initData(Bundle savedInstanceState);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        ButterKnife.inject(this);
        mContext = getApplicationContext();
        initVariables(savedInstanceState);
        initData(savedInstanceState);
    }

    /**
     * Start Activity with bundle
     *
     * @param clazz
     * @param bundle
     */
    protected void startActivity(Class clazz, Bundle bundle) {
        try {
            Intent intent = new Intent(this, clazz);
            if (bundle != null)
                intent.putExtras(bundle);
            startActivity(intent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Start Activity without bundle
     *
     * @param clazz
     */
    protected void startActivity(Class clazz) {
        startActivity(clazz, null);
    }
}
