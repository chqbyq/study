package org.chq.study.thread.threadpool;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class TestThreadPoolTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }



    @Test
    public void testSubmitReturnFuture() {

    }

    @Test
    public void testFixPool() {
    }

    @Test
    public void testMyExcutor() {
    }

    @Test
    public void testScheduled() {
    }
}
