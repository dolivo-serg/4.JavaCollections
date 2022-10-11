package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/* 
Уникальные подстроки
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        String[] words = s.replaceAll("[^a-zA-Z\\s]", "").trim().split("\\b\\s+\\b");
        return Arrays.stream(words)
                .map(word -> word.chars()
                        .mapToObj(c -> (char) c)
                        .collect(Collectors.toSet()))
                .collect(Collectors.toList()).stream()
                .map(set -> set.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining()))
                .max(Comparator.comparing(String::length))
                .get().length();
    }
}
