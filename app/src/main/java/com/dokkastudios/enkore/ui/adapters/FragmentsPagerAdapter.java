package com.dokkastudios.enkore.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dokkastudios.enkore.fragment.Fragments;
import com.dokkastudios.enkore.fragment.util.FragmentFactory;

import java.util.ArrayList;
import java.util.List;

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
    public Fragment getItem(int position)
    {
        return _mFragments.get(position);
    }

    @Deprecated
    public void addFragment(Class _class, String _name)
    {
        _mFragments.add(FragmentFactory.createFragment(_class));
        _mTitles.add(_name);
    }

    public void addFragment(Fragments frag, String name)
    {
        _mFragments.add(frag);
        _mTitles.add(name);
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