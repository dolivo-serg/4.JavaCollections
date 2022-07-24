package com.javarush.task.task31.task3113;

/*
Что внутри папки?
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Solution {
    //    public static void main(String[] args) throws IOException {
//        Path pathToDirectory = enterPath();
//        if (!Files.isDirectory(pathToDirectory)) {
//            System.out.println(pathToDirectory + " - не папка");
//            return;
//        }
//
//        //Всего папок - [количество папок в директории и поддиректориях]
//        long countDirectory = Files.walk(pathToDirectory)
//                .filter(Files::isDirectory)
//                .count() - 1;
//        System.out.println("Всего папок - " + countDirectory);
//
//        //Всего файлов - [количество файлов в директории и поддиректориях]
//        long countFiles = Files.walk(pathToDirectory)
//                .filter(Files::isRegularFile)
//                .count();
//        System.out.println("Всего файлов - " + countFiles);
//
//        //Общий размер - [общее количество байт, которое хранится в директории]
//        long countBytes = Files.size(pathToDirectory);
//        System.out.println("Общий размер - " + countBytes + " байт");
//    }
//
//    private static Path enterPath() {
//        Path path = null;
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
//            path = Paths.get(reader.readLine());
//        } catch (IOException e) {
//            System.out.println("try again...");
//            enterPath();
//        }
//        return path;
//    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String dirName = reader.readLine();

        Path directory = Paths.get(dirName);

        if (!Files.isDirectory(directory)) {
            System.out.println(directory + " - не папка.");
        } else {
            AtomicInteger folderCounter = new AtomicInteger();
            AtomicInteger fileCounter = new AtomicInteger();
            AtomicLong sizeCounter = new AtomicLong();

            Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    if (!dir.equals(directory)) folderCounter.incrementAndGet();
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    fileCounter.incrementAndGet();
                    sizeCounter.addAndGet(attrs.size());
                    return FileVisitResult.CONTINUE;
                }
            });

            System.out.println("Параметры папки " + directory.toString() + ":");
            System.out.println("Всего папок - " + folderCounter.get());
            System.out.println("Всего файлов - " + fileCounter.get());
            System.out.println("Общий размер - " + sizeCounter.get() + " байт");
        }
    }
}
