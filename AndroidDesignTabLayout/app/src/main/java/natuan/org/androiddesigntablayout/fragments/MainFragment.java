package natuan.org.androiddesigntablayout.fragments;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.InjectView;
import natuan.org.androiddesigntablayout.R;

/**
 * Created by Tuan on 6/18/2015.
 */
public class MainFragment extends BaseFragment {

    @InjectView(R.id.tv)
    TextView mTv;

    private String message;

    public static MainFragment getInstance(String message) {
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString("MSG", message);
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {
        Bundle bundle=getArguments();
        this.message = (String) bundle.get("MSG");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mTv.setText(this.message);
    }
}
