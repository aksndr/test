package ru.aksndr.datalayer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.aksndr.model.CounterType;

/**
 * Created by aksndr on 21.08.14.
 */
@Repository
public interface CounterTypesRepository extends CrudRepository<CounterType, Long> {

    @Query("SELECT DISTINCT counterType FROM CounterType counterType WHERE counterType.typeName = :typeName")
    public CounterType findByTypeName(@Param("typeName") String typeName);
}