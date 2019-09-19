package org.chq.study.thread.buildthread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description:  实现Callable接口，有返回值
 * @Author: chenhq
 * @Email: 542504275@qq.com
 * @CreateDate: 2019/7/7 11:21
 */
public class ThreadOfCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "ThreadOfCallable创建的线程";
    }

    public static void main(String[] args) {
        ThreadOfCallable callable = new ThreadOfCallable();
        FutureTask<String> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        String msg = "";
        try {
            msg = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(msg);
    }
}
