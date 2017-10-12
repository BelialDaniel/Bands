package Classes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by BelialDaniel on 8/10/17.
 */

public class Band
{
    @SerializedName("ID")
    private int mBandID = -1;
    @SerializedName("Email")
    private String mEmail = "";
    @SerializedName("Name")
    private String mName = "";
    @SerializedName("City")
    private String mCity = "";

    @SerializedName("Genre")
    private String mGenre = "";
    @SerializedName("Bio")
    private String mBio = "";
    @SerializedName("Price")
    private double mPrice = 0.0;
    @SerializedName("PayPal")
    private String mPayPal = "";
    @SerializedName("Image")
    private String mImage = "";

    @SerializedName("Songs")
    private List<Song> mSongs = null;

    public Band(int _bandID)
    {
        this.mBandID = _bandID;
    }

    public int getBandID()
    {
        return mBandID;
    }

    public String getBandName()
    {
        return mName;
    }

    public String getBandEmail()
    {
        return mEmail;
    }

    public String getGenre()
    {
        return mGenre;
    }

    public String getBio()
    {
        return mBio;
    }

    public double getPrice()
    {
        return mPrice;
    }

    public String getCity()
    {
        return mCity;
    }

    public String getPayPal()
    {
        return mPayPal;
    }

    public String getImage()
    {
        return mImage;
    }

    public List<Song> getSongs()
    {
        return mSongs;
    }
}