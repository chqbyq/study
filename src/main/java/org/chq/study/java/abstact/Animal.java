package org.chq.study.java.abstact;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 抽象类
 *
 * @Author: chenhq
 */
@Controller
@RequestMapping("/animal")
public abstract class Animal {
    public abstract  void say();


    public void speak(){
        say();
        System.out.println("come in speak");
    }

    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.speak();
    }
}
