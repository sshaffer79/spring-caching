package com.shaffer.dao;

import com.shaffer.model.Object;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
@CacheConfig(cacheNames={"object"})
public class ObjectDao {
    private static final Logger logger = LoggerFactory.getLogger(ObjectDao.class);

    @Cacheable
    public Object get(String id) {
        Object object = new Object(id, "Temp " + id);
        logger.info(object.toString());
        return object;
    }

    @CacheEvict(key = "#id")
    public void refresh(String id) {

    }
}
