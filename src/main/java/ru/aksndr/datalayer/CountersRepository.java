package ru.aksndr.datalayer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.aksndr.model.Counter;

/**
 * Created by aksndr on 21.08.14.
 */
@Repository
public interface CountersRepository extends CrudRepository<Counter, Long> {
}
