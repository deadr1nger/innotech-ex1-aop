package ru.innotech.t1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.innotech.t1.annotation.TrackAsyncTime;
import ru.innotech.t1.annotation.TrackTime;


import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@Primary
@RequiredArgsConstructor
public class ExecutionAnyServiceImpl implements ExecutionAnyService {


    @Override
    @TrackTime
    public String executionAny() throws InterruptedException {
        log.info("execution");
        Thread.sleep(1000);
        return "executed";
    }

    @Override
    @Async
    @TrackAsyncTime
    public CompletableFuture<String> executionAnyAsync() throws InterruptedException {
        log.info("execution but async");
        Thread.sleep(1000);
        return CompletableFuture.completedFuture("executed but async");
    }


}
