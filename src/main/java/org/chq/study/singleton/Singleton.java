package org.chq.study.singleton;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @Author: chenhq
 * @Email: 542504275@qq.com
 * @CreateDate: 2019/8/29 21:51
 */
public class Singleton {
    private Singleton(){}
    //1、避开线程安全的问题：类的静态属性只会在第一次加载类的时候初始化，在类进行初始化时，别的线程是无法进入的。
    //2、不过当只调用外部类的静态变量，静态方法时，是不会让静态内部类的被加载,实现懒加载
    private static class SingletonHolder{
        private  static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance(){
        Map map = Collections.synchronizedMap(new HashMap());
        return SingletonHolder.INSTANCE;
    }

}
