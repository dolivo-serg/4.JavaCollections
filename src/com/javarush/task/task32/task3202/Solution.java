package com.javarush.task.task32.task3202;

import java.io.*;
import java.util.Objects;

/* 
Читаем из потока
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("testFile.log"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        if (Objects.isNull(is)){
          return new StringWriter();
        }
        try (StringWriter writer = new StringWriter();
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            reader.lines().forEach(writer::write);
            return writer;
        }
    }
}
