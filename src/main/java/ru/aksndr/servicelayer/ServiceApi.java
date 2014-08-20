package ru.aksndr.servicelayer;

import retrofit.http.*;
import ru.aksndr.model.Flat;
import ru.aksndr.model.House;
import ru.aksndr.model.User;

import java.util.ArrayList;

/**
 * Created by aksndr on 11.08.14.
 */
public interface ServiceApi {
    public static final String USERS_PATH = "/users";
    public static final String ADD_USER_PATH = "/adduser";
    public static final String ADD_NEW_USER_PATH = "/addnewuser";
    public static final String ADD_FLAT_PATH = "/addflat";
    public static final String USER_PATH = "/{id}";

    @GET(USERS_PATH)
    public ArrayList<User> getUsersList();

    @POST(ADD_USER_PATH)
    public User addUser(@Body User u);

    @GET(USER_PATH)
    public User getUser(@Path("id") long id);

    @POST(ADD_FLAT_PATH)
    public Flat addFlat(@Body Flat f);

    @Multipart
    @POST(ADD_NEW_USER_PATH)
    public User addNewUser(@Part("u") User u, @Part("f") Flat f, @Part("h") House h);
}
