package natuan.org.androiddesigntablayout.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import natuan.org.androiddesigntablayout.impls.OnFragmentInteractionListener;

/**
 * Created by Tuan Nguyen on 6/1/2015.
 */
public abstract class BaseFragment extends Fragment {

    protected abstract int getLayoutResource();

    protected abstract void initVariables(Bundle savedInstanceState);

    protected abstract void initData(Bundle savedInstanceState);

    protected OnFragmentInteractionListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutResource(), container, false);
        ButterKnife.inject(this, rootView);
        initVariables(savedInstanceState);
        initData(savedInstanceState);
        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    /**
     * Start Activity with Bundle
     *
     * @param clazz
     * @param bundle
     */
    protected void startActivity(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * Start Activity without Bundle
     *
     * @param clazz
     */
    protected void startActivity(Class<?> clazz) {
        startActivity(clazz, null);
    }

}
