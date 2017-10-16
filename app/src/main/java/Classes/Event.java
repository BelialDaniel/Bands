package Classes;

import com.gb.dokkastudios.enkor.R;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.annotations.SerializedName;

/**
 * Created by BelialDaniel on 8/10/17.
 */

public class Event
{
    @SerializedName("BandID")
    private int mBandID = -1;
    @SerializedName("BandName")
    private String mBandName = "";

    @SerializedName("EventID")
    private int mEventID = -1;
    @SerializedName("Name")
    private String mName = "";
    @SerializedName("Info")
    private String mInfo = "";
    @SerializedName("Date")
    private String mDate = "";
    @SerializedName("Address")
    private String mAddress = "";
    @SerializedName("City")
    private String mCity = "";
    @SerializedName("State")
    private String mState = "";
    @SerializedName("Image")
    private String mImage = "";

    private MarkerOptions marker = null;

    public Event(String _bandName, String _name, String _info, String _date, String _address, String _city)
    {
        this.mName = _name;
        this.mBandName = _bandName;
        this.mInfo = _info;
        this.mDate = _date;
        this.mAddress = _address;
        this.mCity = _city;
    }

    public String getBandName()
    {
        return mBandName;
    }

    public int getBandID()
    {
        return mBandID;
    }

    public String getDate()
    {
        return mDate;
    }

    public String getAddres()
    {
        return mAddress;
    }

    public String getCity()
    {
        return mCity;
    }

    public String getState()
    {
        return mState;
    }

    public String getImage()
    {
        return mImage;
    }

    public int getID()
    {
        return mEventID;
    }

    public String getName()
    {
        return mName;
    }

    public String getInfo()
    {
        return mInfo;
    }

    public MarkerOptions getMarker()
    {
        return marker;
    }

    public void setMarker(MarkerOptions _marker)
    {
        this.marker = _marker;
        this.marker.title(this.mName);
        this.marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_place_black_s));
        this.marker.snippet("Click for more mInfo...");
    }
}