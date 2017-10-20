package com.dokkastudios.enkore.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dokkastudios.enkore.fragment.Fragments;
import com.dokkastudios.enkore.ui.adapters.FragmentsPagerAdapter;
import com.gb.dokkastudios.enkor.R;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class FBandsCategories extends Fragments implements ViewPager.OnPageChangeListener
{
    private ViewPager _mViewPFragments = null;
    private TabLayout _mTabLayFragments = null;
    private FragmentsPagerAdapter _mFragAdapter = null;

    public FBandsCategories() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mSView = inflater.inflate(R.layout.f_bands_categories, container, false);
        return mSView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        SetReferences();
    }

    private void SetReferences()
    {
        _mViewPFragments  = mSView.findViewById(R.id._viewPagerMUser);
        _mTabLayFragments = mSView.findViewById(R.id._tabLTitleMUser);

        if(_mFragAdapter == null)
        {
            _mFragAdapter = new FragmentsPagerAdapter(getChildFragmentManager());
            //_mFragAdapter.addFragment(FListBands.class, "Metal");
            _mFragAdapter.addFragment(new FListBands(), "Metal");
        }

        _mViewPFragments.addOnPageChangeListener(this);
        _mViewPFragments.setOffscreenPageLimit(2);
        _mTabLayFragments.setupWithViewPager(_mViewPFragments, true);

        _mViewPFragments.setAdapter(_mFragAdapter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
    {

    }

    @Override
    public void onPageSelected(int position)
    {

    }

    @Override
    public void onPageScrollStateChanged(int state)
    {

    }
}