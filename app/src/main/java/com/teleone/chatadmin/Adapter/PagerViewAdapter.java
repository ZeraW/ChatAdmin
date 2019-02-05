package com.teleone.chatadmin.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.teleone.chatadmin.Fragment.AllUserFragment;
import com.teleone.chatadmin.Fragment.NotificationFragment;
import com.teleone.chatadmin.Fragment.ProfleFragment;


public class PagerViewAdapter extends FragmentPagerAdapter {
    public PagerViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ProfleFragment();
            case 1:
                return new AllUserFragment();
            case 2:
                return new NotificationFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
