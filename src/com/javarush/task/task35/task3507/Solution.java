package com.javarush.task.task35.task3507;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/

public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(
                Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() +
                        Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }


    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        if (!pathToAnimals.endsWith("\\") && !pathToAnimals.endsWith("/"))
            pathToAnimals = pathToAnimals + "/";

        File directory = new File(pathToAnimals);
        String[] classFiles = directory.list((dir, name) -> name.toLowerCase().endsWith(".class"));
        if (classFiles == null) {
            return new HashSet<>();
        } else {
            ClassLoader loader = createClassLoader(pathToAnimals);
            return getSetAnimalsClasses(loader, classFiles);
        }

    }
    
    private static Set<Animal> getSetAnimalsClasses(ClassLoader loader,String... classFiles) {
        Set<Animal> result = new HashSet<>();
        if (classFiles.length == 0) {
            return result;
        }
        for (String path : classFiles) {
            try {
                String className = path.substring(0, path.length() - 6);
                Class clazz = loader.loadClass(className);
                
                if (!isHasInterface(false, clazz)) continue;
                
                if (!isHasConstructor(false, clazz)) continue;
                
                result.add((Animal) clazz.newInstance());
            } catch (Exception ignored) {
            }
        }
        return result;
    }

    private static boolean isHasConstructor(boolean hasConstructor, Class clazz) {
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor c : constructors) {
            if (c.getParameterTypes().length == 0) {
                hasConstructor = true;
                break;
            }
        }
        return hasConstructor;
    }

    private static boolean isHasInterface(boolean hasInterface, Class clazz) {
        Class[] interfaces = clazz.getInterfaces();
        for (Class i : interfaces) {
            if (Animal.class == i) {
                hasInterface = true;
                break;
            }
        }
        return hasInterface;
    }

    private static ClassLoader createClassLoader(String pathToDirectory) {
        return new ClassLoader() {
            @Override
            public Class<?> findClass(String className) throws ClassNotFoundException {
                try {
                    byte[] b = fetchClassFromFS(pathToDirectory + className + ".class");
                    return defineClass(null, b, 0, b.length);
                } catch (IOException e) {
                    return super.findClass(className);
                }
            }
        };
    }

    private static byte[] fetchClassFromFS(String path) throws IOException {
        InputStream is = Files.newInputStream(new File(path).toPath());
        // Get the size of the file
        long length = new File(path).length();
        if (length > Integer.MAX_VALUE) {
            throw new IOException("File is too large");
        }
        // Create the byte array to hold the data
        byte[] bytes = new byte[(int) length];
        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + path);
        }
        is.close();
        return bytes;
    }
}
