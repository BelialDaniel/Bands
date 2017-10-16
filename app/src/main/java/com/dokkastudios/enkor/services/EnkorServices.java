package com.dokkastudios.enkor.services;

import Classes.Bands;
import Classes.Events;
import Classes.MessagesInfo;
import Classes.Tickets;
import Classes.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by BelialDaniel on 8/10/17.
 */

public interface EnkorServices
{
    String BASE_URL = "http://dokkastudios.com/";
    String IMAGES_URL = "http://dokkastudios.com/images/";

    /**
     *
     * @return
     */
    @GET("bands")
    Call<Bands> getBands();

    /**
     *
     * @param data
     * @return
     */
    @GET("user/{data}")
    Call<User> getUser(@Path("data") String data);

    /**
     *
     * @return
     */
    @GET("events")
    Call<Events> getEvents();

    /**
     *
     * @param id
     * @return
     */
    @GET("tickets/{id}")
    Call<Tickets> getTickets(@Path("id") String id);

    /**
     *
     * @param data
     * @return
     */
    @GET("adduser/{data}")
    Call<MessagesInfo> AddUser(@Path("data") String data);
}