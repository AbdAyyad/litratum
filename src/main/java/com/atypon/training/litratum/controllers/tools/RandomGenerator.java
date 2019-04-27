package com.atypon.training.litratum.controllers.tools;

import java.util.Random;

public class RandomGenerator {

    private RandomGenerator() {
    }

    public static String getRandomString(int n) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            builder.append(Constants.ALL_ALLOWED_RANDOM.charAt(random.nextInt(Constants.ALL_ALLOWED_RANDOM.length())));
        }
        return builder.toString();
    }

    public static String getRandomString(){
        return getRandomString(20);
    }
}
