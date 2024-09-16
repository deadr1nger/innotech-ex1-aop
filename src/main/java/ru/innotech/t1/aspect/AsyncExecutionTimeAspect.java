package ru.innotech.t1.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ru.innotech.t1.model.entity.ExecutionTimeLog;
import ru.innotech.t1.model.enumeration.MethodType;
import ru.innotech.t1.service.AsyncTrackTimeService;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class AsyncExecutionTimeAspect {

    private final AsyncTrackTimeService service;

    @Pointcut("@annotation(ru.innotech.t1.annotation.TrackAsyncTime)")
    public void logExecutionTimeJoinPoint() {
    }

    @Around("@annotation(ru.innotech.t1.annotation.TrackAsyncTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) {
        return CompletableFuture.runAsync(() -> {
            long startTime = System.currentTimeMillis();
            try {
                joinPoint.proceed();
                long executionTime = System.currentTimeMillis() - startTime;
                service.saveTrackTime(new ExecutionTimeLog(UUID.randomUUID(), joinPoint.getSignature().getName(), MethodType.ASYNC, executionTime, true)).subscribe();
            } catch (Throwable e) {
                log.error("Exception while during aspect: ", e);
            }
        });
    }
}

