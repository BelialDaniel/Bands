package com.gb.dokkastudios.enkor.Fragments;


import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gb.dokkastudios.enkor.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import Classes.Event;
import com.dokkastudios.enkor.fragment.Fragments;
import com.dokkastudios.enkor.util.StoreResources;

public class FInfoEvent extends Fragments implements OnMapReadyCallback
{
    private Event _mBandEvent = null;

    private MapView _mMapView = null;
    private GoogleMap _mGoogleMap = null;

    public FInfoEvent() {}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        _mBandEvent = StoreResources.getEvent();
        if (_mBandEvent == null)
            Toast.makeText(getContext(), "Error getting the Eevent ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mSView = inflater.inflate(R.layout.f_info_event, container, false);

        if(_mBandEvent != null)
        {
            ((TextView) mSView.findViewById(R.id._infTxtTitle)).setText(_mBandEvent.getName());
            //((TextView) _mView.findViewById(R.id._infTxtEventName)).setText(_mBandEvent.getBandName());
            ((TextView) mSView.findViewById(R.id._infTxtAddres)).setText(_mBandEvent.getAddres());
            ((TextView) mSView.findViewById(R.id._infTxtDate)).setText(_mBandEvent.getDate());
            ((TextView) mSView.findViewById(R.id._infTxtCity)).setText(_mBandEvent.getCity());
            ((TextView) mSView.findViewById(R.id._infTxtInfo)).setText(_mBandEvent.getInfo());
            ((TextView) mSView.findViewById(R.id._infTxtInfo)).setMovementMethod(new ScrollingMovementMethod());
            ((TextView) mSView.findViewById(R.id._infTxtInfo)).setKeyListener(null);

            InitMap(savedInstanceState);
        }
        return mSView;
    }

    private void InitMap(Bundle savedInstanceState)
    {
        _mMapView = mSView.findViewById(R.id._infMapV);
        _mMapView.onCreate(savedInstanceState);
        _mMapView.onResume();
        _mMapView.getMapAsync(this);

        try
        {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(this.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        _mGoogleMap = googleMap;
        _mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        _mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(20.659698, -103.349609), 16));

        if(_mBandEvent != null)
            if(_mBandEvent.getMarker() != null)
            {
                _mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(_mBandEvent.getMarker().getPosition(), 16));
                _mGoogleMap.addMarker(_mBandEvent.getMarker());
            }

        if (ActivityCompat.checkSelfPermission(this.getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions
                    (
                            this.getActivity(),
                            new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                            1
                    );
        }
        else
        {
            if(!_mGoogleMap.isMyLocationEnabled())
                _mGoogleMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }
}