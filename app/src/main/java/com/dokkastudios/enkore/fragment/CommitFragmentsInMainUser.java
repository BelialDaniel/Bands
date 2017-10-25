package com.dokkastudios.enkore.fragment;

import android.support.v4.app.FragmentManager;

import com.dokkastudios.enkore.ui.fragments.FBandsCategories;
import com.dokkastudios.enkore.ui.fragments.FInfoBand;
import com.dokkastudios.enkore.ui.fragments.FInfoEvent;
import com.dokkastudios.enkore.ui.fragments.FListBands;
import com.dokkastudios.enkore.ui.fragments.FListEvents;
import com.dokkastudios.enkore.ui.fragments.FListSongs;
import com.dokkastudios.enkore.ui.fragments.FMap;
import com.dokkastudios.enkore.ui.fragments.FNoInternet;

/**
 * Created by BelialDaniel on 10/20/17.
 */

public class CommitFragmentsInMainUser extends CommitAFragment
{
    public CommitFragmentsInMainUser(FragmentManager fragmentManager)
    {
        super(fragmentManager);
    }

    @Override
    protected Fragments createFragment(Class _class)
    {
        Fragments frag = null;

        if (FListEvents.class.isAssignableFrom(_class))
            frag = new FListEvents();
        else if (FMap.class.isAssignableFrom(_class))
            frag = new FMap();
        else if (FBandsCategories.class.isAssignableFrom(_class))
            frag = new FBandsCategories();
        else if (FNoInternet.class.isAssignableFrom(_class))
            frag = new FNoInternet();
        else new Throwable("Missing fragment " + _class.getSimpleName());

        return frag;
    }
}