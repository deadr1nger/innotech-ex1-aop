package ru.innotech.t1.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.innotech.t1.model.entity.ExecutionTimeLog;

@Service
public interface AsyncTrackTimeService {

    public Mono<ExecutionTimeLog> saveTrackTime(ExecutionTimeLog entity);
}
