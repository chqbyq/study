package org.chq.study.datastructure.search;

/**
 * @Description: TODO
 * @Author: chenhq
 * @Email: 542504275@qq.com
 * @CreateDate: 2019/9/5 0:31
 */
public class ThreeSearch {

    /**
     * 顺序查找，查到则返回该值下标，查不到返回-1.
     *
     * @param a
     * @param b
     * @return int
     * @author chq
     * @date 2019/9/5 0:38
     */
    public int SequenceSearch(int[] a,int b){

        if(a==null)
            return -1;
        for(int i=0;i<a.length;i++){
            if(a[i]==b)
                return i;
        }
        return -1;
    }
    /**
     * 折半查找的递归算法
     *
     * @param a 数组
     * @param target 寻找的数
     * @param low 最小下标
     * @param high 最大下标
     * @return int
     * @author chq
     * @date 2019/9/5 0:37
     */
    public int BinarySearch1(int[] a,int target,int low,int high){

        if(a==null||low>high)
            return -1;
        int middle=(low+high)/2;
        if(a[middle]==target)
            return middle;
        if(a[middle]<target)
            return BinarySearch1(a,target,middle+1,high);
        else
            return BinarySearch1(a,target,low,middle-1);
    }
}
