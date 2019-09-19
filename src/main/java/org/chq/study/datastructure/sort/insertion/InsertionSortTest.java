package org.chq.study.datastructure.sort.insertion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 插入排序
 * @Author: chenhq
 * @Email: 542504275@qq.com
 * @CreateDate: 2019/7/23 23:56
 */
public class InsertionSortTest {

        public static void main(String[] args) {
            int[] array={23,56,8,45,2,95,11,12,17,10,11};
            System.out.println("length: "+array.length);
//            insertSort(array);
//            sheelSort(array);
//            bubbleSort(array);
//            quickSort(array,0,array.length-1);
//            simpleSelectSort(array);
            heapSort(array);
//            radixSort();

            int i =(int)Math.pow(10, 3);
            int i2 = 95 % (int) Math.pow(10, 2);
            int i3 = 95 % (int) Math.pow(10, 1) / (int) Math.pow(10, 0);
            int i4 = 95 % (int) Math.pow(10, 2) / (int) Math.pow(10, 1);
            System.out.println(Arrays.toString(array));
            System.out.println(i);
            System.out.println(i2);
            System.out.println(i3);
            System.out.println(i4);
        }

        public static void insertSort(int[] a) {
            int insertNum; // 要插入的数
            for (int i = 1; i < a.length; i++) {
                insertNum = a[i];
                int j = i - 1;
                // 将大于insertNum的数向后移动一格
                while (j >= 0 && a[j] > insertNum) {
                    a[j + 1] = a[j];
                    j--;
                }
                a[j + 1] = insertNum;
            }
        }
        public static void sheelSort(int[] a) {
            int d = a.length;
            while (d != 0) {
                d = d / 2;
                System.out.println("length变化： " + d);
                // 分组个数
                for (int x = 0; x < d; x++) {
                    for (int i = x + d; i < a.length; i += d) {
                        // j为有序序列最后一位的位数
                        int j = i - d;
                        // 要插入的元素
                        int insertNum = a[i];
                        for (; j >= 0 && insertNum < a[j]; j -= d) {
                            // 向后移动length位
                            a[j + d] = a[j];
                        }
                        a[j + d] = insertNum;
                    }
                }
            }
        }
        /**
         * @description 冒泡
         * @param a
         * @return void
         */
        public static void bubbleSort(int[] a){
            int d=a.length;
            int temp;
            for(int i=0;i<a.length;i++){
                for(int j=0;j<a.length-i-1;j++){
                    if(a[j]>a[j+1]){
                        temp=a[j+1];
                        a[j+1]=a[j];
                        a[j]=temp;
                    }
                }
            }
        }
        /**
         * @description 快排
         * @param numbers
         * @param start
         * @param end
         * @return void
         */
        public static void quickSort(int[] numbers, int start, int end) {
//            int[] array={23,56,8,45,2,95,11,12,17,10,11};
            if (start < end) {
                // 选定的基准值（第一个数值作为基准值）
                int base = numbers[start];
                // 记录临时中间值
                int temp;
                //这里可以用两个变量i和j，分别指向序列最左边和最右边。我们为这两个变量起个好听的名字“哨兵i”和“哨兵j”
                int i = start;
                int j = end;
                do {
                    //从左到右找比基准值大的数
                    while ((numbers[i] < base) && (i < end))
                        i++;
                    //从右到左找比基准值小的数
                    while ((numbers[j] > base) && (j > start))
                        j--;
                    if (i <= j) {
                        temp = numbers[i];
                        numbers[i] = numbers[j];
                        numbers[j] = temp;
                        i++;
                        j--;
                    }
                } while (i <= j);//哨兵i、j相遇则此次'探测'结束
                //左边序列继续递归快排
                if (start < j)
                    quickSort(numbers, start, j);
                //右边序列继续递归快排
                if (end > i)
                    quickSort(numbers, i, end);
            }
        }
    /**
     * @description 简单交换排序
     * @param arr
     * @return void
     */
    public static void simpleSelectSort(int[] arr){
            for(int i = 0;i < arr.length;i++){
                int key = arr[i];
                int position = i;
                for (int j = i+1;j <arr.length;j++){
                    if (arr[j] < key){
                        key = arr[j];
                        position = j;
                    }
                }
                arr[position] = arr[i];
                arr[i] = key;
            }
        }

