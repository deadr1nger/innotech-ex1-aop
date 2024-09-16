package ru.innotech.t1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.innotech.t1.model.entity.ExecutionTimeLog;
import ru.innotech.t1.model.enumeration.MethodType;
import ru.innotech.t1.repository.AsyncTrackTimeRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@Primary
@Slf4j
public class StatisticServiceImpl implements StatisticService {
    private final AsyncTrackTimeRepository trackTimeRepository;


    @Override
    public Mono<List<String>> getListOfMethods() {
        return trackTimeRepository.findAllNames().switchIfEmpty(Mono.defer(() -> {
                    log.error("Methods-list may be empty, execute any method");
                    return Mono.error(new NullPointerException("Methods-list may be empty"));
                }))
                .collectList()
                .onErrorResume(e ->
                {
                    log.error("Error while execution: {}", e.getMessage());
                    return Mono.empty();
                });
    }

    @Override
    public Mono<Double> getAvgTimeByName(String name) {
        if (name == null){
            throw new NullPointerException("Name can't be NULL");
        }
        return trackTimeRepository.findByMethodName(name).switchIfEmpty(Mono.defer(() -> {
                    log.error("No result found for method name: {}", name);
                    return Mono.error(new NullPointerException(String.format("No result found for method name: %s", name)));
                }))
                .map(ExecutionTimeLog::getExecutionTime)
                .collectList()
                .map(list -> list.stream()
                        .mapToDouble(Double::valueOf)
                        .average().orElseThrow(() -> new ArithmeticException("Provided numbers may not be Double")))
                .onErrorResume(e ->
                {
                    log.error("Error while execution {}: {}", e.getMessage());
                    return Mono.empty();
                });
    }

    @Override
    public Mono<Long> getMaxTimeByName(String name) {
        if (name == null){
            throw new NullPointerException("Name can't be NULL");
        }
        return trackTimeRepository.findByMethodName(name).switchIfEmpty(Mono.defer(() -> {
                    log.error("No result found for method name: {}", name);
                    return Mono.error(new NullPointerException(String.format("No result found for method name: %s", name)));
                }))
                .map(ExecutionTimeLog::getExecutionTime)
                .collectList()
                .map(any -> any.stream()
                        .mapToLong(Long::longValue)
                        .max().orElse(Long.MIN_VALUE))
                .onErrorResume(e ->
                {
                    log.error("Error while execution: {}", e.getMessage());
                    return Mono.empty();
                });
    }

    @Override
    public Mono<Long> getMinTimeByName(String name) {
        if (name == null){
            throw new NullPointerException("Name can't be NULL");
        }
        return trackTimeRepository.findByMethodName(name).switchIfEmpty(Mono.defer(() -> {
                    log.error("No result found for method name: {}", name);
                    return Mono.error(new NullPointerException(String.format("No result found for method name: %s", name)));
                }))
                .map(ExecutionTimeLog::getExecutionTime)
                .collectList()
                .map(any -> any.stream()
                        .mapToLong(Long::longValue)
                        .min().orElse(Long.MIN_VALUE))
                .onErrorResume(e ->
                {
                    log.error("Error while execution: {}", e.getMessage());
                    return Mono.empty();
                });
    }

    @Override
    public Mono<Long> getTotalTimeByName(String name) {
        if (name == null){
            throw new NullPointerException("Name can't be NULL");
        }
        return trackTimeRepository.findByMethodName(name).switchIfEmpty(Mono.defer(() -> {
                    log.error("No result found for method name: {}", name);
                    return Mono.error(new NullPointerException(String.format("No result found for method name: %s", name)));
                }))
                .map(ExecutionTimeLog::getExecutionTime)
                .collectList().map(any -> any.stream()
                        .mapToLong(Long::longValue)
                        .sum())
                .onErrorResume(e ->
                {
                    log.error("Error while execution: {}", e.getMessage());
                    return Mono.empty();
                });
    }

    @Override
    public Mono<Double> getAvgTimeByType(MethodType type) {
        if (type == null){
            throw new NullPointerException("Type can't be NULL");
        }
        return trackTimeRepository.findByMethodType(type).switchIfEmpty(Mono.defer(() -> {
                    log.error("No result found for method type: {}", type);
                    return Mono.error(new NullPointerException(String.format("No result found for method type: %s", type)));
                }))
                .map(ExecutionTimeLog::getExecutionTime)
                .collectList()
                .map(list -> list.stream().mapToDouble(Double::valueOf)
                        .average()
                        .orElseThrow(() -> new ArithmeticException("Provided numbers may not be Double")))
                .onErrorResume(e ->
                {
                    log.error("Error while execution {}: {}", e.getMessage());
                    return Mono.empty();
                });
    }

    @Override
    public Mono<Long> getTotalTimeByType(MethodType type) {
        if (type == null){
            throw new NullPointerException("Type can't be NULL");
        }
        return trackTimeRepository.findByMethodType(type).switchIfEmpty(Mono.defer(() -> {
                    log.error("No result found for method type: {}", type);
                    return Mono.error(new NullPointerException(String.format("No result found for method type: %s", type)));
                }))
                .map(ExecutionTimeLog::getExecutionTime)
                .collectList()
                .map(any -> any.stream()
                        .mapToLong(Long::longValue)
                        .sum())
                .onErrorResume(e ->
                {
                    log.error("Error while execution: {}", e.getMessage());
                    return Mono.empty();
                });
    }

    @Override
    public Mono<Long> getMaxTimeByType(MethodType type) {
        if (type == null){
            throw new NullPointerException("Type can't be NULL");
        }
        return trackTimeRepository.findByMethodType(type).switchIfEmpty(Mono.defer(() -> {
                    log.error("No result found for method type: {}", type);
                    return Mono.error(new NullPointerException(String.format("No result found for method type: %s", type)));
                }))
                .map(ExecutionTimeLog::getExecutionTime)
                .collectList()
                .map(any -> any.stream()
                        .mapToLong(Long::longValue)
                        .max().orElse(Long.MIN_VALUE))
                .onErrorResume(e ->
                {
                    log.error("Error while execution: {}", e.getMessage());
                    return Mono.empty();
                });
    }

    @Override
    public Mono<Long> getMinTimeByType(MethodType type) {
        if (type == null){
            throw new NullPointerException("Type can't be NULL");
        }
        return trackTimeRepository.findByMethodType(type).switchIfEmpty(Mono.defer(() -> {
                    log.error("No result found for method type: {}", type);
                    return Mono.error(new NullPointerException(String.format("No result found for method type: %s", type)));
                }))
                .map(ExecutionTimeLog::getExecutionTime)
                .collectList()
                .map(any -> any.stream()
                        .mapToLong(Long::longValue)
                        .min().orElse(Long.MIN_VALUE))
                .onErrorResume(e ->
                {
                    log.error("Error while execution: {}", e.getMessage());
                    return Mono.empty();
                });
    }
}
