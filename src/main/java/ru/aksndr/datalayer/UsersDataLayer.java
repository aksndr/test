package ru.aksndr.datalayer;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import ru.aksndr.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aksndr on 11.08.14.
 */
public interface UsersDataLayer {
    public Map<Long,User> getUsersList();
    public User addUser(User u);
    public User getUser(long id);
}
