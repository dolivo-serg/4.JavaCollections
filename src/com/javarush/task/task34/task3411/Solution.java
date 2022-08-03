package com.javarush.task.task34.task3411;

/* 
Ханойские башни
*/

public class Solution {
    public static void main(String[] args) {
        int numRings = 8;
        moveRing('A', 'B', 'C', numRings);
    }

    /**
     * Описание алгоритма :
     * -Если кольцо одно, то печатаем "from " + a + " to " + b и выходим из метода.
     * -Если же не одно, то:
     *  Вызываем перемещение numRings-1 колец с 'а' на 'с'
     *  печатаем "from " + a + " to " + b
     *  вызываем перемещение numRings-1 колец с 'c' на 'b'
     */
    public static void moveRing(char a, char b, char c, int numRings) {
        if (numRings == 1) {
            System.out.println("from " + a + " to " + b);
        } else {
            moveRing(a, c, b, numRings - 1);
            System.out.println("from " + a + " to " + b);
            moveRing(c, b, a, numRings - 1);
        }
    }
}