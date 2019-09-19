package org.chq.study.util;

import sun.nio.cs.StreamDecoder;

import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 学习ThreadLocal
 * @Author: chenhq
 * @Email: 542504275@qq.com
 * @CreateDate: 2019/6/11 16:33
 */
public class ThreadLocalUtil {

    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }

    };




}
