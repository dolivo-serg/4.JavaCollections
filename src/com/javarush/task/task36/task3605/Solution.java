package com.javarush.task.task36.task3605;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.TreeSet;
import java.util.stream.Stream;

/* 
Использование TreeSet
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        TreeSet<String> treeSet = new TreeSet<>();

        Files.lines(Paths.get(args[0]))
                .flatMap(str -> Stream.of(str.split("")))
                .map(String::toLowerCase)
                .forEach(treeSet::add);

        treeSet.stream()
                .filter(str -> str.matches("[A-Za-z]"))
                .limit(5)
                .forEach(System.out::print);
    }
}
