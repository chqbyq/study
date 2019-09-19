package org.chq.practice;

import java.util.Arrays;

/**
 * @Description: TODO
 * @Author: chenhq
 * @Email: 542504275@qq.com
 * @CreateDate: 2019/8/26 23:36
 */
public class practice {
    public static void main(String[] args) {
        int[] arr = {1,10,8,29,11,3,48,25};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            int base = arr[start];
            int i = start;
            int j = end;
            do {
                while (arr[i] < base && i < end)
                    i++;
                while (arr[j] > base && j > start)
                    j--;
                if (i <= j) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                    i++;
                    j--;
                }
            } while (i <= j);
            if (i < end) {
                quickSort(arr, i, end);
            }
            if (j > start) {
                quickSort(arr, start, j);
            }
        }
    }
}
