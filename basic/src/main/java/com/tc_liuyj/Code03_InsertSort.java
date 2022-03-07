package com.tc_liuyj.basic;

/**
 * 插入排序
 * @author liuyajie
 * @date 2022/03/06/2:54 下午
 */
public class Code03_InsertSort {

    public static void insertSort(int[] arr) {
        // arr == null 错误数据处理，  arr.length < 2 数组本身就有序，无序排序
        if (arr == null || arr.length < 2) {
            return;
        }

        /**
         *
         * 假设N个元素
         *
         * 第0次： 使0到0有序
         *
         * 第1次： 在第0次的基础上，使0-1有序
         *
         * 第2次： 在第1次的基础上，使0-2有序
         *
         *   。
         *
         *   。
         *
         *   。
         *
         * 第n-2次： 在第n-3的基础上，使0到n-2有序
         *
         * 第n-1次： 在第n-2的基础上，使0到n-1有序
         *
         * 如何在实现在有序集合中增加个一数，集合还是有序集合？  倒着找合适的位置。
         *
         * 倒着遍历集合，与新增数做比较，如果新增的数小，就交换。
         *
         *
         */
        for (int i = 1; i < arr.length; i++) {

            for (int j = i; j >= 0 && arr[j] < arr[j + 1]; j--) {
                swap(arr, j, j+1);
            }
        }
    }


    private static void swap(int[] arr, int j, int i) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {


    }
}
