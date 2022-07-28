package com.example.spring_jwt_get_arrays.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.*;

@Service
public class LoginAttempsService {
    public static final int MAXIMUM_NUMBER_OF_ATTEMPS = 5;
    public static final int ATTEMP_INCREMENT = 1;
    private LoadingCache<String,Integer> loginAttempCache;

    public LoginAttempsService() {
        super();
        loginAttempCache = CacheBuilder.newBuilder().expireAfterWrite(15, MINUTES)
                .maximumSize(100).build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String key) throws Exception {
                        return 0;
                    }
                });
    }
}
