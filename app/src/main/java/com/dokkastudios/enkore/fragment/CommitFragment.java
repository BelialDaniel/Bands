package com.dokkastudios.enkore.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by BelialDaniel on 11/6/17.
 */

public class CommitFragment
{
    private FragmentManager mFragmentManager = null;

    public CommitFragment(FragmentManager fragmentManager)
    {
        mFragmentManager = fragmentManager;
    }

    public void commit(int _contenLayoutID, Fragments fragment)
    {
        mFragmentManager.beginTransaction().replace(
                _contenLayoutID,
                fragment,
                fragment.getClass().getSimpleName()
        ).commit();
    }

    public void commit(int contenLayoutID, Fragments fragment, int enterAnimation, int exitAnimation)
    {
        mFragmentManager.beginTransaction().setCustomAnimations(enterAnimation, exitAnimation).replace(
                contenLayoutID,
                fragment,
                fragment.getClass().getSimpleName()
        ).commit();
    }

    public void commit(int contenLayoutID, Fragments fragment, int enterAnimation, int exitAnimation, int popEnter, int popExit)
    {
      mFragmentManager.beginTransaction().setCustomAnimations(enterAnimation, exitAnimation, popEnter, popExit).replace(
                contenLayoutID,
              fragment,
              fragment.getClass().getSimpleName()
        ).addToBackStack(fragment.getClass().getSimpleName()).commit();
    }

    public void RemoveFragment(String Tag)
    {
        Fragment frag = mFragmentManager.findFragmentByTag(Tag);
        if(frag != null)
            mFragmentManager.beginTransaction().remove(frag).commit();
    }

    public void PopBackStack()
    {
        if(mFragmentManager.getBackStackEntryCount() > 0)
            for (int i = 0; i < mFragmentManager.getBackStackEntryCount(); i++)
                mFragmentManager.popBackStackImmediate();
    }
}