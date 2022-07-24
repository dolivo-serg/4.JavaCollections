package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.ThreadLocalRandom;

/* 
Генератор паролей
*/

public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        int size = 8;
        ByteArrayOutputStream aos = new ByteArrayOutputStream();
        for (int i = 0; i < size/4; i++) {
            aos.write(generateNum());
            aos.write(generateCharLowerCase());
            aos.write(generateCharUpperCase());
            aos.write(generateNum());
        }
        return aos;
    }

    private static char generateNum() {
        return (char) ThreadLocalRandom.current().nextInt(48,57);
    }
    private static char generateCharUpperCase() {
        return (char) ThreadLocalRandom.current().nextInt(65,90);
    }
    private static char generateCharLowerCase() {
        return (char) ThreadLocalRandom.current().nextInt(97,122);
    }

}
