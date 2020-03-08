package com.rookie.bigdata.banner;

import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

/**
 * @author rookie
 * @version 1.0
 * @date 2020/3/8 14:37
 */
public class Mybanner implements Banner {

    private static final String BANNER = "Spring Mybanner";
    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {

        System.out.println(BANNER);
    }
}
