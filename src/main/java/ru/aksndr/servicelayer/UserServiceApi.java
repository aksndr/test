package ru.aksndr.servicelayer;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import ru.aksndr.model.User;

import java.util.ArrayList;

/**
 * Created by aksndr on 11.08.14.
 */
public interface UserServiceApi {
    public static final String USERS_PATH = "/users";
    public static final String ADD_USER_PATH = "/add";
    public static final String USER_PATH = "/{id}";

    @GET(USERS_PATH)
    public ArrayList<User> getUsersList();

    @POST(ADD_USER_PATH)
    public User addUser(@Body User u);

    @GET(USER_PATH)
    public User getUser(@Path("id") long id);
}
