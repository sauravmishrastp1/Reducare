package com.azsm.reeduacare.view.fragment;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;



public class MyAdapter extends FragmentPagerAdapter {


    private Context myContext;
    int totalTabs;


    public MyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;

    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Active_Fragment myProducts = new Active_Fragment();
                return myProducts;
            case 1:
                Inactive_Fragement allProducts = new Inactive_Fragement();
                return allProducts;

            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}
