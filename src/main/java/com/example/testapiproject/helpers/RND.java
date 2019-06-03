package com.example.testapiproject.helpers;

public class RND {

    /**
     * Генерация произвольной строки указанной длины, состоящей из цифр и букв латинского алфавита
     * @param length длина строки
     * @return произвольная строка длины @length
     */
    public static String getRandomString(int length) {

        String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder builder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = (int) (alphaNumericString.length() * Math.random());
            builder.append(alphaNumericString.charAt(index));
        }
        return builder.toString();
    }
}
