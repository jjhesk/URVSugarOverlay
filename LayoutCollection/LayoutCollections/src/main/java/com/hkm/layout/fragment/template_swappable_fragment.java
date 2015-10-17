package com.hkm.layout.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hkm.layout.R;

/**
 * Created by hesk on 30/9/15.
 */
public abstract class template_swappable_fragment extends Fragment {
    private static final String TAG = "RootFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.content_swap_fragment, container, false);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        /*
         * When this container fragment is created, we fill it with our first
		 * "real" fragment
		 */
        transaction.replace(R.id.lylib_root_frame, getInitatedFragment());
        transaction.commit();
        return view;
    }

    protected abstract Fragment getInitatedFragment();

    public void swap2Fragment(Class<? extends Fragment> classname, Bundle args) {
        Fragment fragment_initiated = Fragment.instantiate(getActivity(), classname.getName(), args);
        swapFragment(fragment_initiated);
    }

    public void swap2Fragment(Class<? extends Fragment> classname) {
        Fragment fragment_initiated = Fragment.instantiate(getActivity(), classname.getName(), new Bundle());
        swapFragment(fragment_initiated);
    }

    private void swapFragment(Fragment fragment) {
        FragmentTransaction trans = getFragmentManager().beginTransaction();
                /*
                 * IMPORTANT: We use the "root frame" defined in
				 * "root_fragment.xml" as the reference to replace fragment
				 */
        trans.replace(R.id.lylib_root_frame, fragment);
                /*
                 * IMPORTANT: The following lines allow us to add the fragment
				 * to the stack and return to it later, by pressing back
				 */
        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        trans.addToBackStack(null);
        trans.commit();
    }
}
