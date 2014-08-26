package ru.aksndr.datalayer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.aksndr.model.Record;

/**
 * User: a.arzamastsev Date: 26.08.14 Time: 16:43
 */
@Repository
public interface RecordsRepository extends CrudRepository<Record, Long> {
}
