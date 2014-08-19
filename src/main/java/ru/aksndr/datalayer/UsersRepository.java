package ru.aksndr.datalayer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.aksndr.model.User;

import java.util.List;
import java.util.Map;

/**
 * User: a.arzamastsev Date: 19.08.14 Time: 8:19
 */
@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
    public Map<Long, User> findByFirstname(String firstname);

//    public User saveUser(User u);

    public List<User> findById(long id);
}
