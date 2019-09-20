package org.chq.study.thread.threadpool;

import org.chq.study.thread.buildthread.ThreadOfCallable;
import org.chq.study.thread.buildthread.ThreadOfExtends;
import org.chq.study.thread.buildthread.ThreadOfRunnable;

import javax.sound.midi.Soundbank;
import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @Description: TODO
 * @Author: chenhq
 * @Email: 542504275@qq.com
 * @CreateDate: 2019/9/20 9:05
 */
public class TestThreadPool {
    static ExecutorService fixPool = Executors.newFixedThreadPool(2,new ThreadFactoryImpl("chq"));

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
        testFixPool();
//        testMyExcutor();
//        testScheduled();
    }
    /**
     * 测试submit方法返回future所能接受的参数：
     * 以及作用
     *
     * @param
     * @return void
     * @author chq
     * @date 2019/9/20 9:38
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
         Thread t1 = new ThreadOfExtends();
         Thread t2 = new ThreadOfExtends();
         Thread t3 = new ThreadOfExtends();
         Thread t4 = new ThreadOfExtends();
         Thread t5 = new ThreadOfExtends();
         // 将线程放入池中进行执行
         fixPool.execute(t1);
         fixPool.execute(t2);
         fixPool.execute(t3);
         fixPool.execute(t4);
         fixPool.execute(t5);

         for(int i = 0; i <= 5; i++){
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

}
