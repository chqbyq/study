package org.chq.study.thread.syncframework;

import org.chq.study.thread.threadpool.ThreadFactoryImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 测试Semaphore
 *
 * @Author: chenhq
 */
public class TestSemaphore {

    /**
     * 5个固定
     */
    private static Semaphore semaphore = new Semaphore(0);

    static ExecutorService fixPool = Executors.newFixedThreadPool(5,new ThreadFactoryImpl("TestSemaphore"));

    public static void main(String[] args) {
//        test();
        test2();
    }
    public static void test(){
        for(int i = 0; i < 5; i++){
            fixPool.execute(()->{
                try {
                    semaphore.acquire(1);
                    System.out.println("-1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                semaphore.release();
                System.out.println("释放");
            });
        }
    }

    public static void test2(){
        fixPool.execute(()->{
           semaphore.release(1);
            System.out.println("任务1");

        });

        fixPool.execute(()->{
            semaphore.release(1);
            System.out.println("任务2");
        });

        try {
            semaphore.acquire(3);
            System.out.println("完成任务");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
