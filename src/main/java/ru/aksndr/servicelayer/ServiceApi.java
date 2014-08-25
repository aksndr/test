package ru.aksndr.servicelayer;

import retrofit.http.*;
import ru.aksndr.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by aksndr on 11.08.14.
 */
public interface ServiceApi {
    public static final String USERS_PATH = "/users";
    public static final String USERS_COUNTERS_PATH = "/user/counters";
    public static final String ADD_USER_PATH = "/adduser";
    public static final String ADD_HOUSE_PATH = "/addhouse";
    public static final String GET_HOUSE_PATH = "/houses";
    public static final String ADD_NEW_USER_PATH = "/addnewuser";
    public static final String ADD_FLAT_PATH = "/addflat";
    public static final String GET_FLATS_PATH = "/houses/{houseId}/flats";
    public static final String ADD_FLAT_TO_HOUSE_PATH = "/houses/addflat}";
    public static final String ADD_COUNTERTYPE_PATH = "/add_counter_type";
    public static final String ADD_COUNTER_PATH = "/{flatId}/addcounter";
    public static final String GET_COUNTER_PATH = "/counters/{sn}";
    public static final String USER_PATH = "/{id}";

    @GET(USERS_PATH)
    public ArrayList<User> getUsersList();

    @POST(ADD_USER_PATH)
    public User addUser(@Body User u);

    @POST(ADD_HOUSE_PATH)
    public House addHouse(@Body House h);

    @GET(USER_PATH)
    public User getUser(@Path("id") Long id);

    @GET(GET_COUNTER_PATH)
    public Counter getCounter(@Path("sn") Long sn);

    @GET(USERS_COUNTERS_PATH)
    public Set<Counter> getUserCountersList(String login);

    @FormUrlEncoded
    @POST(ADD_FLAT_TO_HOUSE_PATH)
    public Flat addFlat(@Field("houseId") Long houseId, @Field("flatnum") String flatnum);

    @GET(GET_FLATS_PATH)
    public ArrayList<Flat> getHouseFlatsList(@Path("houseId") Long houseId); //, String address

    @POST(ADD_COUNTERTYPE_PATH)
    public CounterType addCounterType(@Body CounterType ct);

    @POST(ADD_COUNTER_PATH)
    public Counter addCounter(@Path("flatId") long flatId, @Body Counter c);

    @Multipart
    @POST(ADD_NEW_USER_PATH)
    public User addNewUser(@Part("u") User u, @Part("f") Flat f, @Part("h") House h);

    @FormUrlEncoded
    @POST(GET_HOUSE_PATH)
    public List<House> getHouseByAddress(@Field("address") String address);
}
