package ru.innotech.t1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.innotech.t1.service.ExecutionAnyService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/execution")
public class ExecutionController {

    private final ExecutionAnyService executionAnyService;

    @GetMapping("/sync")
    public String executeAny() throws InterruptedException {
        return executionAnyService.executionAny();
    }

    @GetMapping("/async")
    public CompletableFuture<String> executeAnyAsync() throws InterruptedException {
        return executionAnyService.executionAnyAsync();
    }
}
