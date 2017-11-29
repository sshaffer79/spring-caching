package com.shaffer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheController {
    private static final Logger logger = LoggerFactory.getLogger(CacheController.class);

    @RequestMapping(value ="/{id}", method= RequestMethod.GET)
    public String get(@PathVariable("id") String id) {
        logger.info(id);
        return id;
    }

    @RequestMapping(value = "refresh/{id}", method= RequestMethod.PUT)
    public String refresh(@PathVariable("id") String id) {
        logger.info(id);
        return id;
    }
}
