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

    @Query("SELECT DISTINCT flat FROM Flat flat WHERE flat.flatNum = :flatNum " +
            "and EXISTS (SELECT house FROM House house where house.address = :address)")
    public List<Flat> findByFlatNumAndAddress(@Param("flatNum") String flatNum, @Param("address") String address);

    public List<Flat> findByHouseId(Long houseId);


}
