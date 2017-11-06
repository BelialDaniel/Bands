package com.dokkastudios.enkore.fragment.util;

import com.dokkastudios.enkore.fragment.Fragments;
import com.dokkastudios.enkore.ui.fragments.FBandsCategories;
import com.dokkastudios.enkore.ui.fragments.FInfoBand;
import com.dokkastudios.enkore.ui.fragments.FInfoEvent;
import com.dokkastudios.enkore.ui.fragments.FInitApp;
import com.dokkastudios.enkore.ui.fragments.FListBands;
import com.dokkastudios.enkore.ui.fragments.FListEvents;
import com.dokkastudios.enkore.ui.fragments.FListSongs;
import com.dokkastudios.enkore.ui.fragments.FLogIn;
import com.dokkastudios.enkore.ui.fragments.FMap;
import com.dokkastudios.enkore.ui.fragments.FNoInternet;
import com.dokkastudios.enkore.ui.fragments.FSingUp;

/**
 * Created by BelialDaniel on 8/31/17.
 */
@Deprecated
public class FragmentFactory
{
    /**
     *
     * @param _class
     * @param _callback
     * @return
     */
     @Deprecated
    static public Fragments createFragment(Class _class, Object _callback)
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

        return _frag;
    }

    static public Fragments createFragment(Class _class)
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
        else
            throw new RuntimeException("Missing " + _class.getName() + " fragment ");

        return _frag;
    }
}