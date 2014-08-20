package ru.aksndr.datalayer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.aksndr.model.Flat;

import java.util.List;

/**
 * Created by aksndr on 19.08.14.
 */
@Repository
public interface FlatsRepository extends CrudRepository<Flat, Long> {
    public List<Flat> findByFlatnum(String flatnum);
}
