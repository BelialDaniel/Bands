package Classes;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BelialDaniel on 8/10/17.
 */

public class User
{
    @SerializedName("ID")
    protected int mUserID = -1;
    @SerializedName("Email")
    private String mEmail = "";
    @SerializedName("Pass")
    private String mPass = "";
    @SerializedName("Name")
    private String mName = "";
    @SerializedName("LastName")
    private String mLastName = "";
    @SerializedName("City")
    private String mCity = "";
    @SerializedName("UserType")
    private int mUserType = 0;

    public User()
    {}

    public User (int _id, String _email, String _name, String _lastName, String _city, int _userType)
    {
        this.mUserID = _id;
        this.mEmail = _email;
        this.mName = _name;
        this.mLastName = _lastName;
        this.mCity = _city;
        this.mUserType = _userType;
    }

    public User(String _email, String _pass, String _name, String _lastName, String _city, int _userType)
    {
        this.mEmail = _email;
        this.mPass = _pass;
        this.mName = _name;
        this.mLastName = _lastName;
        this.mCity = _city;
        this.mUserType = _userType;
    }

    public int getUserID()
    {
        return mUserID;
    }

    public String getEmail()
    {
        return mEmail;
    }

    public String getFirsName()
    {
        return mName;
    }

    public String getLastName()
    {
        return mLastName;
    }

    public String getCity()
    {
        return mCity;
    }

    public int getUserType()
    {
        return mUserType;
    }
}