package com.dokkastudios.enkore.ui.activities;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dokkastudios.enkore.database.DataBase;
import com.dokkastudios.enkore.fragment.CommitFragment;
import com.dokkastudios.enkore.listeners.LogInAndSignUpStatus;
import com.dokkastudios.enkore.listeners.InternetStatus;
import com.dokkastudios.enkore.services.EnkorServices;
import com.dokkastudios.enkore.ui.fragments.FLogIn;
import com.dokkastudios.enkore.ui.adapters.BackImagesAdapter;
import com.dokkastudios.enkore.ui.fragments.FNoInternet;
import com.dokkastudios.enkore.ui.fragments.FInitApp;
import com.dokkastudios.enkore.ui.fragments.FSingUp;
import com.gb.dokkastudios.enkor.R;

import Classes.Bands;
import com.dokkastudios.enkore.util.CheckInternetConection;
import com.dokkastudios.enkore.database.util.RequestsToTheDataBase;
import Classes.Events;
import com.dokkastudios.enkore.fragment.Fragments;
import com.dokkastudios.enkore.util.PagerIndicator;
import com.dokkastudios.enkore.services.util.RetrofitInstance;
import com.dokkastudios.enkore.util.StoreResources;
import Classes.User;

import com.dokkastudios.enkore.util.RequestTo;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainApp extends AppCompatActivity implements LogInAndSignUpStatus, Callback<Events>, InternetStatus
{
    private Fragments mFragment = null;
    private boolean mStateRequestDB = false;

    private BackImagesAdapter _mBackImages = null;
    private PagerIndicator _mPagerIndicators = null;
    private ViewPager _mVpager = null;

    CommitFragment mCommitFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main_app);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mCommitFragment = new CommitFragment(getSupportFragmentManager());

        //initBackImages();
        iniApp();
    }

    private void initBackImages()
    {
        int[] _backSourcesImages = {R.drawable.kots_s, R.drawable.log};

        _mBackImages = new BackImagesAdapter(getApplicationContext(), _backSourcesImages);

        _mVpager = findViewById(R.id._viewPagerBackImages);
        _mVpager.setAdapter(this._mBackImages);
        _mVpager.setCurrentItem(0, true);

        _mPagerIndicators = new PagerIndicator(getApplicationContext(), (LinearLayout) findViewById(R.id._backImagesIndicator), _mBackImages.getCount());
        _mPagerIndicators.setIndicators(R.drawable.indicator_unselected, R.drawable.indicator_selected, null);

        _mVpager.addOnPageChangeListener(_mPagerIndicators);
    }

    private boolean isFragmentNoInternetActive()
    {
        if(mFragment != null)
            if(mFragment instanceof FNoInternet)
                return true;
        return false;
    }

    private void iniApp()
    {
        if(CheckInternetConection.isNetworkAvailable(getApplicationContext()))
        {
            if(isFragmentNoInternetActive())
                removeFragment(FNoInternet.class);

            checkUserFromDB();
        }
        else
        {
            if(isFragmentNoInternetActive())
                return;
            mCommitFragment.commit(R.id._contentFragsMApp, (mFragment = new FNoInternet()));
        }
    }

    /**************************/

    @Override
    public void onLogInSuccess(final User _user)
    {
        removeFragment(FLogIn.class);

        RequestsToTheDataBase.withContext(this).requestTo(new RequestTo<DataBase>()
        {
            @Override
            public void requestTo(DataBase request)
            {
                mStateRequestDB = request.addUser(_user);
            }
        });

        if(mStateRequestDB)
            getResourcesDataBase();
        else
            Toast.makeText(getApplicationContext(), " [Store User] An error has occurred ", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess()
    {
        mCommitFragment.PopBackStack();
        Toast.makeText(getApplicationContext(), "The User has been created", Toast.LENGTH_LONG).show();
        removeFragment(FSingUp.class);
    }

    @Override
    public void isInternetAvailable()
    {
        iniApp();
    }

    @Override
    public void onSignUpClicked()
    {
        mCommitFragment.commit(R.id._contentFragsMApp, (mFragment = new FSingUp()), R.anim.enter_animation, R.anim.exit_animation, R.anim.pop_enter, R.anim.pop_exit);
    }

    @Override
    public void onLogInClicked()
    {
        mCommitFragment.commit(R.id._contentFragsMApp, (mFragment = new FLogIn()), R.anim.enter_animation, R.anim.exit_animation, R.anim.pop_enter, R.anim.pop_exit);
    }
    /**************************/

    private void removeFragment(Class _class)
    {
        mCommitFragment.RemoveFragment(mFragment.getClass().getSimpleName());
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        mCommitFragment.PopBackStack();
    }

    private void startEnkore()
    {
        Intent _userAct = new Intent(this, MainUser.class);
        startActivity(_userAct);
        this.finish();
    }

    private void getResourcesDataBase()
    {
        getBE();
    }

    private void checkUserFromDB()
    {
        RequestsToTheDataBase.withContext(this).requestTo(new RequestTo<DataBase>()
        {
            @Override
            public void requestTo(DataBase request)
            {
                StoreResources.setUser(request.getUser());
            }
        });

        if (StoreResources.getUser() != null)
            getResourcesDataBase();
        else
            mCommitFragment.commit(R.id._contentFragsMApp, (mFragment = new FInitApp()), R.anim.enter_down_to_up, R.anim.exit_down_to_up);
      }

    private void getB()
    {
        Retrofit _retrofit = RetrofitInstance.getRetrofit();
        EnkorServices _serviceGB = _retrofit.create(EnkorServices.class);
        Call<Bands> _bandReq = _serviceGB.getBands();
        _bandReq.enqueue(new Callback<Bands>()
        {
            @Override
            public void onResponse(Call<Bands> call, Response<Bands> response)
            {
                if(response.isSuccessful())
                {
                    StoreResources.setBands(response.body());

                    if(StoreResources.getBands() != null)
                        if(StoreResources.getBands().getBands() != null)
                            startEnkore();
                        else
                            Toast.makeText(getApplicationContext(), "[MainApp Bands] Something went wrong getting the Bands =(", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "[MainApp Bands] Something went wrong : " + response.code() + " " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Bands> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(), "[MainApp Bands] Something went wrong : " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.i("Tag Class", t.getMessage());
            }
        });
    }

    private void getBE()
    {
        Retrofit _retrofit = RetrofitInstance.getRetrofit();
        EnkorServices __serviceGB = _retrofit.create(EnkorServices.class);
        Call<Events> _bandsEvtsReq = __serviceGB.getEvents();
        _bandsEvtsReq.enqueue(this);
    }

    @Override
    public void onResponse(Call<Events> call, Response<Events> response)
    {
        if(response.isSuccessful())
        {
            StoreResources.setEvents(response.body());
            if(StoreResources.getEvents() != null)
                if (StoreResources.getEvents().getEvents() != null)
                    getB();
                else
                    Toast.makeText(getApplicationContext(), "[MainApp Events] Something went wrong getting the Events ", Toast.LENGTH_LONG).show();

        }
        else
            Toast.makeText(getApplicationContext(), "[MainApp Events] Something went wrong getting Events: " + response.code() +  " " + response.message(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(Call<Events> call, Throwable t)
    {
        if(t instanceof SocketTimeoutException)
            Toast.makeText(getApplicationContext(), "[MainApp Events] Time out: " + t.getMessage(), Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(), "[MainApp Events] Something went wrong getting Events: " + t.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}