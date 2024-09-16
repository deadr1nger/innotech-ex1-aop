package ru.innotech.t1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.innotech.t1.model.enumeration.MethodType;
import ru.innotech.t1.service.StatisticService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class StatisticController {


    private final StatisticService statisticService;
    @GetMapping("/get-list-of-methods")
    public Mono<List<String>> getMethodList(){
        return statisticService.getListOfMethods();
    }

    @GetMapping("/avg-time-by-name")
    public Mono<Double> avgExecTimeByName(@RequestParam String name) {
        return statisticService.getAvgTimeByName(name);
    }

    @GetMapping("/total-time-by-name")
    public Mono<Long> totalExecTimeByName(@RequestParam String name) {
        return statisticService.getTotalTimeByName(name);
    }

    @GetMapping("/max-time-by-name")
    public Mono<Long> maxExecTimeByName(@RequestParam String name) {
        return statisticService.getMaxTimeByName(name);
    }

    @GetMapping("/min-time-by-name")
    public Mono<Long> minExecTimeByName(@RequestParam String name) {
        return statisticService.getMinTimeByName(name);
    }

    @GetMapping("/avg-time-by-type")
    public Mono<Double> avgExecTimeByType(@RequestParam MethodType type) {
        return statisticService.getAvgTimeByType(type);
    }

    @GetMapping("/total-time-by-type")
    public Mono<Long> totalExecTimeByType(@RequestParam MethodType type) {
        return statisticService.getTotalTimeByType(type);
    }

    @GetMapping("/max-time-by-type")
    public Mono<Long> maxExecTimeByType(@RequestParam MethodType type) {
        return statisticService.getMaxTimeByType(type);
    }

    @GetMapping("/min-time-by-type")
    public Mono<Long> minExecTimeByType(@RequestParam MethodType type) {
        return statisticService.getMinTimeByType(type);
    }
}
