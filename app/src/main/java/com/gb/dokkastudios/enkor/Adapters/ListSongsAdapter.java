package com.gb.dokkastudios.enkor.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gb.dokkastudios.enkor.Holders.SongHolder;
import com.gb.dokkastudios.enkor.R;

import java.util.List;

import Classes.Song;

public class ListSongsAdapter extends RecyclerView.Adapter<SongHolder>
{
    private final List<Song> _mSongs;
    private View _mView = null;

    public ListSongsAdapter(List<Song> _songs, Object listener)
    {
        _mSongs = _songs;
    }

    @Override
    public SongHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        _mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.f_song, parent, false);
        return new SongHolder(_mView);
    }

    @Override
    public void onBindViewHolder(SongHolder holder, int position)
    {
        holder._mSong = _mSongs.get(position);
        holder._mIdView.setText(_mSongs.get(position).getSongInfo());
        holder._mContentView.setText(_mSongs.get(position).getSongName());

        holder._mView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /*if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }*/
                Toast.makeText(_mView.getContext(), "Song Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount()
    {
        return _mSongs.size();
    }
}