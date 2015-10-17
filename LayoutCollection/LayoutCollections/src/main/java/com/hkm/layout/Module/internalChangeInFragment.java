package com.hkm.layout.Module;

/**
 * Created by hesk on 17/9/15.
 */
public interface internalChangeInFragment<Fragment> {
    void setinternalChange(Fragment section, String title);

    void setinternalChange(Fragment section, String title, Fragment previousFragment, boolean closedrawer);

    void setinternalChange(Fragment section, String title, Fragment previousFragment);

}
