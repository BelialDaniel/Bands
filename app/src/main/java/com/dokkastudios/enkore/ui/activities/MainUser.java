package com.dokkastudios.enkore.ui.activities;

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

import com.dokkastudios.enkore.database.DataBase;
import com.dokkastudios.enkore.fragment.CommitFragment;
import com.dokkastudios.enkore.fragment.Fragments;
import com.dokkastudios.enkore.listeners.OnBandListClick;
import com.dokkastudios.enkore.listeners.OnEventListClick;
import com.dokkastudios.enkore.listeners.OnMapEventClick;
import com.dokkastudios.enkore.ui.fragments.FBandsCategories;
import com.dokkastudios.enkore.ui.fragments.FListEvents;
import com.dokkastudios.enkore.ui.holders.EventCVHolder;
import com.dokkastudios.enkore.ui.fragments.FMap;
import com.gb.dokkastudios.enkor.R;

import com.squareup.picasso.Picasso;

import Classes.Band;

import com.dokkastudios.enkore.database.util.RequestsToTheDataBase;
import Classes.Event;
import com.dokkastudios.enkore.util.StoreResources;
import Classes.User;

import com.dokkastudios.enkore.util.RequestTo;

public class MainUser extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnMapEventClick, BottomNavigationView.OnNavigationItemSelectedListener,
        OnBandListClick, OnEventListClick
{
    private Event mEvent = null;

    private boolean mPopUpVisible = false;
    private boolean mStateRequestDB = false;

    private EventCVHolder mEventCardHolder = null;

    private View mEventCardV = null;

    //private FragmentApplication mFragmentsMainUser = null;

    CommitFragment mCommitFragment = null;
    Fragments mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main_user);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //mFragmentsMainUser = new FragmentsMainUser(getSupportFragmentManager());

        mCommitFragment = new CommitFragment(getSupportFragmentManager());

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
        mEventCardHolder = new EventCVHolder(mEventCardV);
        mEventCardHolder.mEventCardView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                EventCardVPopUpClicked();
            }
        });

        DataFromUser(navigationView);
        //mFragmentsMainUser.commit(R.id._contentFragsMUser, FListEvents.class);
        mCommitFragment.commit(R.id._contentFragsMUser, (mFragment = new FListEvents()));
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
        RequestsToTheDataBase.withContext(this).requestTo(new RequestTo<DataBase>()
        {
            @Override
            public void requestTo(DataBase request)
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
                //_fragClass = FListEvents.class;
                mFragment = new FListEvents();
                break;
            case R.id._menu_bottomItem_Bands:
                //_fragClass = FBandsCategories.class;
                mFragment = new FBandsCategories();
                break;
            case R.id._menu_bottomItem_Map:
                  //_fragClass = FMap.class;
                mFragment = new FMap();
                break;
            case R.id._menu_drawerItem_Acount:
                break;
            case R.id._menu_drawerItem_LogOut:
                    LogOut();
                break;
        }

        if(mFragment != null)
            mCommitFragment.commit(R.id._contentFragsMUser, mFragment, android.R.anim.fade_in, android.R.anim.fade_out);
        //mFragmentsMainUser.commit(R.id._contentFragsMUser, _fragClass, android.R.anim.fade_in, android.R.anim.fade_out);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void PopBackStack()
    {
        mCommitFragment.PopBackStack();
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
    public void onMarkerClicked(final Event event)
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
    public void onMapClicked()
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
        FragmentApplication.withContext(getSupportFragmentManager(), R.id._contentFragsMUser, FInfoEvent.class,
                android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }*/
}