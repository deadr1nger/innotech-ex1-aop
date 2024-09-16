package ru.innotech.t1.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public interface ExecutionAnyService {


    public String executionAny() throws InterruptedException;

    public CompletableFuture<String> executionAnyAsync() throws InterruptedException;
}
