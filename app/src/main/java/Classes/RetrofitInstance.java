package Classes;

import Interfaces.GarageBandsServices;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by BelialDaniel on 8/10/17.
 */

public class RetrofitInstance
{
    private static final RetrofitInstance INSTANCE = new RetrofitInstance();
    private static Retrofit mRetrofit = null;

    private RetrofitInstance() {}

    /**
     *
     * @return
     */
    static public Retrofit getRetrofit()
    {
        if(INSTANCE.mRetrofit == null)
            INSTANCE.mRetrofit = new Retrofit.Builder()
                    .baseUrl(GarageBandsServices.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        return INSTANCE.mRetrofit;
    }
}