package com.dokkastudios.enkore.fragment;

import android.support.v4.app.FragmentManager;

import com.dokkastudios.enkore.ui.fragments.FInitApp;
import com.dokkastudios.enkore.ui.fragments.FLogIn;
import com.dokkastudios.enkore.ui.fragments.FNoInternet;
import com.dokkastudios.enkore.ui.fragments.FSingUp;

/**
 * Created by BelialDaniel on 10/20/17.
 */

@Deprecated
public class FragmentsMainApp extends FragmentApplication
{

    public FragmentsMainApp(FragmentManager fragmentManager)
    {
        super(fragmentManager);
    }

    @Override
    public Fragments createFragment(Class _class)
    {
        Fragments frag = null;

        if (FLogIn.class.isAssignableFrom(_class))
            frag = new FLogIn();
        else if (FSingUp.class.isAssignableFrom(_class))
            frag = new FSingUp();
        else if (FInitApp.class.isAssignableFrom(_class))
            frag = new FInitApp();
        else if (FNoInternet.class.isAssignableFrom(_class))
            frag = new FNoInternet();
        else new Throwable("Missing fragment " + _class.getSimpleName());

        return frag;
    }
}