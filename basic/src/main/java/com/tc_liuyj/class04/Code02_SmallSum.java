package com.tc_liuyj.class04;

import java.util.Arrays;

/**
 * @author liuyajie
 * @date 2022/03/16/4:15 下午
 *
 *
 * 小和问题
 * 描述： 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。求一个数组的小和。
 * 例子： [1,3,4,2,5]
 * 1左边比1小的数：没有
 * 3左边比3小的数：1
 * 4左边比4小的数：1,3
 * 2左边比2小的数：1
 * 5左边比5小的数：1,3,4,2
 * 所以小和为1+1+3+1+1+3+4+2=16
 *
 * 暴力遍历o(N^2)的复杂度
 *
 * 归并排序解决上述问题
 * 换个思路： 当前数的右边有多少数比当前数大，就有多少个当前数的小和。
 * 每个数的心路历程：
 * 1的右边有多少个数比1大， 3，4，2，5  所以1的小和有4个
 * 3的右边有多少个数比3大， 4，5 所以3的小和有俩个
 * 4的右边有多少个数比4大， 5 所以4的小和有一个
 * 2的右边有多少个数比2大， 5 所以2的小和有一个
 * 5的右边没有比5大的， 所以5的小和为0个
 * 所以小和为 4*1 + 3*2 + 4 + 2 = 16
 * 关键点： 如何实现 高效实现 当前数的右边比当前数大的个数。
 */
public class Code02_SmallSum {

    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return partation(arr, 0, arr.length - 1);
    }

    public static int smallSum2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                sum += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return sum;
    }



    public static int mergeSort(int[] arr) {
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

    // 二路归并俩个有序数组
    private static int merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int helpIndex = 0;
        int p1 = l;
        int p2 = mid + 1;
        /**
         * 例子： [1,3,4,2,5]
         *
         * 1的右边有多少个数比1大， 3，4，2，5  所以1的小和有4个
         * 3的右边有多少个数比3大， 4，5 所以3的小和有俩个
         * 4的右边有多少个数比4大， 5 所以4的小和有一个
         * 2的右边有多少个数比2大， 5 所以2的小和有一个
         * 5的右边没有比5大的， 所以5的小和为0个
         * 所以小和为 4*1 + 3*2 + 4 + 2 = 16
         *
         * 关键点： 如何实现 当前数与每个右边的数做比较， 效率还要高
         *
         * [-10, -9, 12]
         * -10
         */
        int sum = 0;
        while(p1 <= mid && p2 <= r) {
            sum += arr[p1] < arr[p2]? (r - p2 + 1) * arr[p1] : 0;
            // 相等先合并右边
            help[helpIndex++] = arr[p1] < arr[p2] ? arr[p1++]: arr[p2++];
        }

        while(p1 <= mid) {
            help[helpIndex++] = arr[p1++];
        }

        while(p2 <= r) {
            help[helpIndex++] = arr[p2++];
        }

        // 把help中的有序数据拷贝到 arr中
        for (int i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return sum;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] res = new int[(int)(Math.random() * (maxSize + 1))];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int)(Math.random() * (maxValue + 1)) - (int)(Math.random() * (maxValue + 1));
        }
        return res;
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

    public static boolean isEquals(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 == null) {
            return true;
        }

        if ((arr1 == null && arr2 != null) || (arr2 == null && arr1 != null) || (arr1.length != arr2.length)) {
            return false;
        }

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }

    /**
     * [-4, 32, 36]
     * -4  0
     * 32   -4
     * 36  -4 32
     * @param args
     */
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int sum = smallSum(arr1);
            int sum2 = smallSum2(arr2);
            if (sum != sum2) {
                System.out.println("出错了");
                printArray(arr1);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
