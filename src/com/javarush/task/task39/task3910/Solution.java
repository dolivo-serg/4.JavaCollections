package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(isPowerOfThree(729));
    }

    public static boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
/*
        return Math.floor(Math.log(n) / Math.log(3)) - Math.floor(n) < 0.000001;
*/

/*
        for (int i = 1; i <= n; i *= 3) {
            if (i == n) return true;
        }
        return false;
*/

/*
        if (n < 1) return false;

        while (n % 3 == 0) n /= 3;

        return n == 1;
*/
    }
}
