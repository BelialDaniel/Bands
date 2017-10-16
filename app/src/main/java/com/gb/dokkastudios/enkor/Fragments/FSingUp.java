package com.gb.dokkastudios.enkor.Fragments;


import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gb.dokkastudios.enkor.R;
import com.google.gson.Gson;

import com.dokkastudios.enkor.fragment.Fragments;
import Classes.MessagesInfo;
import com.dokkastudios.enkor.services.util.RetrofitInstance;
import Classes.User;
import com.dokkastudios.enkor.services.EnkorServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FSingUp extends Fragments implements Callback<MessagesInfo>, View.OnClickListener
{
    private String _name     = "";
    private String _lastName = "";
    private String _email    = "";
    private String _pass     = "";
    private String _city     = "Guadalajara";

    public FSingUp() {}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mSView = inflater.inflate(R.layout.f_singup, container, false);
        mSView.findViewById(R.id._btnMAppSingUp).setOnClickListener(this);
        return mSView;
    }

    @Override
    public void onClick(View view)
    {
        if(view instanceof Button)
            SignUpUser();
    }

    private void SignUpUser()
    {
        _name     = ((TextView) mSView.findViewById(R.id._txtMAppSUName)).getText().toString();
        _email    = ((TextView) mSView.findViewById(R.id._txtMAppSUEmail)).getText().toString();
        _pass     = ((TextView) mSView.findViewById(R.id._txtMAppSUPass)).getText().toString();

        if(!_name.isEmpty() && !_email.isEmpty() && !_pass.isEmpty())
            AddUser(new User(_email, _pass, _name, _lastName, _city, 1));
        else
            Toast.makeText(getContext(), " Please fill out ALL fields ", Toast.LENGTH_LONG).show();
    }

    private void AddUser(User _userG)
    {
        Gson _gsonObj = new Gson();
        String _jsonUsr = _gsonObj.toJson(_userG);

        byte[] _loginBytes = _jsonUsr.getBytes();
        StringBuilder _signUpData = new StringBuilder()
                .append(Base64.encodeToString(_loginBytes, Base64.DEFAULT));

        Log.i("Tag Class", _signUpData.toString());

        Retrofit _retrofit = RetrofitInstance.getRetrofit();

        EnkorServices _serviceGB = _retrofit.create(EnkorServices.class);
        Call<MessagesInfo> _userSignUpData = _serviceGB.AddUser(_signUpData.toString());
        _userSignUpData.enqueue(this);
    }

    @Override
    public void onResponse(Call<MessagesInfo> call, Response<MessagesInfo> _response)
    {
        if(_response.isSuccessful())
        {
            MessagesInfo _messages = _response.body();
            if (_messages != null)
            {
                if (!_messages.getError())
                {
                    ClearTexts();
                    Toast.makeText(getContext(), "Adding User ", Toast.LENGTH_LONG).show();
                    mCallbackMA.SingUpSuccess();
                }
                else
                {
                    if (_messages.getErrorCode() == 23000)
                        Toast.makeText(getContext(), "This email has already been registered", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getContext(), _messages.getErrMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }
        else
        {
            String _eMessage = "";
            if(_response.body() != null)
                _eMessage = _response.body().getErrMessage();
            Toast.makeText(getContext(), "Error code " + _response.code() + " " + _eMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Call<MessagesInfo> call, Throwable _t)
    {
        Toast.makeText(getContext(), "[SignUpUser] Something went wrong: " + _t.getMessage(), Toast.LENGTH_LONG).show();
    }

    private void ClearTexts()
    {
        ((TextView) mSView.findViewById(R.id._txtMAppSUName)).setText(null);
        ((TextView) mSView.findViewById(R.id._txtMAppSUEmail)).setText(null);
        ((TextView) mSView.findViewById(R.id._txtMAppSUPass)).setText(null);
    }
}