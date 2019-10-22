package org.chq.study.thread.threadpool.common;

/**
 * 测试调用这里的同步或非同步方法
 *
 * @Author: chenhq
 */
public class CommonSynz {

    public synchronized  void synzTest(){
        System.out.println(Thread.currentThread().getName()+"同步方法1");
        System.out.println(Thread.currentThread().getName()+"是否被");
        System.out.println(Thread.currentThread().getName()+"打断");
    }
    public synchronized  void synzTest2(){
        System.out.println("同步方法2");
    }
    public synchronized  void synzTest3(){
        System.out.println("同步方法3");
    }
    public void notSynzTest(){
        System.out.println("非同步方法1");
    }
}
