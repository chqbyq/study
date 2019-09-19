package org.chq.study.thread.wait_notify;

/**
 * @Description: 并发之美11页，研究是不是线程被挂起后，wait（）后面的代码就不执行。
 * 实验结果：果然run方法就停在了wait()这里。
 * @Author: chenhq
 * @Email: 542504275@qq.com
 * @CreateDate: 2019/7/11 0:22
 */
public class waitP11 {
    static Object obj = new Object();

//    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new Thread(() -> {
//           try{
//               System.out.println("---begin----");
//               synchronized (obj){
//                   System.out.println("---obj-wait-begin----");
//                   obj.wait();
//                   //主要实验会不会执行这一步
//                   System.out.println("---obj-wait-end----");
//               }
//               System.out.println("---end----");
//           }catch (InterruptedException e){
//               e.printStackTrace();
//           }
//        });
//        thread.start();
//        Thread.sleep(1000);
//        System.out.println("---begin interrupt threadA----");
//        thread.interrupt();
//        System.out.println("---end interrupt threadA----");
//
//    }
    //主线程跑完，子线程还在跑
    public static void main(String[] args) {
        Thread threadA = new Thread(()->{
            synchronized (obj){
                System.out.println("A run");
                try {
                    obj.wait(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("A end");
            }
        });
        threadA.start();
        System.out.println("mian run");
        System.out.println("mian end");
    }
}
