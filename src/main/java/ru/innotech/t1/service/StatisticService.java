package ru.innotech.t1.service;

import reactor.core.publisher.Mono;
import ru.innotech.t1.model.enumeration.MethodType;

import java.util.List;

public interface StatisticService {

    public Mono<List<String>> getListOfMethods();

    public Mono<Double> getAvgTimeByName(String name);

    public Mono<Long> getTotalTimeByName(String name);

    public Mono<Long> getMaxTimeByName(String name);

    public Mono<Long> getMinTimeByName(String name);

    public Mono<Double> getAvgTimeByType(MethodType type);

    public Mono<Long> getTotalTimeByType(MethodType type);

    public Mono<Long> getMaxTimeByType(MethodType type);

    public Mono<Long> getMinTimeByType(MethodType type);
}
