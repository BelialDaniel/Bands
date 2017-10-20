package com.dokkastudios.enkore.fragment;

import android.support.v4.app.FragmentManager;

import com.dokkastudios.enkore.ui.fragments.FInfoBand;
import com.dokkastudios.enkore.ui.fragments.FInfoEvent;
import com.dokkastudios.enkore.ui.fragments.FListBands;
import com.dokkastudios.enkore.ui.fragments.FListEvents;
import com.dokkastudios.enkore.ui.fragments.FListSongs;
import com.dokkastudios.enkore.ui.fragments.FMap;

/**
 * Created by BelialDaniel on 10/20/17.
 */

public class CommitFragmentsMainUser extends CommitAFragment
{
    public CommitFragmentsMainUser(FragmentManager fragmentManager)
    {
        super(fragmentManager);
    }

    @Override
    protected Fragments createFragment(Class _class)
    {
        Fragments frag = null;

        if (FInfoBand.class.isAssignableFrom(_class))
            frag = new FInfoBand();
        else if (FInfoEvent.class.isAssignableFrom(_class))
            frag = new FInfoEvent();
        else if (FListBands.class.isAssignableFrom(_class))
            frag = new FListBands();
        else if (FListEvents.class.isAssignableFrom(_class))
            frag = new FListEvents();
        else if (FMap.class.isAssignableFrom(_class))
            frag = new FMap();
        else if (FListSongs.class.isAssignableFrom(_class))
            frag = new FListSongs();

        return frag;
    }
}