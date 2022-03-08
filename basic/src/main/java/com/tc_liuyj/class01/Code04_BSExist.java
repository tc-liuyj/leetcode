package com.tc_liuyj.class01;

/**
 * @author liuyajie
 * @date 2022/03/06/3:57 下午
 */
public class Code04_BSExist {
    /**
     *
     * 时间复杂度O(logN)
     * 二分查找： 在有序数组中，查找特定值
     * 假设N个数
     *                                     N
     *                             [0, n/2]              [n/2 +1, n-1]
     *                    [0, n/4]    [n/4 + 1, n/2]   [n/2 +1, n-1/2]  [n-1/2 + 1, n-1]
     *
     * 二分查找的执行流程可以看作是一棵树， 但是只会走树的一个分支。 终止条件是 左边界和右边界相等
     *
     *
     * @param arr
     * @param num
     * @return
     */
    public static boolean bsExist(int[] arr, int num) {

        if (arr == null || arr.length == 0) {
            return false;
        }

        int L = 0;

        int R = arr.length-1;

        int mid = 0;

        while (L < R) {
            mid = L + ((R - L) >> 1);    // mid = (R-L) /2

            if (arr[mid] == num) {
                return true;
            } else if (arr[mid] > num) {
                R = mid -1;
            } else {
                L = mid + 1;
            }
        }
        return arr[L] == num;
    }

    public static void main(String[] args) {
        while (true) {
            int a = (int) Math.random() * 9 + 1;

            if (a > 9) {
                System.out.println(a);
                break;
            }
        }
    }
}
