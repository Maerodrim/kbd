package com.ssau.db.utils;

import java.util.HashSet;
import java.util.Set;

public class Utils {
    public static String getRandomString() {
        return "test";
    }

    public static Integer getRandomInteger() {
        return 0;
    }

    public static String getFullName() {
        return Const.names.get(rnd(Const.names.size())) + "  " + Const.fam.get(rnd(Const.fam.size()));
    }

    public static String getBrand() {
        return Const.brand.get(rnd(Const.brand.size()));
    }

    public static String getBody() {
        return Const.body.get(rnd(Const.body.size()));
    }

    public static Integer rnd(int max) {
        return (int) (Math.random() * max);
    }

    final static String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

    final static java.util.Random rand = new java.util.Random();

    // consider using a Map<String,Boolean> to say whether the identifier is being used or not
    final static Set<String> identifiers = new HashSet<String>();

    public static String randomIdentifier() {
        StringBuilder builder = new StringBuilder();
        while(builder.toString().length() == 0) {
            int length = rand.nextInt(5)+5;
            for(int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            if(identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }
}
