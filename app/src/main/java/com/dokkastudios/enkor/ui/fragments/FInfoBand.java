package com.dokkastudios.enkor.ui.fragments;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dokkastudios.enkor.ui.adapters.ListSongsAdapter;
import com.gb.dokkastudios.enkor.R;

import Classes.Band;
import com.dokkastudios.enkor.fragment.Fragments;

import com.dokkastudios.enkor.util.StoreResources;

public class FInfoBand extends Fragments implements View.OnClickListener
{
    private Band _mBand = null;

    private ViewGroup _mContainer = null;
    private RecyclerView _mListSongsLay = null;
    private LayoutInflater _mInflater = null;

    public FInfoBand() {}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        _mBand = StoreResources.getBand();
        if (_mBand == null)
            Toast.makeText(getContext(), "Error getting the band ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mSView = inflater.inflate(R.layout.f_info_band, container, false);
        _mInflater = inflater;
        _mContainer = container;

        if(_mBand != null)
        {
            ((TextView) mSView.findViewById(R.id._txtInfoBandNameFrag)).setText(_mBand.getBandName());
            ((TextView) mSView.findViewById(R.id._txtInfoGenreBandFrag)).setText(_mBand.getGenre());
            ((TextView) mSView.findViewById(R.id._txtInfoUbicationBandFrag)).setText(_mBand.getCity());

            String _price = _mBand.getPrice() + " $";
            ((TextView) mSView.findViewById(R.id._txtInfoBandPriceFrag)).setText(_price);

            TextView _txtBio = mSView.findViewById(R.id._txtInfoBioBandFrag);
            _txtBio.setText(_mBand.getBio());
            _txtBio.setMovementMethod(new ScrollingMovementMethod());
            _txtBio.setKeyListener(null);

            mSView.findViewById(R.id._btnInfoBandEventsFrag).setOnClickListener(this);
        }
        return mSView;
    }

    private void AssignSongs()
    {
        _mListSongsLay = (RecyclerView) _mInflater.inflate(R.layout.f_list_songs, _mContainer, false);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setAutoMeasureEnabled(true);

        _mListSongsLay.setLayoutManager(layoutManager);//new LinearLayoutManager(getContext()));
        _mListSongsLay.setHasFixedSize(true);
        _mListSongsLay.setNestedScrollingEnabled(false);
        _mListSongsLay.setAdapter(new ListSongsAdapter(_mBand.getSongs(), new Object()));
    }

    @Override
    public void onClick(View view)
    {
        if(view instanceof Button)
            ContractBtnClick();
    }

    private void ContractBtnClick()
    {
        Toast.makeText(this.getContext(), "Button contract pressed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }
}