package ru.aksndr.datalayer;

import ru.aksndr.model.User;

import java.util.Map;

/**
 * Created by aksndr on 11.08.14.
 */
public interface DataLayer {
    public Map<Long, User> getUsersList();

    public User addUser(User u);

    public User getUser(long id);
}
