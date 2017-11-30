package com.shaffer.dao.s3;

import com.amazonaws.services.s3.AmazonS3;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shaffer.model.Object;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
@CacheConfig(cacheNames={"objects"})
public class S3Dao {
    private static final Logger logger = LoggerFactory.getLogger(S3Dao.class);

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Value("${aws.bucket.object.key}")
    private String keyName;

    @Autowired
    private AmazonS3 s3Client;

    @Cacheable
    public List<Object> getAllObjects() {
        S3Object s3object = s3Client.getObject(bucketName, keyName);
        S3ObjectInputStream inputStream = s3object.getObjectContent();

        ObjectMapper mapper = new ObjectMapper();

        List<Object> list = new ArrayList<>();
        try {
            list = mapper.readValue(inputStream, mapper.getTypeFactory().constructCollectionType(List.class, Object.class));
            logger.info(list.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    @CacheEvict
    public void refresh(){

    }
}
