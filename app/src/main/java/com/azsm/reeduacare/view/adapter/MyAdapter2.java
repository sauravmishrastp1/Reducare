package com.azsm.reeduacare.view.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.azsm.reeduacare.view.fragment.Sttudy_Marrial;
import com.azsm.reeduacare.view.fragment.TestSeries_Adeapter;
import com.azsm.reeduacare.view.fragment.Viedos_fragment;


public class MyAdapter2 extends FragmentPagerAdapter {


    private Context myContext;
    int totalTabs;


    public MyAdapter2(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;

    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Sttudy_Marrial myProducts = new Sttudy_Marrial();
                return myProducts;
            case 1:
                Viedos_fragment allProducts = new Viedos_fragment();
                return allProducts;
            case 2:
                TestSeries_Adeapter allProducts2 = new TestSeries_Adeapter();
                return allProducts2;

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
