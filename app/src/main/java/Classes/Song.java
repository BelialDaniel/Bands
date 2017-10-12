package Classes;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BelialDaniel on 9/5/17.
 */

public class Song
{
    @SerializedName("ID")
    private int mSongID = -1;
    @SerializedName("SongName")
    private String mSongName = "";
    @SerializedName("SongInfo")
    private String mSongInfo = "";

    public int getID()
    {
        return mSongID;
    }

    public String getSongName()
    {
        return mSongName;
    }

    public String getSongInfo()
    {
        return mSongInfo;
    }
}