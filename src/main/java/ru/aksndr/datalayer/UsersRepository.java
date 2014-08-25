package ru.aksndr.datalayer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
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

    public User findByLogin(String login);

    @Query("SELECT flat.id FROM Flat flat where flat = " +
            "(SELECT user.flat FROM User user WHERE LOWER(user.login) = LOWER(:login))")
    public Long findFlatidByLogin(@Param("login") String login);

    @Query("SELECT u FROM User u WHERE u.flat = " +
            "(select f from Flat f where f.flatnum = :flatnum " +
            "AND f.house = (SELECT h FROM House h where LOWER(h.address) = LOWER(:address))) ")
    public User findByFlatNumAndHouseAddress(@Param("flatnum") String flatnum, @Param("address") String address);

//    @Query("SELECT p FROM Person p WHERE LOWER(p.lastName) = LOWER(:lastName)")
//    public List<Person> find(@Param("lastName") String lastName);
}
