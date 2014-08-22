package ru.aksndr.datalayer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.aksndr.model.Counter;

import java.util.Set;

/**
 * Created by aksndr on 21.08.14.
 */
@Repository
public interface CountersRepository extends CrudRepository<Counter, Long> {

    @Query("SELECT DISTINCT counter FROM Counter counter " +
            "left join fetch counter.type " +
            "WHERE counter.sn = :sn")
    public Counter getCounterBySn(@Param("sn") Long sn);

    public Set<Counter> getCountersByFlatId(Long flatId);
}
