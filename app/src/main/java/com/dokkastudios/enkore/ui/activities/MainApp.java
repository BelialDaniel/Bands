package com.dokkastudios.enkore.ui.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dokkastudios.enkore.listeners.CallbackMainApp;
import com.dokkastudios.enkore.services.EnkorServices;
import com.dokkastudios.enkore.fragment.util.CommitFragment;
import com.dokkastudios.enkore.ui.fragments.FLogIn;
import com.dokkastudios.enkore.ui.adapters.BackImagesAdapter;
import com.dokkastudios.enkore.ui.fragments.FNoInternet;
import com.dokkastudios.enkore.ui.fragments.FInitApp;
import com.dokkastudios.enkore.ui.fragments.FSingUp;
import com.gb.dokkastudios.enkor.R;

import Classes.Bands;
import com.dokkastudios.enkore.util.CheckInternetConection;
import com.dokkastudios.enkore.database.DB;
import com.dokkastudios.enkore.database.util.RequestsToTheDataBase;
import Classes.Events;
import com.dokkastudios.enkore.fragment.Fragments;
import com.dokkastudios.enkore.util.PagerIndicator;
import com.dokkastudios.enkore.services.util.RetrofitInstance;
import com.dokkastudios.enkore.util.StoreResources;
import Classes.User;

import com.dokkastudios.enkore.util.RequestTo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainApp extends AppCompatActivity implements CallbackMainApp, Callback<Events>
{
    private Fragments mFragment = null;
    private boolean mStateRequestDB = false;

    private BackImagesAdapter _mBackImages = null;
    private PagerIndicator _mPagerIndicators = null;
    private ViewPager _mVpager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main_app);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //InitBackImages();
        iniApp();
    }

    private void InitBackImages()
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

    private boolean IsFNoInternetActive()
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
            if(IsFNoInternetActive())
                RemoveFragment(FNoInternet.class);
            CheckUserFromDB();
        }
        else
        {
            if(IsFNoInternetActive())
                return;
            mFragment = CommitFragment.commitFragment(getSupportFragmentManager(), R.id._contentFragsMApp, FNoInternet.class);
        }
    }

    /**************************/

    @Override
    public void LogInSuccess(final User _user)
    {
        RemoveFragment(FLogIn.class);

        RequestsToTheDataBase.contextToGetDataBase(this).requestTo(new RequestTo<DB>()
        {
            @Override
            public void requestTo(DB request)
            {
                mStateRequestDB = request.addUser(_user);
            }
        });

        if(mStateRequestDB)
            GetResources();
        else
            Toast.makeText(getApplicationContext(), " [Store User] An error has occurred ", Toast.LENGTH_LONG).show();
    }

    @Override
    public void SingUpSuccess()
    {
        PopBackStack();
        Toast.makeText(getApplicationContext(), "The User has been created", Toast.LENGTH_LONG).show();
        RemoveFragment(FSingUp.class);
    }

    @Override
    public void CheckInternet()
    {
        iniApp();
    }

    @Override
    public void SingUp()
    {
        mFragment = CommitFragment.commitFragmentToBack(getSupportFragmentManager(), R.id._contentFragsMApp, FSingUp.class,
                R.anim.enter_animation, R.anim.exit_animation, R.anim.pop_enter, R.anim.pop_exit);
    }

    @Override
    public void LogIn()
    {
        mFragment = CommitFragment.commitFragmentToBack(getSupportFragmentManager(), R.id._contentFragsMApp, FLogIn.class,
                R.anim.enter_animation, R.anim.exit_animation, R.anim.pop_enter, R.anim.pop_exit);
    }
    /**************************/

    private void RemoveFragment(Class _class)
    {
        android.support.v4.app.Fragment _frag = getSupportFragmentManager().findFragmentByTag(_class.getSimpleName());
        if(_frag != null)
            getSupportFragmentManager().beginTransaction().remove(_frag).commit();
    }

    private void PopBackStack()
    {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0)
            for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); i++)
                getSupportFragmentManager().popBackStackImmediate();

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        PopBackStack();
    }

    private void StartGB()
    {
        Intent _userAct = new Intent(this, MainUser.class);
        startActivity(_userAct);
        this.finish();
    }

    private void GetResources()
    {
        GetBE();
    }

    private void CheckUserFromDB()
    {
        RequestsToTheDataBase.contextToGetDataBase(this).requestTo(new RequestTo<DB>()
        {
            @Override
            public void requestTo(DB request)
            {
                StoreResources.setUser(request.getUser());
            }
        });

        if (StoreResources.getUser() != null)
            GetResources();
        else
            mFragment = CommitFragment.commitFragment(getSupportFragmentManager(), R.id._contentFragsMApp, FInitApp.class,
                    R.anim.enter_down_to_up, R.anim.exit_down_to_up);
    }

    private void GetB()
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
                            StartGB();
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

    private void GetBE()
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
                    GetB();
                else
                    Toast.makeText(getApplicationContext(), "[MainApp Events] Something went wrong getting the Events ", Toast.LENGTH_LONG).show();

        }
        else
            Toast.makeText(getApplicationContext(), "[MainApp Events] Something went wrong getting Events: " + response.code() +  " " + response.message(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(Call<Events> call, Throwable t)
    {
        Toast.makeText(getApplicationContext(), "[MainApp Events] Something went wrong getting Events: " + t.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}