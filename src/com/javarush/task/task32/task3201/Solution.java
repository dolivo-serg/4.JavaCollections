package com.javarush.task.task32.task3201;

/*
Запись в существующий файл
*/

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class Solution {
    public static void main(String... args) throws IOException {
        RandomAccessFile file = new RandomAccessFile(args[0], "w");
        long position = Long.parseLong(args[1]);
        file.seek(Math.min(position, file.length()));
        file.write(args[2].getBytes());
    }
}
