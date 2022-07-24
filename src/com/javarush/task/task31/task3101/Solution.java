package com.javarush.task.task31.task3101;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.Map;
import java.util.TreeMap;

/*
Проход по дереву файлов
*/

    public class Solution {
        public static void main(String[] args) {
            String path = args[0];
            String resultFileAbsolutePath = args[1];
            try {
                File resultFile = new File(resultFileAbsolutePath);
                File dest = new File(resultFile.getParentFile() + "/allFilesContent.txt");
                if (FileUtils.isExist(dest)) {
                    FileUtils.deleteFile(dest);
                }
                FileUtils.renameFile(resultFile, dest);

                Map<String, byte[]> fileTree = getFileTree(path);
                try (FileOutputStream fileOutputStream = new FileOutputStream(dest)) {
                    for (byte[] bytes : fileTree.values()) {
                        fileOutputStream.write(bytes);
                        fileOutputStream.write("\n".getBytes());
                    }
                }
            } catch (IOException ignored) {
            }
        }

        public static Map<String, byte[]> getFileTree(String root) throws IOException {
            Map<String, byte[]> result = new TreeMap<>();

            EnumSet<FileVisitOption> options = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
            Files.walkFileTree(Paths.get(root), options, 20, new GetFiles(result));

            return result;
        }

        private static class GetFiles extends SimpleFileVisitor<Path> {
            private Map<String, byte[]> result;

            public GetFiles(Map<String, byte[]> result) {
                this.result = result;
            }

            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
                File file = path.toFile();
                if (file.isFile()) {
                    if (file.length() <= 50) {
                        result.put(path.getFileName().toString(), Files.readAllBytes(path));
                    }
                }
                return super.visitFile(path, basicFileAttributes);
            }
        }
    }


//    public static void main(String[] args) {
//        if (args.length != 2) {
//            return;
//        }
//        String path = args[0];
//        String resultFileAbsolutePath = args[1];
//        File allFilesContent = getFile(resultFileAbsolutePath);
//        perform(allFilesContent, Collections.singletonList(new File(path)));
//    }
//
//    private static File getFile(String resultFileAbsolutePath) {
//        File providedFile = new File(resultFileAbsolutePath);
//        if (FileUtils.isExist(providedFile)) {
//            FileUtils.deleteFile(providedFile);
//        }
//        if (FileUtils.isExist(providedFile)) {
//            FileUtils.renameFile(providedFile, new File(providedFile.getParentFile() + "\\allFilesContent.txt"));
//        }
//        return providedFile;
//    }
//
//    private static boolean isValidFileLength(File file) {
//        return file.length() > 50;
//    }
//
//    private static void perform(File resultFile, List<File> files) {
//        for (File file : files) {
//            System.out.println(file);
//            if (!file.isDirectory()) {
//                if (isValidFileLength(file)) {
//                    executedWriteFile(resultFile, file);
//                }
//            } else {
//                File[] directory = file.listFiles();
//                List<File> list = Arrays.asList(Objects.requireNonNull(directory));
//                perform(resultFile, list);
//            }
//        }
//    }
//
//    private static void executedWriteFile(File resultFile, File file) {
//        Objects.requireNonNull(file);
//        Objects.requireNonNull(resultFile);
//        ExecutorService executor = Executors.newFixedThreadPool(4);
//        executor.execute(() -> writeFile(resultFile, file));
//        executor.shutdown();
//    }
//
//    private static void writeFile(File resultFile, File file) {
//        try (BufferedReader reader = new BufferedReader(new FileReader(file));
//             PrintWriter writer = new PrintWriter(resultFile)) {
//            reader.lines().forEach(writer::write);
//            writer.write("\n");
//        } catch (IOException e) {
//            e.getStackTrace();
//        }
//    }

