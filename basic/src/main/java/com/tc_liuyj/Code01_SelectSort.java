package com.tc_liuyj;

import java.util.Arrays;

/**
 * 选择排序
 * @author liuyajie
 * @date 2022/03/06/2:00 下午
 */
public class Code01_SelectSort {


    public static void selectSort(int[] arr) {
        // arr == null 错误数据处理，  arr.length < 2 数组本身就有序，无序排序
        if (arr == null || arr.length < 2) {
            return;
        }

        /**
         *
         * 假设N个元素
         * 第0次： 在 0 到 n-1的元素 中找到最小的元素的位置，放到0的位置
         *
         * 第1次： 在 1 到 n-1的元素 中找到最小的元素的位置，放到1的位置
         *
         * 第2次： 在 2 到 n-1的元素 中找到最小的元素的位置，放到2的位置
         *
         *    。
         *
         *    。
         *
         *    。
         *
         * 第n-2次：在 n-2次到n-1的元素中找到最小位置的元素，放到n-2的位置
         *
         * 最后一步可省
         * 第n-1次：在 n-1次到n-1的元素中找到最小位置的元素，放到n-1的位置
         *
         * 总共N次
         */
        // i 从 0 到  n-2
        for (int i = 0; i < arr.length - 1; i++) {
            //
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j ++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;

            }
            swap(arr, i, minIndex);
        }
    }

    /**
     * 交换i位置与j位置的数据
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * for test
     * @param arr
     */
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    /**
     * 生成随机数组
     * @param maxSize 数组最大的的大小
     * @param maxValue 数组中的最大值
     * @return
     */
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int length = (int)((maxSize + 1) * Math.random());
        int[] arr = new int[length];
        // 给数组中添加随机数
        for (int i = 0; i < arr.length; i++) {
            // [-maxValue, maxValue]
            arr[i] = (int)((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());;
        }
        return arr;
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
}
