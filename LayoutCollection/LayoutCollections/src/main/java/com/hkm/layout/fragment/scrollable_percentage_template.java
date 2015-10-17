package com.hkm.layout.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hkm.layout.R;
import com.squareup.picasso.Picasso;

/**
 * Created by hesk on 23/9/15.
 */
public abstract class scrollable_percentage_template extends Fragment {
    public static String TAG = "catelog";
    protected Picasso picasso;
    private ImageView loc1, loc2, loc3, loc4, loc5, loc6;

    protected void bindViews(View view) {
        loc1 = (ImageView) view.findViewById(R.id.image1);
        loc2 = (ImageView) view.findViewById(R.id.image2);
        loc5 = (ImageView) view.findViewById(R.id.image3);
        loc6 = (ImageView) view.findViewById(R.id.image4);
        loc3 = (ImageView) view.findViewById(R.id.image5);
        loc4 = (ImageView) view.findViewById(R.id.image6);
        loadingUp(loc1, loc2, loc3, loc4, loc5, loc6);
    }

    protected abstract void loadingUp(
            final ImageView loc1,
            final ImageView loc2,
            final ImageView loc3,
            final ImageView loc4,
            final ImageView loc5,
            final ImageView loc6);

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
    }

    @LayoutRes
    protected int getFragmentResId() {
        return R.layout.content_grid_original;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getFragmentResId(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            try {
                bindViews(view);
            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }
        } else {
            Log.d(TAG, "back from pause");
        }
    }

}
