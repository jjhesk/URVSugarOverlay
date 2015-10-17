package com.hkm.layout.fragment;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.quickAdapter.BiAdAdapterSwitcher;
import com.marshalchen.ultimaterecyclerview.quickAdapter.easyRegularAdapter;
import com.marshalchen.ultimaterecyclerview.quickAdapter.simpleAdmobAdapter;

/**
 * Created by hesk on 12/8/15.
 */
public class patchHyprbid extends BiAdAdapterSwitcher {
    public interface externalcb {
        void onrefresh();
    }

    protected externalcb callb;
    protected Runnable new_refresh_default = new Runnable() {
        public void run() {
            if (loading_more != null) {
                boolean ok = loading_more.request_start(1, 0, 0, patchHyprbid.this, true);
                if (ok) {
                    page_now = 1;
                    max_pages = 1;
                    if (callb != null) callb.onrefresh();
                } else if (auto_disable_loadmore) {
                    listview.disableLoadmore();
                }
            }

            listview.setRefreshing(false);
        }
    };

    public patchHyprbid(UltimateRecyclerView view, easyRegularAdapter adapter_without_ad, simpleAdmobAdapter adapter_with_ad) {
        super(view, adapter_without_ad, adapter_with_ad);
        page_now = 2;
        max_pages = 1000;
        setCustomOnFresh(new_refresh_default);
    }

    public patchHyprbid setExternalCallback(externalcb cb) {
        this.callb = cb;
        return this;
    }


}
