package com.tc_liuyj.class04;

/**
 * @author liuyajie
 * @date 2022/03/16/7:42 下午
 *
 * 逆序数问题
 * 描述： 在一个排列中，如果一对数的前后位置与大小顺序相反，
 * 　　　 即前面的数大于后面的数，那么它们就称为一个逆序。
 * 　　　 一个排列中逆序的总数就称为这个排列的逆序数。
 * 例子： [3,1,-3,2,5]
 * 3 1； 3 -3； 3 2；
 * 1-3；
 * 每个数的心路历程：
 * 当前数为3， 3的右边比3小的数的个数。  1 -3 2
 * 当前数为1， 1的右边比1小的数的个数。  -3
 * 当前数为-3， -3的右边比-3小的数的个数。 没有
 * 当前数为2， 2的右边比2小的数的个数。 没有
 * 当前数为5， 5的右边比5小的数的个数。 没有
 *
 * 迭代的归并来理解比较好理解。
 *
 */
public class Code03_ReversePair {




    private static int reversePair(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return partation(arr, 0, arr.length - 1);
    }

    private static int reversePair2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < arr.length; i++) {

            for (int j = i + 1; j < arr.length; j++) {
                res += arr[i] > arr[j]? 1 : 0;
            }
        }
        return res;
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
     * [4,3,1,8,4,3]
     *
     * [4,4,4,4,6,8,10]   [1,2,4,6,7,15, 17]
     *
     *
     *
     *
     *
     *
     *
     *
     */
    private static int merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int helpIndex = 0;
        int p1 = mid;
        int p2 = r;
        int res = 0;
        // [4,4,4,4,6,8,10]   [1,2,4,6,7]
        /**
         * [4,4,4,4,6,8,10]   [1,2,4,6,7, 15, 17]
         * 10
         *
         */
        while(p1 >= l && p2 >= mid + 1) {
            res += arr[p1] > arr[p2] ? p2 - (mid + 1) + 1 : 0;
            help[helpIndex++] = arr[p1] <= arr[p2] ? arr[p2--] : arr[p1--];
        }

        while(p1 >= l) {
            help[helpIndex++] = arr[p1--];
        }
        while(p2 >= mid + 1) {
            help[helpIndex++] = arr[p2--];
        }

        // help 合并到arr中
        for (int i = 0; i < help.length; i++) {
            arr[r - i] = help[i];
        }
        return res;
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
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
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



    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);

            int res1 = reversePair(arr1);
            int res2 = reversePair2(arr2);
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
