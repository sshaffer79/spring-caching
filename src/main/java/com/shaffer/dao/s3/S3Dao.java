package com.shaffer.dao.s3;

import com.amazonaws.services.s3.AmazonS3;

import com.amazonaws.services.s3.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shaffer.model.Object;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
@CacheConfig(cacheNames={"objects"})
public class S3Dao {
    private static final Logger logger = LoggerFactory.getLogger(S3Dao.class);

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Value("${aws.bucket.object.key}")
    private String keyName;

    @Value("${aws.bucket.object.prefix}")
    private String prefix;

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    private CacheManager cacheManager;

    @PostConstruct
    public void initialize() {
        ObjectListing ol = s3Client.listObjects(bucketName, prefix);
        Pattern pattern = Pattern.compile(prefix + "/(.*?).json");
        for (S3ObjectSummary summary : ol.getObjectSummaries()) {
            if (!summary.getKey().equals(prefix + "/") && !summary.getKey().equals(prefix + "/temp.json")) {
                logger.info(summary.getBucketName() + "::::" + summary.getKey());
                Matcher matcher = pattern.matcher(summary.getKey());
                if (matcher.find()) {
                    String id = matcher.group(1);
                    Object object = refresh(id);
                    cacheManager.getCache("objects").put(id, object);
                }
            }
        }
    }

    @CachePut(key = "#id" , unless="#result==null")
    public Object refresh(String id){
        logger.info("Refreshing for id {}", id);
        return getObject(id);
    }

    @Cacheable(key="#id", unless = "#result==null")
    public Object getBy(String id) {
        logger.info("Get for id {}", id);
        return getObject(id);
    }

    private Object getObject(String id) {
        Object object = null;
        String key = prefix + "/" + id + ".json";
        try {
            S3Object s3object = s3Client.getObject(bucketName, key);
            S3ObjectInputStream inputStream = s3object.getObjectContent();
            ObjectMapper mapper = new ObjectMapper();
            object = mapper.readValue(inputStream, Object.class);
            logger.info(object.toString());
        } catch (AmazonS3Exception e) {
            logger.error("An error occurred trying to retrieve S3 resource {} at bucket {}", key, bucketName, e);
        }catch (Exception e) {
            logger.error("An unexpected error occurred", e);
        }
        return object;
    }
}
