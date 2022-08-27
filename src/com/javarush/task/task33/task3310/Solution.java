package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        long elementsNumber = 10000;

        testStrategy(new FileStorageStrategy(), elementsNumber);

        testStrategy(new HashMapStorageStrategy(), elementsNumber);

        testStrategy(new OurHashMapStorageStrategy(), elementsNumber);

        testStrategy(new OurHashBiMapStorageStrategy(), elementsNumber);

        testStrategy(new HashBiMapStorageStrategy(), elementsNumber);

        testStrategy(new DualHashBidiMapStorageStrategy(), elementsNumber);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        return strings.stream()
                .map(shortener::getId)
                .collect(Collectors.toSet());
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        return keys.stream()
                .map(shortener::getString)
                .collect(Collectors.toSet());
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        String strategyName = strategy.getClass().getSimpleName();
        System.out.println(strategyName);

        Set<String> testSet = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            testSet.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);

        long timeStart = new Date().getTime();
        Set<Long> testSetIDs = getIds(shortener, testSet);
        long timeEnd = new Date().getTime();
        System.out.println("Test getIds: " + (timeEnd - timeStart) + "ms");

        timeStart = new Date().getTime();
        Set<String> resultSet = getStrings(shortener, testSetIDs);
        timeEnd = new Date().getTime();
        System.out.println("Test getStrings: " + (timeEnd - timeStart) + "ms");

        System.out.println(testSet.equals(resultSet) ? "Тест пройден." : "Тест не пройден.");
    }
}