package org.chq.study.retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * spring-retry尝试
 *
 * @Author: chenhq
 */
@Component

public class SpringRetry {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringRetry.class);

    private AtomicInteger count = new AtomicInteger(1);

    /**
     * 线程池
     */
    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    private String[] stringArr = {"a","b","c","d"};

    @Retryable(value = { SocketTimeoutException.class, ConnectException.class}, maxAttemptsExpression = "${retry.maxAttempts:5}",
            backoff = @Backoff(delayExpression = "${retry.backoff:1000}"))
    public void retry(String str)  {
        executor.execute(()->{
            LOGGER.info(str+"-start to retry : " + count.getAndIncrement());
            throw new RuntimeException("here " + count.get());
        });
    }

    public void diaoYong(){
        for(String str : stringArr){
            retry(str);
        }
    }

    @Recover
    public void recover(RuntimeException t) {
        LOGGER.info("SampleRetryService.recover:{}", t.getClass().getName());
    }
}
