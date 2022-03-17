package com.tc_liuyj.class04;

import java.util.Arrays;

/**
 * @author liuyajie
 * @date 2022/03/16/10:26 上午
 */
public class Code01_MergeSort {


    /**
     *
     * 归并排序（Merge Sort）是建立在归并操作上的一种有效，稳定的排序算法，该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
     * 将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为二路归并。
     *
     * 一个大小为N的无序数组，使用归并排序使其有序
     *                       [0, N-1]   有序
     *                [0, N/2]         [(n/2)+1, N-1]   merge 把[0, N-1]分为[0, n/2]，[(n/2)+1, N-1]俩个子序列，先使俩个子序列有序，然后合并俩个有序子序列，这样 0 to n有序
     *         [0,N/4]  [(n/4)+1,N/2]  [(n/2)+1 mid] [mid+1, N-1]    把[0, N/2]分为[0, n/4]，[(n/4)+1, N/2]俩个子序列，先使俩个子序列有序，然后合并俩个有序子序列。 这样0 to N/2就有序
     *
     *    定义： partation(0, n)  含义 使0到N有序
     *    partation(0, n) 中包含 partation(0, mid)  partation(mid+1, n) merge
     *
     *
     *
     */
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        partation(arr, 0, arr.length - 1);
    }


    /**
     *
     * 非递归方式归并排序
     * 原理： 递归的逆过程
     *
     * 假设 5个数
     * step= 1  [0, 1]  [2,3] [4,5] [6]
     * step= 2  [0,1,2,3] [4,5] [6]
     * step= 4  [0,1,2,3,4]
     * step= 8  结束
     *
     * @param arr
     */
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int stepLength = 1;
        int n = arr.length;
        while (stepLength < n) {
            int l = 0;
            while (l < n) {
                //
                if (n - l <= stepLength) {
                    break;
                }
                // 有上述判断， 中间边界一定是正常的
                int mid = l + stepLength - 1;
                // 确定正常右边界   (n-1) 是索引的位置  n-1 - mid 表示
                int r = mid + Math.min(stepLength, n - 1 - mid );
                merge(arr, l,mid, r);
                l = r + 1;
            }
            // 防止溢出
            if (stepLength > n / 2) {
                break;
            }
            stepLength <<= 1;
        }
    }

    private static void partation(int[] arr, int L, int R) {
        //递归结束条件 L==R表示L到R范围内只有一个数，L到R有序
        if (L == R) {
            return;
        }
        int mid = L + (R - L) / 2;
        partation(arr, L, mid);
        partation(arr,mid + 1, R);
        merge(arr, L, mid, R);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int helpIndex = 0;
        int p1 = l;
        int p2 = mid + 1;
        // 处理无越界的情况
        while (p1 <= mid && p2 <= r) {
            help[helpIndex++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }

        // 处理一边越界的情况
        while (p1 <= mid) {
            help[helpIndex++] = arr[p1++];
        }

        while (p2 <= r) {
            help[helpIndex++] = arr[p2++];
        }
        // 将help中的数据拷贝到arr中
        for (int i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int)(Math.random() * (maxSize + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * (maxValue + 1)) - (int)(Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static boolean isEquals(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null) || (arr1.length != arr2.length)) {
            return false;
        }

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTime = 50000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr);
            // mergeSort(arr);
            mergeSort2(arr);
            Arrays.sort(arr2);
            if (!isEquals(arr, arr2)) {
                System.out.println("出错了！");
                printArray(arr);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
