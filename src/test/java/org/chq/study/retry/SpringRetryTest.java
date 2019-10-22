package org.chq.study.retry;

import org.chq.study.StudyApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.SocketTimeoutException;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudyApplication.class)
public class SpringRetryTest {
    @Autowired
    private SpringRetry springRetry;
    @Test
    public void test() {
        springRetry.diaoYong();
    }

}