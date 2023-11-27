package ru.hogwarts.school.controller;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger;

public class InfoController {

    Logger logger = (Logger) LoggerFactory.getLogger(InfoController.class);
    @Value("${server.port}")
    private int port;

    @GetMapping("/getPort")
    public Integer getPort() {
        return port;
    }

}



