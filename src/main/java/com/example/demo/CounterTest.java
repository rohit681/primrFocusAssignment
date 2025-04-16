package com.example.demo;

import com.example.demo.services.CounterService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class CounterTest {

    @Autowired
    private CounterService counterService;

    @PostConstruct
    public void runThreads() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executor.submit(() -> {
                long id = counterService.getNextSequence("userCounter");
                System.out.println("Generated ID: " + id);
            });
        }
        executor.shutdown();
    }
}
