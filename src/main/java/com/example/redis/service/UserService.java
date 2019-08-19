package com.example.redis.service;

import com.example.redis.config.RedisConfig;
import com.example.redis.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class UserService {

    @Cacheable(value = RedisConfig.USER_NAME_CACHE, key = RedisConfig.USER_NAME_KEY)
    public String getUserId(String username) {

        return fetchUserFromDb(username);
    }

    private String fetchUserFromDb(String name) {

        log.info("----------------DB call to get user with name: {}", name);
        String userId = name + new Random().nextLong();
        log.info("-------userId: {}", userId);
        return userId;
    }
}

