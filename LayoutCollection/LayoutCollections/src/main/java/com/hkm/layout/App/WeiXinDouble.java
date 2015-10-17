package com.hkm.layout.App;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.hkm.layout.ControllableFrame;
import com.hkm.layout.R;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

/**
 * Created by hesk on 22/9/15.
 */
public abstract class WeiXinDouble<f> extends WeiXinLayout<f> {
    protected ControllableFrame controllingframe;
    protected MaterialSearchView searchView;

    /**
     * the location to setup and configure the toolbar widget under AppCompat V7
     *
     * @param mxToolBarV7 Toolbar object
     */
    @Override
    protected void configToolBar(Toolbar mxToolBarV7) throws NullPointerException {
        searchView = (MaterialSearchView) findViewById(R.id.lylib_search_view);
        searchView.setVoiceSearch(false);
        searchView.setCursorDrawable(R.drawable.color_cursor_white);
        searchView.setSuggestions(getSuggestions());
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Snackbar.make(findViewById(R.id.container), "Query: " + query, Snackbar.LENGTH_LONG).show();
                searchSubmission(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });
    }

    protected void searchSubmission(String query) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    searchView.setQuery(searchWrd, false);
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    protected String[] getSuggestions() {
        return new String[]{};
    }

    @Override
    protected void onDaPageSelected(int position) {
        closeStaticFragment();
    }

    @Override
    protected int getDefaultMainActivityLayoutId() {
        return BODY_LAYOUT.weixindual.getResID();
    }

    protected int targetHomeFrame() {
        return R.id.lylib_main_frame_body_secondary;
    }

    @Override
    protected void afterInitContentViewToolBar() {
        controllingframe = (ControllableFrame) findViewById(targetHomeFrame());
    }

    /**
     * when the fragment is changed now and it will notify the function for user specific operations
     *
     * @param new_fragment_change_now the generic fragment type
     */
    @Override
    protected void notifyOnBodyFragmentChange(f new_fragment_change_now) {
        super.notifyOnBodyFragmentChange(new_fragment_change_now);
        controllingframe = (ControllableFrame) findViewById(targetHomeFrame());
        controllingframe.setVisibility(View.VISIBLE);
    }

    protected void closeStaticFragment() {
        if (isSingleFrameOpenned()) {
            controllingframe.setVisibility(View.GONE);
            mViewPager.setVisibility(View.VISIBLE);
        }
    }

    protected boolean isSingleFrameOpenned() {
        controllingframe = (ControllableFrame) findViewById(targetHomeFrame());
        return controllingframe.getVisibility() == View.VISIBLE;
    }

    protected void onFreezeScreen(final boolean bool) {
        if (bool) {
            if (controllingframe != null && controllingframe.getVisibility() == View.VISIBLE) {
                controllingframe.setDimColor(R.color.white_screen);
            } else if (mViewPager != null && mViewPager.getVisibility() == View.VISIBLE) {
                mViewPager.setDimColor(R.color.white_screen);
            }
        } else {
            if (controllingframe != null && controllingframe.getVisibility() == View.VISIBLE) {
                controllingframe.noBlock();
            } else if (mViewPager != null && mViewPager.getVisibility() == View.VISIBLE) {
                mViewPager.noBlock();
            }
        }

    }
}
