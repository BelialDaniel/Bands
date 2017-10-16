package com.gb.dokkastudios.enkor.Fragments;

import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gb.dokkastudios.enkor.R;
import com.google.gson.Gson;

import com.dokkastudios.enkor.fragment.Fragments;
import com.dokkastudios.enkor.services.util.RetrofitInstance;
import Classes.User;
import com.dokkastudios.enkor.services.EnkorServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FLogIn extends Fragments implements Callback<User>
{
    public FLogIn() {}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mSView = inflater.inflate(R.layout.f_login, container, false);

        (mSView.findViewById(R.id._btnMAppLogIn)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                LogIn(((TextView) mSView.findViewById(R.id._txtMAppLIEmail)).getText().toString(),
                        ((TextView) mSView.findViewById(R.id._txtMAppLIPass)).getText().toString());
            }
        });

        return mSView;
    }

    private void LogIn(String _email, String _pass)
    {
        mSView.findViewById(R.id._btnMAppLogIn).setEnabled(false);
        getUser(_email, _pass);
    }

    private void getUser(String _email, String _pass)
    {
        User _usrG = new User(_email, _pass, "", "", "", 1);
        Gson _gsonObj = new Gson();
        String _jsonUsr = _gsonObj.toJson(_usrG);

        byte[] _loginBytes = _jsonUsr.getBytes();
        StringBuilder _loginData = new StringBuilder()
                .append(Base64.encodeToString(_loginBytes, Base64.DEFAULT));

        Retrofit _retrofit = RetrofitInstance.getRetrofit();
        EnkorServices _serviceGB = _retrofit.create(EnkorServices.class);
        Call<User> _userReq = _serviceGB.getUser(_loginData.toString());
        _userReq.enqueue(this);
    }

    @Override
    public void onResponse(Call<User> call, Response<User> _response)
    {

        if(_response.isSuccessful())
        {
            mCallbackMA.LogInSuccess(_response.body());
        }
        else
        {
            if(_response.code() == 404)
                Toast.makeText(getContext(), " Email or Password may be wrong. ", Toast.LENGTH_LONG).show();
            mSView.findViewById(R.id._btnMAppLogIn).setEnabled(true);
        }
    }

    @Override
    public void onFailure(Call<User> call, Throwable t)
    {

        Toast.makeText(getContext(), " An error has occurred " + t.getStackTrace().toString(), Toast.LENGTH_LONG).show();
        mSView.findViewById(R.id._btnMAppLogIn).setEnabled(true);
    }
}