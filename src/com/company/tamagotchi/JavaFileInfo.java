package com.company.tamagotchi;

public @interface JavaFileInfo {
    String name() default "author ";
    int month() default 1;
    int year() default 2000;
}
