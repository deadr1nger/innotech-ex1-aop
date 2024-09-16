package ru.innotech.t1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.innotech.t1.model.entity.ExecutionTimeLog;
import ru.innotech.t1.repository.AsyncTrackTimeRepository;

@RequiredArgsConstructor
@Service
@Slf4j
@Primary
public class AsyncTrackTimeServiceImpl implements AsyncTrackTimeService {

    private final AsyncTrackTimeRepository trackTimeRepository;

    @Override
    public Mono<ExecutionTimeLog> saveTrackTime(ExecutionTimeLog entity) {
        return trackTimeRepository.save(entity).doOnSuccess(savedEntity -> log.info("Entity saved successfully: {}", savedEntity)).doOnError(error -> log.error("Error while saving entity: {}", error));
    }
}
