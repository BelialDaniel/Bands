package com.dokkastudios.enkore.ui.fragments;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gb.dokkastudios.enkor.R;

import com.dokkastudios.enkore.fragment.Fragments;
import com.dokkastudios.enkore.services.EnkorServices;
import com.dokkastudios.enkore.services.util.RetrofitInstance;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import Classes.Event;
import Classes.Events;

import com.dokkastudios.enkore.util.StoreResources;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FMap extends Fragments implements OnMapReadyCallback, Callback<Events>, GoogleMap.OnMarkerClickListener
{
    private MapView _mMapView = null;
    private GoogleMap _mGoogleMap = null;

    public FMap() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mSView = inflater.inflate(R.layout.f_map, container, false);
        return mSView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        InitMap(savedInstanceState);
    }

    private void InitMap(Bundle savedInstanceState)
    {
        _mMapView = mSView.findViewById(R.id._mapBands);
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

    private void BandsEventsFromDB()
    {
        Retrofit _retrofit = RetrofitInstance.getRetrofit();
        EnkorServices __serviceGB = _retrofit.create(EnkorServices.class);
        Call<Events> _bandsEvtsReq = __serviceGB.getEvents();
        _bandsEvtsReq.enqueue(this);
    }

    private void DecodeAddresses()
    {
        Geocoder _geo = new Geocoder(this.getContext());
        try
        {
            for(int i = 0; i < StoreResources.getEvents().getEvents().size(); i++)
            {
                List<Address> _listAddres = _geo.getFromLocationName(StoreResources.getEvents().getEvents().get(i).getAddres(), 5);
                if(_listAddres.size() > 0)
                    StoreResources.getEvents().getEvents().get(i).setMarker(new MarkerOptions()
                            .position(new LatLng(_listAddres.get(0).getLatitude(), _listAddres.get(0).getLongitude())));
            }

            AddMarkers();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            mSView.findViewById(R.id._progBLoadMapBands).setVisibility(View.INVISIBLE);
            mSView.findViewById(R.id._txtMapVLadEvents).setVisibility(View.INVISIBLE);
        }
    }

    private void AddMarkers()
    {
        for(Event _event : StoreResources.getEvents().getEvents())
            _mGoogleMap.addMarker(_event.getMarker());

        mSView.findViewById(R.id._progBLoadMapBands).setVisibility(View.INVISIBLE);
        mSView.findViewById(R.id._txtMapVLadEvents).setVisibility(View.INVISIBLE);
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        _mGoogleMap = googleMap;
        _mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        _mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(20.659698, -103.349609), 16));

        InitMapCallBack();

        if (ActivityCompat.checkSelfPermission(this.getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
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

        if(StoreResources.getEvents() != null)
            DecodeAddresses();
        else
            BandsEventsFromDB();
    }

    private void InitMapCallBack()
    {
        _mGoogleMap.setOnMarkerClickListener(this);

        _mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener()
        {
            @Override
            public void onMapClick(LatLng latLng)
            {
                OnMapClick();
            }
        });

        _mGoogleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener()
        {
            @Override
            public void onCameraMove()
            {
                OnCameraMove();
            }
        });
    }

    private void OnMapClick()
    {
        mOnMapEventClick.onMapClicked();
    }

    private void OnCameraMove()
    {
        mOnMapEventClick.onMapClicked();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        _mMapView.onResume();
    }

    @Override
    public void onResponse(Call<Events> call, Response<Events> _response)
    {
        if(_response.isSuccessful())
        {
            StoreResources.setEvents(_response.body());
            if(StoreResources.getEvents() != null)
                if(StoreResources.getEvents().getEvents() != null)
                    DecodeAddresses();
        }
        else
            Toast.makeText(getContext(), "[Events Map] Something went wrong : " + _response.code(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(Call<Events> call, Throwable _t)
    {
        Toast.makeText(getContext(), "[Events" +
                " Map] Something went wrong =O : " + _t.fillInStackTrace(), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onMarkerClick(Marker marker)
    {
        if(StoreResources.getEvents() != null)
        {
            for (Event _event : StoreResources.getEvents().getEvents())
                if (_event.getMarker() != null)
                {
                    if (marker.getTitle().equals(_event.getMarker().getTitle()))
                        mOnMapEventClick.onMarkerClicked(_event);
                }
        }
        else
            mOnMapEventClick.onMarkerClicked(StoreResources.getEvents().getEvents().get(0));
        return true;
    }
}