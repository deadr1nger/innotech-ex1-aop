package ru.innotech.t1.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.innotech.t1.model.entity.ExecutionTimeLog;
import ru.innotech.t1.model.enumeration.MethodType;

import java.util.UUID;

@Repository
public interface AsyncTrackTimeRepository extends R2dbcRepository<ExecutionTimeLog, UUID> {
    @Query("select * from log where log.method_name = :methodName")
    public Flux<ExecutionTimeLog> findByMethodName(String methodName);

    @Query("select * from log where log.method_type = :methodType")
    public Flux<ExecutionTimeLog> findByMethodType(MethodType methodType);
    @Query("select distinct method_name from log")
    public Flux<String> findAllNames();
}
