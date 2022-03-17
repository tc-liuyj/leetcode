package com.tc_liuyj.class04;

import java.util.Arrays;

/**
 * @author liuyajie
 * @date 2022/03/17/10:45 上午
 *
 * 大于俩倍问题
 * 当前数大于右边数的俩倍
 *
 *
 *
 */
public class Code04_BiggerThanRightTwice {


    private static int reversePairs(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return partation(arr, 0, arr.length - 1);
    }

    private static int partation(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = (l + r) / 2;
        return partation(arr, l, mid) +
        partation(arr, mid + 1, r) +
        merge(arr, l, mid, r);
    }

    /**
     *
     * [5,7,8,10,15,20]    [2,3,5,8,10]
     * // 正这遍历
     * 5, 2 5,3
     * 以5来说 求右边数的俩倍比5小的个数， 右边需要找个第一个
     * 以7来说 求右边数的俩倍比7小的个数
     * 。。。
     * 以20来说 求右边数的俩倍比20小的个数
     *
     * 倒着遍历 都可以实现
     * 以20来说
     * @return
     */
    private static int merge(int[] arr, int l, int mid, int r) {
        int ans = 0;
        // 目前囊括进来的数，是从[M+1, windowR)
        int windowR = mid + 1;
        for (int i = l; i <= mid; i++) {
            while (windowR <= r && (long) arr[i] > (long) arr[windowR] * 2) {
                windowR++;
            }
            ans += windowR - mid - 1;
        }
        int[] help = new int[r - l + 1];
        int helpIndex = 0;
        int p1 = l;
        int p2 = mid + 1;
        while(p1 <= mid && p2 <= r) {
            help[helpIndex++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while(p1 <= mid) {
            help[helpIndex++] = arr[p1++];
        }

        while(p2 <= r) {
            help[helpIndex++] = arr[p2++];
        }

        // 将help中的有序数拷贝到目标数组中
        for (int i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return ans;
    }

    private static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] res = new int[(int)(Math.random() * (maxSize + 1))];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int)(Math.random() * (maxSize + 1)) - (int)(Math.random() * (maxSize + 1));
        }
        return res;
    }

    private static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }



    public static void main(String[] args) {


        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int res1 = reversePairs(arr1);
            // Arrays.sort(arr2);
            int res2 = comparator(arr2);
            if (res1 != res2) {
                System.out.println("出错了");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }



}
