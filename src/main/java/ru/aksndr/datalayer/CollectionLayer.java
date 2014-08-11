package ru.aksndr.datalayer;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.aksndr.model.User;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by aksndr on 11.08.14.
 */
@Component
public class CollectionLayer implements UsersDataLayer{
    private Map<Long, User> users;
    private AtomicLong counter;

    public CollectionLayer(){
        users = new HashMap<Long, User>();
        counter = new AtomicLong(10);
    }

    public static CollectionLayer get(){
        return new CollectionLayer();
    }

    @Override
    public Map<Long, User> getUsersList() {
        return users;
    }

    @Override
    public User addUser(User u) {
        Long id = counter.incrementAndGet();
        u.setId(id);
        users.put(id,u);
        return u;
    }

    @Override
    public User getUser(long id) {
        User user = users.get(id);
        return user;
    }
}
