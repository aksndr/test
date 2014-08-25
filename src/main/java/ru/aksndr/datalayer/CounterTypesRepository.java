package ru.aksndr.datalayer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.aksndr.model.CounterType;

import java.util.Set;

/**
 * Created by aksndr on 21.08.14.
 */
@Repository
public interface CounterTypesRepository extends CrudRepository<CounterType, Long> {

    @Query("SELECT DISTINCT counterType FROM CounterType counterType WHERE counterType.typename = :typename")
    public CounterType findByTypeName(@Param("typename") String typename);

    @Query("SELECT DISTINCT counterType.typename FROM CounterType counterType")
    Set<String> getAllCounterTypes();
}