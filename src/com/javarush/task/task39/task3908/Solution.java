package com.javarush.task.task39.task3908;

/* 
Возможен ли палиндром?
*/

import java.util.Arrays;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("ete"));
    }

    public static boolean isPalindromePermutation(String s) {
        Map<String, Long> symbols = Arrays.stream(s.toLowerCase().split(""))
                .collect(Collectors.groupingBy(UnaryOperator.identity(), Collectors.counting()));
        return symbols.values().stream().filter(value -> value % 2 != 0).count() <= 1;
    }

/*
    public static boolean isPalindromePermutation(String s) {
        boolean foundOdd = false;
        int[] tableCount = new int[256];

        for (char c : s.toLowerCase().toCharArray()) {
            tableCount[c] += 1;
        }

        for (int count : tableCount) {
            if (count % 2 != 0) {
                if (foundOdd) {
                    return false;
                }
                foundOdd = true;
            }
        }

        return true;
    }
 */

}
