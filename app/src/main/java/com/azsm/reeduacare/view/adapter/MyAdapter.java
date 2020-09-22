package com.azsm.reeduacare.view.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.azsm.reeduacare.view.fragment.Active_Fragment;
import com.azsm.reeduacare.view.fragment.Inactive_Fragement;


public class MyAdapter extends FragmentPagerAdapter {


    private Context myContext;
    int totalTabs;
    public String idt;

    public MyAdapter(@NonNull FragmentManager fm, Context myContext, int totalTabs, String idt) {
        super(fm);
        this.myContext = myContext;
        this.totalTabs = totalTabs;
        this.idt = idt;
    }

    public MyAdapter(@NonNull FragmentManager fm, int behavior, Context myContext, int totalTabs, String idt) {
        super(fm, behavior);
        this.myContext = myContext;
        this.totalTabs = totalTabs;
        this.idt = idt;
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
