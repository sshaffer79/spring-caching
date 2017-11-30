package com.shaffer.dao;

import com.shaffer.dao.s3.S3Dao;
import com.shaffer.model.Object;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@CacheConfig(cacheNames={"object"})
public class ObjectDao {
    private static final Logger logger = LoggerFactory.getLogger(ObjectDao.class);

    @Autowired
    private S3Dao s3Dao;

    @Cacheable
    public Optional<Object> get(String id) {
        List<Object> list = s3Dao.getAllObjects();

        if (list.isEmpty()) {
            return null;
        }

        Optional<Object> object = list.stream()
                .peek(x -> logger.info("will filter " + x))
                .filter(x -> x.getId().equals(id))
                .findFirst();

        logger.info(object.toString());

        return object;
    }

    @CacheEvict(key = "#id")
    public void refresh(String id) {

    }
}
