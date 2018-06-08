package com.shaffer.dao;

import com.shaffer.dao.s3.S3Dao;
import com.shaffer.model.Object;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@CacheConfig(cacheNames={"object"})
public class ObjectDao {
    private static final Logger logger = LoggerFactory.getLogger(ObjectDao.class);

    @Autowired
    private S3Dao s3Dao;

    @Cacheable(key = "#id", unless = "#result==null")
    public Optional<Object> get(String id) {
        logger.info("Get by {}", id);
        Optional<Object> object = Optional.ofNullable(s3Dao.getBy(id));

        logger.info(object.toString());

        return object;
    }
}
