package com.hkm.layout.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
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
public abstract class percentage_template_home extends Fragment {
    public static String TAG = "catelog";
    protected Picasso picasso;
    private ImageView loc1, loc2, loc3, loc4, loc5;

    protected void bindViews(View view) {
        loc1 = (ImageView) view.findViewById(R.id.loc1);
        loc2 = (ImageView) view.findViewById(R.id.loc2);
        loc3 = (ImageView) view.findViewById(R.id.loc3);
        loc4 = (ImageView) view.findViewById(R.id.loc4);
        loc5 = (ImageView) view.findViewById(R.id.loc5);
        loadingUp(loc1, loc2, loc3, loc4, loc5);
    }

    protected abstract void loadingUp(
            final ImageView loc1,
            final ImageView loc2,
            final ImageView loc3,
            final ImageView loc4,
            final ImageView loc5);

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
    }

    protected int getFragmentResId() {
        return R.layout.template_home;
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
