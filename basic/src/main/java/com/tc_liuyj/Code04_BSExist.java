package com.tc_liuyj;

/**
 * @author liuyajie
 * @date 2022/03/06/3:57 下午
 */
public class Code04_BSExist {

    public static boolean bsExist(int[] arr, int num) {

        if (arr == null || arr.length == 0) {
            return false;
        }

        int L = 0;

        int R = arr.length;

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
