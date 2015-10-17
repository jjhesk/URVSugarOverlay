package com.hkm.layout;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by hesk on 21/8/15.
 */
public class NonSwipe extends ViewPager {
    private boolean enabled = false;
    private boolean locked = false;

    public NonSwipe(Context context) {
        super(context);
    }

    public NonSwipe(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void Enabled(boolean b) {
        enabled = b;
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setDimDrawble(@DrawableRes int dr) {
       // setForeground(getContext().getDrawable(dr));
        locked = true;
    }

    public void setDimDefault() {
      //  setForeground(new ColorDrawable(getContext().getResources().getColor(R.color.white_tab)));
        locked = true;
    }

    public void setDimColor(@ColorRes int n_color) {
      //  setForeground(new ColorDrawable(getContext().getResources().getColor(n_color)));
        locked = true;
    }

    public void noBlock() {
     //   setForeground(null);
        locked = false;
    }

    public void Lock(boolean b) {
        locked = b;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
        return locked;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!locked && enabled) {
            return super.onTouchEvent(event);
        } else {
            // Never allow swiping to switch between pages
            return false;
        }
    }

}
