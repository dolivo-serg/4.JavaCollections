package com.javarush.task.task35.task3502;

/*
Знакомство с дженериками
*/

import java.util.List;

public class Solution<R extends List<Solution.SomeClass>> {
    public static class SomeClass<T extends Number> {
    }

    public static void main(String[] args) {

    }
}
