package com.hkm.layout.fragment;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.hkm.layout.Dialog.ErrorMessage;
import com.hkm.layout.NonSwipe;
import com.hkm.layout.R;
import com.hkm.layout.WeiXinTabLayout;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.uiUtils.ScrollSmoothLineaerLayoutManager;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItems;
import com.squareup.picasso.Picasso;

/**
 * Created by hesk on 12/10/15.
 */
public abstract class sub_pages extends Fragment {
    protected Handler hlder = new Handler();
    protected NonSwipe mNonSwipe;
    private int mScrollState;

    protected abstract FragmentPagerItems.Creator addFragmentsToStack(FragmentPagerItems.Creator creator);

    private class InternalViewPagerListener implements ViewPager.OnPageChangeListener {


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            onDaPageSelected(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            mScrollState = state;
        }
    }


    protected abstract void onDaPageSelected(final int position);

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void renderviewlayout(View view) throws Exception {
        FragmentPagerItems mCreate = addFragmentsToStack(FragmentPagerItems.with(getActivity())).create();
        mNonSwipe = (NonSwipe) view.findViewById(R.id.lylib_main_frame_body);
        // mSmartTabLayout = (WeiXinTabLayout) findViewById(R.id.lylib_bottom_tab_smart_layout);
        // mSmartTabLayout.setCustomTabView(this);
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getChildFragmentManager(), mCreate);
        mNonSwipe.setAdapter(adapter);
        mNonSwipe.setOffscreenPageLimit(4);
        mNonSwipe.addOnPageChangeListener(new InternalViewPagerListener());
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            renderviewlayout(view);
        } catch (Exception e) {
            ErrorMessage.alert(e.getMessage(), getChildFragmentManager());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getlayout(), container, false);
    }

    @LayoutRes
    protected int getlayout() {
        return R.layout.template_subswip;
    }

}
