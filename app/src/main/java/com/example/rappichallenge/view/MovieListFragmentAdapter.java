package com.example.rappichallenge.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan on 27/09/2018.
 */

public class MovieListFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList;

    public MovieListFragmentAdapter(FragmentManager fm) {
        super(fm);
        fragmentList = new ArrayList<>();
        fragmentList.add(new PopularFragment());
        fragmentList.add(new TopRatedFragment());
        fragmentList.add(new UpcomingFragment());
        fragmentList.add(new SearchFragment());

    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "POPULAR";
        } else if (position == 1){
            return "TOP RATED";
        }
        else if (position == 2){
            return "UPCOMING";
        }
        else {
            return "SEARCH";
        }
    }
}