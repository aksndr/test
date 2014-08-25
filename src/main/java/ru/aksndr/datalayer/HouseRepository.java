package ru.aksndr.datalayer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.aksndr.model.House;

import java.util.List;

/**
 * Created by aksndr on 19.08.14.
 */
@Repository
public interface HouseRepository extends CrudRepository<House, Long> {
    @Query("SELECT DISTINCT house FROM House house WHERE house.address LIKE :address%")
    public List<House> findByAddress(@Param("address") String address);
}
