package com.tc_liuyj.class02;

/**
 * @author liuyajie
 * @date 2022/03/08/5:45 下午
 *
 *
 * ^ 异或运算： （无进位相加）
 *    相同为0， 不同为1， 如果俩个数异或的结果，在某个位置上是1，说明这俩个数在这个位置上是不一样的。  （无进位相加来理解）
 *    满足交换律和结合律  （无进位相加来理解）
 *    0^N=N ; N^N=0   （无进位相加来理解）
 */
public class Code02_EvenTimesOddTimes {


    // arr中，只有一种数，出现奇数次   arr数组中满足 只有一种数出现了奇数次，其他种数都出现了偶数次， 肯定需要全部遍历，因此时间复杂度最少为O(N)
    public static int printOddTimesNum1(int[] arr) {
        // 限制输入
        if (arr == null || arr.length == 0) {
            throw new IllegalStateException("arr 不能为空") ;
        }
        // 必须等于0
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        return eor;
    }

    // arr中，有俩种数，出现奇数次   其他数都出现偶数次， 要求时间复杂度为O(N)

    /**
     * 设： 这俩个奇数次的数为 A B
     * 1、 求A^B  若 A^B 的值在某一位上位1， 说明A、B 这俩个数在这个位置上不一样的。
     * 2、 数组可以 根据这个位置来划分， 这个位置上为1， 这个位置上不为1 俩部分，  A、B分别在这俩部分中， 其他的数都是偶数次的数
     *     对一部分中的所有数进行异或，就可以得到A或B，
     * 3、 第一步的结果异或上第二部的结果， 就可以得到另一个值
     * @param arr
     * @return
     */
    public static int printOddTimesNum2(int[] arr) {
        // 限制输入
        if (arr == null || arr.length == 0) {
            throw new IllegalStateException("arr 不能为空") ;
        }

        // 假设这俩种奇数次的数为A和B
        // 1、 求A^B
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        // 2. 求A或B
        // 2.1 求A^B 最后1
        int rightOne = eor & (-eor);  // 提取最右的1

        // 遍历arr求A或B
        int eorA = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & rightOne) != 0) {
                eorA ^= arr[i];
            }
        }
        int eorB = eor ^ eorA;
        return eor;
    }


    public static void main(String[] args) {
        Integer i = 1010;
        Integer j = 1010;
        i = i ^ j;
        j = i ^ j;
        i = i ^ j;
        System.out.println(i^j);
    }

}
