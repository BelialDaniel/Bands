package com.gb.dokkastudios.garagebands.AvtivityClasses;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.gb.dokkastudios.garagebands.R;
import com.squareup.picasso.Picasso;

public class InfoEvent extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_info_event);
        setReferences();
    }

    private void setReferences()
    {
        Picasso.with(getApplicationContext())
                .load(R.drawable.no_image)
                .into((ImageView) findViewById(R.id._imageViewInfoEventAct));
    }
}