package com.tc_liuyj.class02;

/**
 * @author liuyajie
 * @date 2022/03/08/7:27 下午
 *
 *
 *
 *
 * 在arr中，只有一种数出现了K次，其他数都出现了M次， 求出现K次的这种数
 *
 *
 * 一个数有一个32位的二进制数，
 * 这个集合中 32B位 上的所有数据相加
 */
public class Code03_KM {

    public static int onlyKTimes(int[] arr, int k, int m) {
        /**
         * 第0个: 存放 数组中所有数字的二进制数的第0位上数字的和
         * 第1个: 存放 数组中所有数字的二进制数的第1位上数字的和
         * 第2个: 存放 数组中所有数字的二进制数的第2位上数字的和
         * 。
         * 。
         * 。
         * 第31个: 存放 数组中所有数字的二进制数的第31位上数字的和
         */
        int[] t = new int[32];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 32; j++) {
                t[i] += arr[i] >> i;
            }
        }

        int ans = 0;
        for (int i = 0; i < t.length; i++) {
            if (t[i] % m != 0) {
                ans |= 1<<i;
            }
        }
        // 得到的ans, 出现的真实次数
        int real = 0;
        for (int num : arr) {
            if (num == ans) {
                real++;
            }
        }
        return real == k ? ans : -1;
    }
    public static void main(String[] args) {


    }
}
