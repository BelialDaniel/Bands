package com.dokkastudios.enkor.ui.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.dokkastudios.enkor.ui.fragments.FBandsCategories;
import com.dokkastudios.enkor.ui.fragments.FListEvents;
import com.dokkastudios.enkor.ui.holders.EventCardVHolder;
import com.dokkastudios.enkor.ui.fragments.FMap;
import com.gb.dokkastudios.enkor.R;

import com.dokkastudios.enkor.database.DB;
import com.dokkastudios.enkor.listeners.CallbackMainUser;
import com.squareup.picasso.Picasso;

import Classes.Band;
import com.dokkastudios.enkor.fragment.util.CommitFragment;
import com.dokkastudios.enkor.database.util.RequestsToTheDataBase;
import Classes.Event;
import com.dokkastudios.enkor.util.StoreResources;
import Classes.User;

import com.dokkastudios.enkor.util.RequestTo;

public class MainUser extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, CallbackMainUser, BottomNavigationView.OnNavigationItemSelectedListener
{
    private Event mEvent = null;

    private boolean mPopUpVisible = false;
    private boolean mStateRequestDB = false;

    private EventCardVHolder mEventCardHolder = null;

    private View mEventCardV = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main_user);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        SetReferences();
    }

    private void SetReferences()
    {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        BottomNavigationView _mBottomNavView = findViewById(R.id._bottomNavMUser);
        _mBottomNavView.setOnNavigationItemSelectedListener(this);

        setInvisibleCardVEvent();

        mEventCardV = findViewById(R.id._cardVEvent);
        mEventCardHolder = new EventCardVHolder(mEventCardV);
        mEventCardHolder.mEventCardView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                EventCardVPopUpClicked();
            }
        });

        DataFromUser(navigationView);
        CommitFragment.commitFragment(getSupportFragmentManager(), R.id._contentFragsMUser, FListEvents.class);
    }

    private void DataFromUser(NavigationView navigationView)
    {
        User _user;
        if ((_user = StoreResources.getUser()) != null )
        {
            View _header = navigationView.getHeaderView(0);
            ((TextView)_header.findViewById(R.id._navTxtMail)).setText(_user.getEmail());;
            ((TextView)_header.findViewById(R.id._navTxtName)).setText(_user.getFirsName() + " " + _user.getLastName());
        }
    }

    private void LogOut()
    {
        RequestsToTheDataBase.contextToGetDataBase(this).requestTo(new RequestTo<DB>()
        {
            @Override
            public void requestTo(DB request)
            {
                mStateRequestDB = request.deleteUser();
            }
        });

        if(mStateRequestDB)
        {
            startActivity(new Intent(this, MainApp.class));
            this.finish();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        Class _fragClass = null;

        if(getSupportFragmentManager().getBackStackEntryCount() > 0)
            PopBackStack();

        if (mPopUpVisible)
        {
            setInvisibleCardVEvent();
            mPopUpVisible = false;
        }

        switch (item.getItemId())
        {
            case R.id._menu_bottomItem_Events:
                _fragClass = FListEvents.class;
                break;
            case R.id._menu_bottomItem_Bands:
                _fragClass = FBandsCategories.class;
                break;
            case R.id._menu_bottomItem_Map:
                  _fragClass = FMap.class;
                break;
            case R.id._menu_drawerItem_Acount:
                break;
            case R.id._menu_drawerItem_LogOut:
                    LogOut();
                break;
        }

        if(_fragClass != null)
            CommitFragment.commitFragment(getSupportFragmentManager(), R.id._contentFragsMUser, _fragClass,
                    android.R.anim.fade_in, android.R.anim.fade_out);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void PopBackStack()
    {
        for(int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); i++)
                getSupportFragmentManager().popBackStackImmediate();
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBandListClicked(Band band, View cardView)
    {
        //Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(cardViewBand, "selectedBand")).toBundle();
        StoreResources.setBand(band);
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                cardView,
                getResources().getString(R.string.bandSelected)
        ).toBundle();

        Intent intent = new Intent(this, InfoBand.class);
        startActivity(intent, bundle);
    }

    @Override
    public void onEventListClicked(Event event, View cardView)
    {
        StoreResources.setEvent(event);
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                cardView,
                getResources().getString(R.string.eventSelected)
        ).toBundle();

        Intent intent = new Intent(this, InfoEvent.class);
        startActivity(intent, bundle);
    }

    private void EventCardVPopUpClicked()
    {
        mPopUpVisible = false;
        setInvisibleCardVEvent();
        if(mEvent != null)
            onEventListClicked(mEvent, mEventCardV);
    }

    @Override
    public void onClickMarker(final Event event)
    {
        Picasso.with(mEventCardHolder.mEventCardView.getContext())
                .load(R.drawable.no_image)
                .resize(688, 360)
                .centerCrop()
                .into(mEventCardHolder.mImageBackEvent);
        setVisibleCardVEvent();

        mEventCardHolder.mTxtCVEventTitle.setText(event.getName());
        mEvent = event;
        mPopUpVisible = true;

    }

    @Override
    public void onClickMap()
    {
        if (mPopUpVisible)
        {
            setInvisibleCardVEvent();
            mPopUpVisible = false;
        }
    }

    private void setInvisibleCardVEvent()
    {
        (findViewById(R.id._eventCardVPopUp)).setVisibility(View.INVISIBLE);
    }

    private void setVisibleCardVEvent()
    {
        (findViewById(R.id._eventCardVPopUp)).setVisibility(View.VISIBLE);
    }

    /*@Override
    public void onEventListClicked(Event event)
    {
        StoreResources.setEvent(_event);
        CommitFragment.commitFragmentToBack(getSupportFragmentManager(), R.id._contentFragsMUser, FInfoEvent.class,
                android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }*/
}