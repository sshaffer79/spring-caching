package com.shaffer.controller;

import com.shaffer.model.Object;
import com.shaffer.service.ObjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
public class CacheController {
    private static final Logger logger = LoggerFactory.getLogger(CacheController.class);

    @Autowired
    private ObjectService service;

    @RequestMapping(value ="/{id}", method= RequestMethod.GET)
    @ResponseBody
    public Object getById(@PathVariable("id") String id) {
        logger.info(id);
        return service.get(id);
    }

    @RequestMapping(value = "refresh/{id}", method= RequestMethod.PUT)
    public String refresh(@PathVariable("id") String id) {
        logger.info(id);
        service.refresh(id);
        return "success";
    }
}
