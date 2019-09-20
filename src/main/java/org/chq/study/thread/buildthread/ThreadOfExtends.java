package org.chq.study.thread.buildthread;

/**
 * @Description: TODO
 * @Author: chenhq
 * @Email: 542504275@qq.com
 * @CreateDate: 2019/9/20 9:47
 */
public class ThreadOfExtends extends Thread {
    @Override
    public void run() {
             System.out.println(Thread.currentThread().getName() + "继承Thread创建的线程正在执行… …");
    }
}
