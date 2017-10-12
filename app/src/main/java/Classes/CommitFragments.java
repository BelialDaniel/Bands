package Classes;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.gb.dokkastudios.garagebands.R;

/**
 * Created by BelialDaniel on 9/22/17.
 */

public class CommitFragments
{
    public static Fragments commitFragment(FragmentManager _fragMan, int _contenLayoutID, Class _class)
    {
        Fragments _frag = FragmentF.getFragment(_class);
        _fragMan.beginTransaction().replace(
                _contenLayoutID,
                _frag,
                _frag.FRAGMENT_TAG
        ).commit();

        return _frag;
    }

    public static Fragments commitFragment(FragmentManager _fragMan, int _contenLayoutID, Class _class, int _enterAnimation, int _exitAnimation)
    {
        Fragments _frag = FragmentF.getFragment(_class);
        _fragMan.beginTransaction().setCustomAnimations(_enterAnimation, _exitAnimation).replace(
                _contenLayoutID,
                _frag,
                _frag.FRAGMENT_TAG
        ).commit();

        return _frag;
    }

    public static Fragments commitFragmentToBack(FragmentManager _fragMan, int _contenLayoutID, Class _class)
    {
        Fragments _frag = FragmentF.getFragment(_class);
        _fragMan.beginTransaction().replace(
                _contenLayoutID,
                _frag,
                _frag.FRAGMENT_TAG
        ).addToBackStack(_frag.FRAGMENT_TAG).commit();

        return _frag;
    }

    public static Fragments commitFragmentToBack(FragmentManager _fragMan, int _contenLayoutID, Class _class, View cardView, String transitionName)
    {
        Fragments _frag = FragmentF.getFragment(_class);
        _fragMan.beginTransaction()
                .addSharedElement(cardView, transitionName)
                .replace(
                        _contenLayoutID,
                        _frag,
                        _frag.FRAGMENT_TAG
                ).addToBackStack(_frag.FRAGMENT_TAG).commit();

        return _frag;
    }

    public static Fragments commitFragmentToBack(FragmentManager _fragMan, int _contenLayoutID, Class _class, int _enterAnimation, int _exitAnimation)
    {
        Fragments _frag = FragmentF.getFragment(_class);
        _fragMan.beginTransaction().setCustomAnimations(_enterAnimation, _exitAnimation).replace(
                _contenLayoutID,
                _frag,
                _frag.FRAGMENT_TAG
        ).addToBackStack(_frag.FRAGMENT_TAG).commit();

        return _frag;
    }

    public static Fragments commitFragmentToBack(FragmentManager _fragMan, int _contenLayoutID, Class _class, int _enterAnimation, int _exitAnimation, int _popEnter, int _popExit)
    {
        Fragments _frag = FragmentF.getFragment(_class);
        _fragMan.beginTransaction().setCustomAnimations(_enterAnimation, _exitAnimation, _popEnter, _popExit).replace(
                _contenLayoutID,
                _frag,
                _frag.FRAGMENT_TAG
        ).addToBackStack(_frag.FRAGMENT_TAG).commit();

        return _frag;
    }
}