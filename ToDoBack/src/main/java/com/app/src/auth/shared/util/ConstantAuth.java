package com.app.src.auth.shared.util;

import java.util.regex.Pattern;

public class ConstantAuth {


    private static final String MAIL_REGEX = "^(.+)@(.+)$";
    public static final Pattern PATTERN_MAIL = Pattern.compile(MAIL_REGEX);
    public static  final  int PARAM_MIN_LENGTH_PASS = 6;
}
