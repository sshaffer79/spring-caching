package com.shaffer.dao;

import com.shaffer.model.Object;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ObjectDao {
    private static final Logger logger = LoggerFactory.getLogger(ObjectDao.class);

    public Object get(String id) {
        Object object = new Object(id, "Temp " + id);
        logger.info(object.toString());
        return object;
    }

    public void refresh() {

    }
}
