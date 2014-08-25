package ru.aksndr.datalayer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.aksndr.model.Flat;

import java.util.List;

/**
 * Created by aksndr on 19.08.14.
 */
@Repository
public interface FlatsRepository extends CrudRepository<Flat, Long> {

//    @Query("SELECT DISTINCT flat FROM Flat flat WHERE flat.flatNum = :flatNum ORDER BY flat.flatNum")
//    public List<Flat> findByFlatNum(@Param("flatNum") String flatNum);

    @Query("SELECT DISTINCT flat FROM Flat flat WHERE flat.flatnum = :flatnum " +
            "and EXISTS (SELECT house FROM House house where house.address = :address)")
    public List<Flat> findByFlatNumAndAddress(@Param("flatnum") String flatnum, @Param("address") String address);

    @Query("SELECT DISTINCT flat FROM Flat flat WHERE flat.house = " +
            "(SELECT house FROM House house WHERE house.id = :houseid)")
    public List<Flat> findByHouseid(@Param("houseid") Long houseid);


}
