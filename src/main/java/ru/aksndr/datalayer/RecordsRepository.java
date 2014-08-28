package ru.aksndr.datalayer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.aksndr.model.Counter;
import ru.aksndr.model.Record;

/**
 * User: a.arzamastsev Date: 26.08.14 Time: 16:43
 */
@Repository
public interface RecordsRepository extends CrudRepository<Record, Long> {
    Iterable<Record> findByCounter(Counter counter);

    @Query("SELECT DISTINCT record FROM Record record WHERE record.counter IN " +
            "(:counters)")
//            "(SELECT DISTINCT counter FROM Counter counter WHERE counter.sn IN (:counterSns))")
    Iterable<Record> findByCountersSns(@Param("counters") Iterable<Counter> counters);
//    public Record findByCounterAndDate(Counter counter, DateTime dateTime);
}
