package org.chq.study.thread.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description: 盟大线程工厂
 * @Author: chenhq
 * @Email: 542504275@qq.com
 * @CreateDate: 2019/9/20 13:50
 */
public class ThreadFactoryImpl implements ThreadFactory {
    private final String threadNamePrefix;
    private final AtomicLong threadIndex;

    public ThreadFactoryImpl() {
        this(ThreadFactoryImpl.class.getSimpleName());
    }

    public ThreadFactoryImpl(String threadNamePrefix) {
        this.threadIndex = new AtomicLong(0L);
        this.threadNamePrefix = threadNamePrefix;
    }
    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, this.threadNamePrefix + '-' + this.threadIndex.incrementAndGet());
    }
}
