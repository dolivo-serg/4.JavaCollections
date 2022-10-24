package com.javarush.task.task39.task3905;

/*
Залей меня полностью
*/

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Solution {
    private static final String RESET = "\u001B[0m";
    private static final String[] COLORS = new String[Color.values().length];

    static {
        COLORS[Color.RED.ordinal()] = "\u001B[41m";
        COLORS[Color.ORANGE.ordinal()] = "\u001B[47m";
        COLORS[Color.YELLOW.ordinal()] = "\u001B[43m";
        COLORS[Color.GREEN.ordinal()] = "\u001B[42m";
        COLORS[Color.BLUE.ordinal()] = "\u001B[44m";
        COLORS[Color.INDIGO.ordinal()] = "\u001B[46m";
        COLORS[Color.VIOLET.ordinal()] = "\u001B[45m";
    }

    public static void main(String[] args) {
        final Color[][] matrix = new Color[10][10];
        final PhotoPaint photoPaint = new PhotoPaint();
        final Random random = new Random();

        fill(matrix);
        print(matrix);
        photoPaint.paintFill(
                matrix,
                random.nextInt(matrix[0].length),
                random.nextInt(matrix.length),
                Color.INDIGO
        );
        System.out.println();
        print(matrix);
    }

    private static void fill(Color[][] matrix) {
        final Random random = new Random();
        final int colorsCount = Color.values().length;

        Arrays.stream(matrix)
                .forEach(row -> IntStream.range(0, row.length)
                        .forEach(x -> row[x] = Color.values()[random.nextInt(colorsCount)]));

    }

    private static void print(Color[][] matrix) {
        for (Color[] row : matrix) {
            for (Color cell : row) {
                System.out.printf("%6s ", formatColor(cell));
            }
            System.out.println();
        }
    }

    public static String formatColor(Color cell) {
        return COLORS[cell.ordinal()] + "cell" + RESET;
    }
}
