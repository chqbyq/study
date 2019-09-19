package org.chq.study.thread.buildthread;

/**
 * @Description: 实现Runnable接口
 * @Author: chenhq
 * @Email: 542504275@qq.com
 * @CreateDate: 2019/7/6 23:59
 */
public class ThreadOfRunnable implements Runnable {

    public static void main(String[] args) {
        //匿名内部类
        new Thread(() -> System.out.println("lambda表达式创建的线程")).start();
        //
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("显示内部类的线程");
            }
        }).start();
        //当前类生成的线程
        ThreadOfRunnable myThread = new ThreadOfRunnable();
        Thread a = new Thread(myThread);
        a.start();

    }

    @Override
    public void run() {
        System.out.println("继承runnable接口创建的线程");
    }
}
