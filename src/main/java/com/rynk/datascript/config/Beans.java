package com.rynk.datascript.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description
 * @auther Somnus丶y
 * @date 2019/7/17 22:47
 */
@Configuration
public class Beans {

    @Value("${datascript.worker.core-size:10}")
    private int coreSize;

    @Value("${datascript.worker.max-size:10}")
    private int maxSize;

    private long waitTime = 1000;

    private AtomicInteger workerSequence = new AtomicInteger(0);

    @Bean
    public ThreadPoolExecutor convertWorker(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(coreSize,maxSize,waitTime,
                TimeUnit.MINUTES,new LinkedBlockingQueue<>(1000),
                r->new Thread(r,"pool-datascript-thread-"+workerSequence.incrementAndGet())
                ,new ThreadPoolExecutor.CallerRunsPolicy());
        return threadPoolExecutor;
    }


}
