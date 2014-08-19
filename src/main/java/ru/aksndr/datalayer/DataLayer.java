package ru.aksndr.datalayer;

import ru.aksndr.model.User;

import java.util.Map;

/**
 * Created by aksndr on 11.08.14.
 */
public interface DataLayer {
    public Map<Long, User> findAllUsers();

    public User save(User u);

    public User findById(long id);
}
