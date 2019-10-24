package com.gagankaushal.portfolio;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;

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
                AboutFragment homeFragment = new AboutFragment();
                return homeFragment;
            case 1:
               ProjectFragment projectFragment = new ProjectFragment();
                return projectFragment;
            case 2:
                Contact_tab c = new Contact_tab();
                return c;
            default:
                AboutFragment homeFragment1 = new AboutFragment();
                return homeFragment1;
        }
    }

    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        if (position == 0) {
            title = "About";
        } else if (position == 1) {
            title = "Projects";
        } else if (position == 2) {
            title = "Contact us";
        }

        return title;
    }
}