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
//@SuppressWarnings("JpaQlInspection")
@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
    public Map<Long, User> findByFirstname(String firstname);

//    public User saveUser(User u);

    public List<User> findById(long id);

    @Query("SELECT u FROM User u WHERE u.flatid = " +
            "(select f.id from Flat f where f.flatnum = :flatnum " +
            "AND f.houseid = (SELECT h.id FROM House h where LOWER(h.address) = LOWER(:address))) ")
    public User findByFlatNumAndHouseAddr(@Param("flatnum") String flatnum, @Param("address") String address);

//    @Query("SELECT p FROM Person p WHERE LOWER(p.lastName) = LOWER(:lastName)")
//    public List<Person> find(@Param("lastName") String lastName);
}
