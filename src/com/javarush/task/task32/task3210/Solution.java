package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Solution {
    public static void main(String... args) {
        try (RandomAccessFile raf = new RandomAccessFile(args[0], "rw")) {
            String text = args[2];

            raf.seek(Long.parseLong(args[1]));
            byte[] readBytes = new byte[text.length()];
            raf.read(readBytes, 0, text.length());

            String readText = new String(readBytes);

            raf.seek(raf.length());
            if (readText.equals(text)) {
                raf.write("true".getBytes());
            } else {
                raf.write("false".getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
