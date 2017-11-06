package com.dokkastudios.enkore.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by BelialDaniel on 9/22/17.
 */

@Deprecated
public abstract class FragmentApplication implements IFragmentApplication
{
    private FragmentManager mFragmentManager;

    public FragmentApplication(FragmentManager fragmentManager)
    {
        mFragmentManager = fragmentManager;
    }

    protected abstract Fragments createFragment(Class _class);

    @Override
    public void commit(int contenLayoutID, Class _class)
    {
        Fragments _frag = createFragment(_class);

        mFragmentManager.beginTransaction().replace(
                contenLayoutID,
                _frag,
                _class.getSimpleName()
        ).commit();
    }

    @Override
    public void commit(int contenLayoutID, Class _class, int enterAnimation, int exitAnimation)
    {
        Fragments _frag = createFragment(_class);

        mFragmentManager.beginTransaction().setCustomAnimations(enterAnimation, exitAnimation).replace(
                contenLayoutID,
                _frag,
                _class.getSimpleName()
        ).commit();
    }

    @Override
    public void commit(int contenLayoutID, Class _class, int enterAnimation, int exitAnimation, int popEnter, int popExit)
    {
        Fragments _frag = createFragment(_class);

        mFragmentManager.beginTransaction().setCustomAnimations(enterAnimation, exitAnimation, popEnter, popExit).replace(
                contenLayoutID,
                _frag,
                _class.getSimpleName()
        ).addToBackStack(_class.getSimpleName()).commit();
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