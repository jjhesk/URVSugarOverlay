package com.hkm.layout.App;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.hkm.layout.Dialog.BottomSheetDialogFragment;
import com.hkm.layout.Menu.TabIconView;
import com.hkm.layout.WeiXinTabLayout;
import com.hkm.layout.NonSwipe;
import com.hkm.layout.R;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItems;

/**
 * Created by hesk on 21/9/15.
 */
public abstract class WeiXinLayout<f> extends fundamental<f> {
    protected NonSwipe mViewPager;
    protected WeiXinTabLayout mSmartTabLayout;
    private int mScrollState;

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

    @LayoutRes
    @Override
    protected int getDefaultMainActivityLayoutId() {
        return SimpleLayout.BODY_LAYOUT.weixin.getResID();
    }

    protected abstract FragmentPagerItems.Creator addFragmentsToStack(FragmentPagerItems.Creator creator);

    @Override
    protected void initMainContentFragment(f fragment, Bundle savestate) throws Exception {
        FragmentPagerItems mCreate = addFragmentsToStack(FragmentPagerItems.with(this)).create();
        mViewPager = (NonSwipe) findViewById(R.id.lylib_main_frame_body);
        mSmartTabLayout = (WeiXinTabLayout) findViewById(R.id.lylib_bottom_tab_smart_layout);
        // mSmartTabLayout.setCustomTabView(this);
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getFragmentManager(), mCreate);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(4);
        mSmartTabLayout.setViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new InternalViewPagerListener());
    }


}
