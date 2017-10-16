package com.gb.dokkastudios.enkor.Adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import com.dokkastudios.enkor.fragment.util.FragmentFactory;
import com.dokkastudios.enkor.fragment.Fragments;

/**
 * Created by BelialDaniel on 9/8/17.
 */

public class FragmentsPagerAdapter extends FragmentStatePagerAdapter
{
    private List<String> _mTitles = null;
    private List<Fragments> _mFragments = null;

    public FragmentsPagerAdapter(FragmentManager _fm)
    {
        super(_fm);
        _mFragments = new ArrayList<>();
        _mTitles = new ArrayList<>();
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position)
    {
        return _mFragments.get(position);
    }

    public void addFragment(Class _class, String _name)
    {
        _mFragments.add(FragmentFactory.getFragment(_class));
        _mTitles.add(_name);
    }

    @Override
    public int getCount()
    {
        return _mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return _mTitles.get(position);
    }
}