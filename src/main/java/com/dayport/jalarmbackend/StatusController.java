package com.dayport.jalarmbackend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class StatusController {

    private static final String template = "hello, %s";
    private final AtomicLong counter = new AtomicLong();

    private long millisSinceLastStatus = Instant.now().getEpochSecond();


    @GetMapping("/status")
    public Status status(@RequestParam(value = "name", defaultValue = "World") String name) {
        System.out.print("Last packet was: ");
        System.out.print(Instant.now().getEpochSecond() - millisSinceLastStatus);
        System.out.print(" ");
        System.out.println("Seconds ago.");
        millisSinceLastStatus = Instant.now().getEpochSecond();

        return new Status(counter.incrementAndGet(), String.format(template, name));
    }

}
