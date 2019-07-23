package com.example.katarzyna.playtune.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.katarzyna.playtune.View.ListOfDiscsFragment;
import com.example.katarzyna.playtune.View.ListOfDiscsFragmentByGenre;

public class MyAdapterForFragments extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 5;

    public MyAdapterForFragments(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                try {
                    return ListOfDiscsFragment.class.newInstance();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            case 1:
                return ListOfDiscsFragmentByGenre.newInstance("Hard rock");
            case 2:
                return ListOfDiscsFragmentByGenre.newInstance("Rock progresywny");
            case 3:
                return ListOfDiscsFragmentByGenre.newInstance("Pop");
            case 4:
                return ListOfDiscsFragmentByGenre.newInstance("Muzyka klasyczna");
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Wszystkie";
            case 1:
                return "Hard rock";
            case 2:
                return "Prog rock";
            case 3:
                return "Pop";
            case 4:
                return "Klasyczna";
            default:
                return null;
        }
    }
}
