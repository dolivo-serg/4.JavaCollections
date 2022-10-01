package com.javarush.task.task38.task3803;

/*
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object obj = new VeryComplexClass();
        Integer integer = (Integer) obj;
    }

    public void methodThrowsNullPointerException() {
        String string = null;
        string.toLowerCase();
    }

    public static void main(String[] args) {

    }
}
