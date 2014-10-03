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
    public static final String GET_ADDRESSES = "/addresses";
    public static final String ADD_NEW_USER_PATH = "/addnewuser";
    public static final String ADD_FLAT_PATH = "/addflat";
    public static final String GET_FLATS_PATH = "/houses/{houseId}/flats";
    public static final String ADD_RECORD_PATH = "/counters/record/add";
    public static final String GET_FLAT_PATH = "/flats";
    public static final String ADD_FLAT_TO_HOUSE_PATH = "/houses/addflat}";
    public static final String GET_FLAT_COUNTERS_PATH = "/{flatId}/counters";
    public static final String GET_FLAT_COUNTER_PATH = "/{flatId}/counter";
    public static final String GET_COUNTERTYPES_PATH = "/countertypes";
    public static final String GET_COUNTERTYPE_PATH = "/countertype";
    public static final String GET_ALL_COUNTERS_PATH = "/counters";
    public static final String ADD_COUNTERTYPE_PATH = "/countertype/add";
    public static final String ADD_COUNTER_PATH = "/{flatId}/counter/add";
    public static final String GET_COUNTER_PATH = "/counters/{sn}";
    public static final String GET_COUNTER_RECORDS_PATH = "/counters/{sn}/records";
    public static final String GET_FLATS_COUNTERS_RECORDS_PATH = "/{flatId}/counters/records";
    public static final String USER_PATH = USERS_PATH + "/{id}";

    @GET(USERS_PATH)
    public ArrayList<User> getUsersList();

    @POST(ADD_USER_PATH)
    public User addUser(@Body User u);

    @FormUrlEncoded
    @POST(ADD_HOUSE_PATH)
    public House addHouse(@Field("address") String address);

    @GET(USER_PATH)
    public User getUser(@Path("id") Long id);

    @GET(GET_COUNTER_PATH)
    public Counter getCounter(@Path("sn") Long sn);

    @GET(GET_COUNTER_RECORDS_PATH)
    public Counter getCounterRecords(@Path("sn") Long sn);

    @GET(USERS_COUNTERS_PATH)
    public Set<Counter> getUserCountersList(@Query("login") String login);

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

    @GET(GET_HOUSE_PATH)
    public List<House> getHouseByAddress(@Query("address") String address);

    @GET(GET_ADDRESSES)
    public List<String> getHousesAddresses(@Query("address") String address);

    @GET(GET_FLAT_PATH)
    public Long findFlatIdByLogin(@Query("login") String login);

    @GET(GET_COUNTERTYPES_PATH)
    public Set<String> getAllCounterTypes();

    @GET(GET_COUNTERTYPE_PATH)
    public CounterType getCounterTypeByTypeName(@Query("typename") String typename);

    @GET(GET_ALL_COUNTERS_PATH)
    public Set<Counter> getAllCounters();

    @POST(ADD_RECORD_PATH)
    public Record addRecord(@Body Record record);

    @GET(GET_FLAT_COUNTERS_PATH)
    Set<Counter> getCountersByFlatId(@Path("flatId") Long flatid);

    @GET(GET_FLATS_COUNTERS_RECORDS_PATH)
    Set<Record> getCountersRecordsByFlatIdAndDatesInterval(@Path("flatId") Long flatid, @Query("dateStart") String dateStart, @Query("dateEnd") String dateEnd);

    @GET(GET_FLAT_COUNTER_PATH)
    Counter getUserCounterByType(@Path("flatId") Long flatid, @Query("type") String type);

}
