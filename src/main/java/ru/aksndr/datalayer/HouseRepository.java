package ru.aksndr.datalayer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.aksndr.model.House;

import java.util.List;

/**
 * Created by aksndr on 19.08.14.
 */
@Repository
public interface HouseRepository extends CrudRepository<House, Long> {
    public List<House> findByAddress(String address);
}
