package net.deechael.conversation.util;

import java.util.Collection;
import java.util.Random;

public final class StrUtil {

    private final static String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String random(int length) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            builder.append(LETTERS.charAt(random.nextInt(LETTERS.length())));
        }
        return builder.toString();
    }

    public static String random(int length, Collection<String> excepts) {
        String result = random(length);
        while (excepts.contains(result))
            result = random(length);
        return result;
    }

    public static int count(String container, String content) {
        int length = content.length();
        if (length == 0)
            return 0;
        return (container.length() - container.replace(content, "").length()) / length;
    }

    public static boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private StrUtil() {
    }

}