    /**
     * @description 堆排序
     * @param a
     * @return void
     */
    public static void heapSort(int[] a){

        System.out.println("开始排序");
        int arrayLength=a.length;
        //循环建堆,这里再-1是因为最后一个节点，也就是下面建堆的第一次循环，倘若右节点存在且最大，如果不-1则基于2k+2的规则会越界，
        // 不信调试下即可，虽然这里第一次lastIndex为10，但是会发现右节点存在的话刚好下标也是10，倘若不-1就是11了，也就越界了。
        for(int i=0;i<arrayLength-1;i++){
            //建堆

            buildMaxHeap(a,arrayLength-1-i);
            //交换堆顶和最后一个元素
            swap(a,0,arrayLength-1-i);
            System.out.println(Arrays.toString(a));
        }
    }
    private static void swap(int[] data, int i, int j) {
        int tmp=data[i];
        data[i]=data[j];
        data[j]=tmp;
    }
    //对data数组从0到lastIndex建大顶堆
    private static void buildMaxHeap(int[] data, int lastIndex) {
        //从lastIndex处节点（最后一个节点）的父节点开始
        for(int i=(lastIndex-1)/2;i>=0;i--){
            //k保存正在判断的节点
            int k=i;
            //如果当前k节点的子节点存在
            while(k*2+1<=lastIndex){
                //k节点的左子节点的索引
                int biggerIndex=2*k+1;
                //如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在,因为9<10,则右节点可以为10
                if(biggerIndex<lastIndex){
                    //若果右子节点的值较大
                    if(data[biggerIndex]<data[biggerIndex+1]){
                        //biggerIndex总是记录较大子节点的索引
                        biggerIndex++;
                    }
                }
                //如果k节点的值小于其较大的子节点的值
                if(data[k]<data[biggerIndex]){
                    //交换他们，因为k一直在变小，直到变为0，就是第一个
                    swap(data,k,biggerIndex);
                    //将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值
                    k=biggerIndex;
                }else{
                    //退出本次while
                    break;
                }
            }
        }
    }

    /**
     * @description 归并排序
     * @param numbers
     * @param left
     * @param right
     * @return void
     */
    public static void mergeSort(int[] numbers, int left, int right) {

        int t = 1;// 每组元素个数
        int size = right - left + 1;
        while (t < size) {
            int s = t;// 本次循环每组元素个数
            t = 2 * s;
            int i = left;
            while (i + (t - 1) < size) {
                merge(numbers, i, i + (s - 1), i + (t - 1));
                i += t;
            }
            if (i + (s - 1) < right)
                merge(numbers, i, i + (s - 1), right);
        }
    }
    private static void merge(int[] data, int p, int q, int r) {
        int[] B = new int[data.length];
        int s = p;
        int t = q + 1;
        int k = p;
        while (s <= q && t <= r) {
            if (data[s] <= data[t]) {
                B[k] = data[s];
                s++;
            } else {
                B[k] = data[t];
                t++;
            }
            k++;
        }
        if (s == q + 1)
            B[k++] = data[t++];
        else
            B[k++] = data[s++];
        for (int i = p; i <= r; i++)
            data[i] = B[i];
    }

    /**
     * @description 基数排序
     * @param arr
     * @return void
     */
    public static void radixSort(int[] arr) {
        int[] array = {3,33,43,44,67,55};

        //首先确定排序的趟数;
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        int time = 0;
        //判断位数;
        while (max > 0) {
            max /= 10;
            time++;
        }
        //建立10个队列;
        List<ArrayList> queue = new ArrayList<ArrayList>();
        for (int i = 0; i < 10; i++) {
            ArrayList<Integer> queue1 = new ArrayList<Integer>();
            queue.add(queue1);
        }
        //进行time次分配和收集;
        for (int i = 0; i < time; i++) {
            //分配数组元素;
            for (int j = 0; j < array.length; j++) {
                //得到数字的第time+1位数;
                int x = array[j] % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
                ArrayList<Integer> queue2 = queue.get(x);
                queue2.add(array[j]);
                queue.set(x, queue2);
            }
            int count = 0;//元素计数器;
            //收集队列元素;
            for (int k = 0; k < 10; k++) {
                while (queue.get(k).size() > 0) {
                    ArrayList<Integer> queue3 = queue.get(k);
                    array[count] = queue3.get(0);
                    queue3.remove(0);
                    count++;
                }
            }
        }
    }
}
