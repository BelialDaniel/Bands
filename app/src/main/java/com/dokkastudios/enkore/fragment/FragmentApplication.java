package com.dokkastudios.enkore.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by BelialDaniel on 9/22/17.
 */

public abstract class FragmentApplication implements IFragmentApplication
{
    private FragmentManager mFragmentManager;

    public FragmentApplication(FragmentManager fragmentManager)
    {
        mFragmentManager = fragmentManager;
    }

    protected abstract Fragments createFragment(Class _class);

    @Override
    public void commit(int _contenLayoutID, Class _class)
    {
        Fragments _frag = createFragment(_class);
        _frag.setFragmentTag(_class.getSimpleName());

        mFragmentManager.beginTransaction().replace(
                _contenLayoutID,
                _frag,
                _frag.getFragmentTag()
        ).commit();
    }

    @Override
    public void commit(int _contenLayoutID, Class _class, int _enterAnimation, int _exitAnimation)
    {
        Fragments _frag = createFragment(_class);
        _frag.setFragmentTag(_class.getSimpleName());

        mFragmentManager.beginTransaction().setCustomAnimations(_enterAnimation, _exitAnimation).replace(
                _contenLayoutID,
                _frag,
                _frag.getFragmentTag()
        ).commit();
    }

    @Override
    public void commit(int _contenLayoutID, Class _class, int _enterAnimation, int _exitAnimation, int _popEnter, int _popExit)
    {
        Fragments _frag = createFragment(_class);
        _frag.setFragmentTag(_class.getSimpleName());

        mFragmentManager.beginTransaction().setCustomAnimations(_enterAnimation, _exitAnimation, _popEnter, _popExit).replace(
                _contenLayoutID,
                _frag,
                _frag.getFragmentTag()
        ).addToBackStack(_frag.getFragmentTag()).commit();
    }

    @Override
    public void removeFragment(Class _class)
    {
        Fragment _frag = mFragmentManager.findFragmentByTag(_class.getSimpleName());
        if(_frag != null)
            mFragmentManager.beginTransaction().remove(_frag).commit();
    }

    @Override
    public void popBackStack()
    {
        if(mFragmentManager.getBackStackEntryCount() > 0)
            for (int i = 0; i < mFragmentManager.getBackStackEntryCount(); i++)
                mFragmentManager.popBackStackImmediate();
    }
}