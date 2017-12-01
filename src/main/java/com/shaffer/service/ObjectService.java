package com.shaffer.service;

import com.shaffer.dao.ObjectDao;
import com.shaffer.dao.s3.S3Dao;
import com.shaffer.model.Object;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ObjectService {
    private static final Logger logger = LoggerFactory.getLogger(ObjectService.class);

    @Autowired
    private ObjectDao dao;

    @Autowired
    private S3Dao s3Dao;

    public Object get(String id) {
        logger.info(id);
        Optional<Object> object = dao.get(id);
        if (object.isPresent()) {
            return object.get();
        }

        return null;
    }

    public void refresh(String id) {
        logger.info(id);
        s3Dao.refresh(id);
    }
}
