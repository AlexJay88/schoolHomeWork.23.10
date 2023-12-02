package ru.hogwarts.school.controller;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger;
import java.util.stream.Stream;

public class InfoController {

    Logger logger = (Logger) LoggerFactory.getLogger(InfoController.class);
    @Value("${server.port}")
    private int port;

    @GetMapping("/getPort")
    public Integer getPort() {
        return port;

    }

    @GetMapping("/sum")

    public int getSum() {
        long time = System.currentTimeMillis();


        Stream.
                iterate(1, a -> a + 1)
                .limit(1_000_000)
                .parallel()

                .reduce(0, (a, b) -> a + b);

        time = System.currentTimeMillis() - time;
        System.out.printf("time %d \n", time);
        return (int) time;

    }


}



