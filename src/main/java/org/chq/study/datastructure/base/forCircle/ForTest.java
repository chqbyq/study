package org.chq.study.datastructure.base.forCircle;

/**
 * @Description: 循环的执行顺序
 * @Author: chenhq
 * @Email: 542504275@qq.com
 * @CreateDate: 2019/7/22 23:06
 */
public class ForTest {


    public static void main(String[] args) {
//        test1();
        test2();
    }

    /**
     * @description 先初始化j = 0，只初始化一次，然后判断表达式，符合条件才j++，不然不会自增，所以最后一次不符合条件跳出来不会自增。
     * @return void
     */
    public static void  test1() {
        int[] arr = new int[3];
        int j;
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 3;
        int searchKey = 4;
        for (j = 0; j < arr.length; j++) {
            System.out.println("j1=========" + j);
            if (arr[j] == searchKey) {
                break;
            }
        }
        System.out.println("j2=========" + j);
    }

    /**
     * @description 一次外循环，跑完内循环，然后下一次外循环。当再次进入内部循环，j还是从0
     * @param
     * @return void
     */
    public static void test2(){

        for(int i=1;i<3;i++)
        {
            for(int j=1;j<5;j++)//执行一次外循环，内循环执行5次。
            {
                System.out.println("第"+j+"次内循环！");
            }
            System.out.println("第"+i+"轮外循环执行结束。");
        }
    }
}