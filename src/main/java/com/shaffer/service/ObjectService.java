package com.shaffer.service;

import com.shaffer.dao.ObjectDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjectService {
    private static final Logger logger = LoggerFactory.getLogger(ObjectService.class);

    @Autowired
    private ObjectDao dao;

    public Object get(String id) {
        logger.info(id);
        return dao.get(id);
    }

    public void refresh(String id) {
        logger.info(id);
        dao.refresh(id);
    }

    public void refreshAll() {

    }
}
