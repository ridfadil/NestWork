package com.sebasku.networks.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sebasku.networks.fragment.RequestCutiFragment;
import com.sebasku.networks.fragment.RiwayatCutiFragment;

/**
 * Created by fadil on 2/4/18.
 */


public class CutiPagerAdapter extends FragmentStatePagerAdapter {

    private int number_tabs;

    public CutiPagerAdapter(FragmentManager fm, int number_tabs) {
        super(fm);
        this.number_tabs = number_tabs;
    }

    //Mengembalikan Fragment yang terkait dengan posisi tertentu
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new RequestCutiFragment();
            case 1:
                return new RiwayatCutiFragment();
            default:
                return null;
        }
    }

    //Mengembalikan jumlah tampilan yang tersedia.
    @Override
    public int getCount() {
        return number_tabs;
    }
}