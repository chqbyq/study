package org.chq.study.thread.interrupt;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Description: 并发之美P26研究为什么会又输出一个hello
 * 研究结果：join会把中断标志设为false，时不时还是会在interrupt后面输出hello，我也不知道为啥，
 * interrupt后不是线程thread退出循环就跑完了吗，怎么还输出hello
 * @Author: chenhq
 * @Email: 542504275@qq.com
 * @CreateDate: 2019/7/11 23:09
 */
public class interruptP26 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
           while (!Thread.currentThread().isInterrupted()){
               System.out.println(Thread.currentThread()+"hello");
           }
        });
        thread.start();
        //主线程休眠1s，以便中断前让子线程输出
        Thread.sleep(1000);

        //中断子线程
        System.out.println("main thread interrupt thread");
        thread.interrupt();
//        System.out.println("interrupt后，join前当前中断标志"+thread.isInterrupted());

        //等待子线程执行完毕
        thread.join();
//        System.out.println("join后当前中断标志"+thread.isInterrupted());
        System.out.println("main is over");

    }
}
