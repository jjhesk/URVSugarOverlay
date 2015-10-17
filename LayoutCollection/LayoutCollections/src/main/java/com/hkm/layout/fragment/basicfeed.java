package com.hkm.layout.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.uiUtils.ScrollSmoothLineaerLayoutManager;
import com.squareup.picasso.Picasso;
import com.hkm.layout.R;

/**
 * Created by hesk on 5/8/15.
 */
public abstract class basicfeed extends Fragment {
    protected Handler hlder = new Handler();
    protected UltimateRecyclerView listview_layout;
    private ProgressBar loadingbar;
    protected Picasso picasso;
    private boolean inital_loading_use = true, isRefresh = false;
    public final static String URL = "data_url", FRAGMENTTITLE = "fragment_title", FRAGMENTTITLE_RESID = "title_idres", LIST_WITH_AD = "HasAdOnIt", SLUG = "slug", REQUEST_TYPE = "typerequest", TAG = "newfeed";
    public final static int
            MAJOR = -1,
            MAJOR_FILTERED = -2,
            CATE = -5,
            CATE_FILTERED = -6,
            UNSET = -9,
            LATEST = -10,
            BROWSERABLE_FULL_URL = -7,
            SEARCH = -8;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // listener = this;
    }

    @LayoutRes
    protected int getlayout() {
        return R.layout.loading_list_simple;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getlayout(), container, false);
    }

    protected abstract void onClickItem(final long routePID);

    protected abstract void onClickItem(final String route);

    protected abstract void setUltimateRecyclerViewExtra(final UltimateRecyclerView listview);

    /**
     * step 1:
     * takes the arguement form the intent bundle and determine if there is a need to queue a loading process. If that is a yes then we need to load up the data before displaying the list out.
     *
     * @param r and the data bundle
     * @return tells if  there is a loading process to be done before hand
     */
    protected abstract boolean onArguments(final Bundle r);

    /**
     * step 2:
     * this is the call for the loading the data stream externally
     */
    protected abstract void loadDataInitial();

    protected ScrollSmoothLineaerLayoutManager mLayoutManager;


    protected int getSmoothDuration() {
        return 300;
    }


    protected void doneInitialLoading() {
        inital_loading_use = false;
        isRefresh = false;
        loadingbar.animate().alpha(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                loadingbar.setVisibility(View.GONE);
            }
        });
    }

    protected boolean isNowInitial() {
        return inital_loading_use;
    }

    protected void setRefreshInitial() {
        inital_loading_use = true;
        isRefresh = true;
    }

    protected boolean isRefresh() {
        return isRefresh;
    }

    private void renderviewlayout(View view) throws Exception {
        listview_layout = (UltimateRecyclerView) view.findViewById(R.id.recyclerlistview);
        loadingbar = (ProgressBar) view.findViewById(R.id.ul_loading_progress);
        mLayoutManager = new ScrollSmoothLineaerLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false, getSmoothDuration());
        listview_layout.setLayoutManager(mLayoutManager);
        listview_layout.setHasFixedSize(false);
        listview_layout.setSaveEnabled(false);
        picasso = Picasso.with(getActivity());
        setUltimateRecyclerViewExtra(listview_layout);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            try {
                renderviewlayout(view);
                if (getArguments() != null) {
                    if (onArguments(getArguments())) {
                        inital_loading_use = true;
                        loadDataInitial();
                    }
                }
            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }
        } else {
            Log.d(TAG, "back from pause");
        }
    }

}
