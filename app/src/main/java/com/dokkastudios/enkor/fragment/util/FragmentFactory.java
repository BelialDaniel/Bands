package com.dokkastudios.enkor.fragment.util;

import com.dokkastudios.enkor.fragment.Fragments;
import com.dokkastudios.enkor.ui.fragments.FBandsCategories;
import com.dokkastudios.enkor.ui.fragments.FInfoBand;
import com.dokkastudios.enkor.ui.fragments.FInfoEvent;
import com.dokkastudios.enkor.ui.fragments.FInitApp;
import com.dokkastudios.enkor.ui.fragments.FListBands;
import com.dokkastudios.enkor.ui.fragments.FListEvents;
import com.dokkastudios.enkor.ui.fragments.FListSongs;
import com.dokkastudios.enkor.ui.fragments.FLogIn;
import com.dokkastudios.enkor.ui.fragments.FMap;
import com.dokkastudios.enkor.ui.fragments.FNoInternet;
import com.dokkastudios.enkor.ui.fragments.FSingUp;

/**
 * Created by BelialDaniel on 8/31/17.
 */

public class FragmentFactory
{
    /**
     *
     * @param _class
     * @param _callback
     * @return
     */
     @Deprecated
    public static Fragments getFragment(Class _class, Object _callback)
    {
        Fragments _frag = null;

        if (FInfoBand.class.isAssignableFrom(_class))
            _frag = new FInfoBand();
        else if (FInfoEvent.class.isAssignableFrom(_class))
            _frag = new FInfoEvent();
        else if(FListBands.class.isAssignableFrom(_class))
            _frag = new FListBands();
        else if (FListEvents.class.isAssignableFrom(_class))
            _frag = new FListEvents();
        else if (FLogIn.class.isAssignableFrom(_class))
            _frag = new FLogIn();
        else if (FSingUp.class.isAssignableFrom(_class))
            _frag = new FSingUp();
        else if (FMap.class.isAssignableFrom(_class))
            _frag = new FMap();
        else if (FListSongs.class.isAssignableFrom(_class))
            _frag = new FListSongs();
        else if (FInitApp.class.isAssignableFrom(_class))
            _frag = new FInitApp();

        _frag.FRAGMENT_TAG = _class.getSimpleName();
        _frag.RegisterListener(_callback);
        return _frag;
    }

    public static Fragments getFragmentClass(Class _class)
    {
        Fragments _frag = null;
        _frag.FRAGMENT_TAG = _class.getSimpleName();
        return _frag;
    }

    public static Fragments getFragment(Class _class)
    {
        Fragments _frag = null;

        if (FInfoBand.class.isAssignableFrom(_class))
            _frag = new FInfoBand();
        else if (FInfoEvent.class.isAssignableFrom(_class))
            _frag = new FInfoEvent();
        else if (FListBands.class.isAssignableFrom(_class))
            _frag = new FListBands();
        else if (FListEvents.class.isAssignableFrom(_class))
            _frag = new FListEvents();
        else if (FLogIn.class.isAssignableFrom(_class))
            _frag = new FLogIn();
        else if (FSingUp.class.isAssignableFrom(_class))
            _frag = new FSingUp();
        else if (FMap.class.isAssignableFrom(_class))
            _frag = new FMap();
        else if (FListSongs.class.isAssignableFrom(_class))
            _frag = new FListSongs();
        else if (FInitApp.class.isAssignableFrom(_class))
            _frag = new FInitApp();
        else if (FNoInternet.class.isAssignableFrom(_class))
            _frag = new FNoInternet();
        else if(FBandsCategories.class.isAssignableFrom(_class))
            _frag = new FBandsCategories();

        _frag.FRAGMENT_TAG = _class.getSimpleName();
        return _frag;
    }
}