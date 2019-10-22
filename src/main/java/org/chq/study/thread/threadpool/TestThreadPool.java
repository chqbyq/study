package org.chq.study.thread.threadpool;

import org.chq.study.thread.buildthread.ThreadOfCallable;
import org.chq.study.thread.buildthread.ThreadOfExtends;
import org.chq.study.thread.buildthread.ThreadOfRunnable;
import org.chq.study.thread.threadpool.common.CommonSynz;

import javax.sound.midi.Soundbank;
import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: TODO
 * @Author: chenhq
 * @Email: 542504275@qq.com
 * @CreateDate: 2019/9/20 9:05
 */
public class TestThreadPool {
    static ExecutorService fixPool = Executors.newFixedThreadPool(10,new ThreadFactoryImpl("chq"));

    /** 记载爬虫成功次数 */
    private static AtomicInteger sucCount = new AtomicInteger(0);

    public static class MyTask implements Runnable{
        private int taskNum;

        public MyTask(int num) {
             this.taskNum = num;
         }
        public MyTask() {
             this.taskNum = 0;
         }
        @Override
        public void run() {
            System.out.println("开始执行时间:" +
                    DateFormat.getTimeInstance().format(new Date()));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("正在执行task "+taskNum);
        }
    }
    public static void main(String[] args) {
//        testSubmitReturnFuture();
//        testFixPool();
//        testMyExcutor();
//        testScheduled();
//        testSynz();
        test2ThreadPool();
    }
    /**
     *  测试submit方法返回future所能接受的参数:
     *  以及作用
     *
     * @param
     * @return void
     */
    public static void testSubmitReturnFuture(){
        fixPool.submit(()-> {
            System.out.println(Thread.currentThread().getName()+"aaa");
        });
        fixPool.submit(new Thread(() -> System.out.println("lambda表达式创建的线程")));
        Future<String> futureOfRunnable = (Future<String>) fixPool.submit(new ThreadOfRunnable());

        Future<String> futureOfCallable = fixPool.submit(new ThreadOfCallable());
        try {

            String msgOfRunnable = futureOfRunnable.get();
            String msgOfCallable = futureOfCallable.get();
            //返回null，因为runnable的没有返回值，future只能用来判断状态
            System.out.println("1111"+msgOfRunnable);
            System.out.println("1111"+futureOfRunnable.cancel(true));
            System.out.println("1111"+futureOfRunnable.isCancelled());
            System.out.println("1111"+futureOfRunnable.isDone());
            System.out.println(Thread.currentThread().getName()+msgOfRunnable);
            System.out.println("2222"+msgOfCallable);
            System.out.println(Thread.currentThread().getName()+msgOfCallable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        fixPool.shutdown();
    }
    /**
     * 可以发现来来回回就是那两个线程在执行
     *
     * @param
     * @return void
     * @author chq
     * @date 2019/9/20 9:49
     */
    public static void testFixPool(){
         // 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
        for (int i = 0; i < 10; i++){
            Thread thread = new ThreadOfExtends();
            // 将线程放入池中进行执行
            fixPool.execute(thread);
//            System.out.println("线程池中线程数目："+fixPool.getPoolSize());
        }
         for(int i = 0; i <= 10; i++){
             fixPool.execute(()-> System.out.println(Thread.currentThread().getName()+"testFixPool"));
         }
         // 关闭线程池
         fixPool.shutdown();
    }

    public static void testMyExcutor(){
        ThreadPoolExecutor  myExecutor = new ThreadPoolExecutor(5,10,5,TimeUnit.MILLISECONDS,new LinkedBlockingDeque<Runnable>(5));
        for(int i=0;i<15;i++){
            MyTask myTask = new MyTask(i);
            myExecutor.execute(myTask);
            System.out.println("线程池中线程数目："+myExecutor.getPoolSize()+"，队列中等待执行的任务数目："+
                              myExecutor.getQueue().size()+"，已执行完别的任务数目："+myExecutor.getCompletedTaskCount());
        }
        myExecutor.shutdown();
    }

    public static void testScheduled(){
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
//        System.out.println("testScheduled-scheduleAtFixedRate 开始执行时间:" +
//                DateFormat.getTimeInstance().format(new Date()));
//        service.scheduleAtFixedRate(new MyTask(),3L,2L,TimeUnit.SECONDS);
        System.out.println("testScheduled-scheduleWithFixedDelay 开始执行时间:" +
                DateFormat.getTimeInstance().format(new Date()));
        service.scheduleWithFixedDelay(new MyTask(),3L,2L,TimeUnit.SECONDS);
    }


    public static void testSynz(){
        for(int i = 0; i < 100; i++){
            fixPool.execute(()->{
                CommonSynz commonSynz = new CommonSynz();
                commonSynz.synzTest();
                sucCount.getAndIncrement();
                if (sucCount.get() % 10 == 0){

                }

            });
        }

    }

    public static void test2ThreadPool(){
        fixPool.execute(()->{
            System.out.println(Thread.currentThread().getName()+"任务1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        fixPool.execute(()->{
            System.out.println(Thread.currentThread().getName()+"任务2");
        });
        System.out.println(Thread.currentThread().getName()+"主任务");
//        fixPool.shutdownNow();
    }

}
