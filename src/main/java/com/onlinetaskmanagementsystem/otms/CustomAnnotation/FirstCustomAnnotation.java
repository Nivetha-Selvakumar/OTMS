package com.onlinetaskmanagementsystem.otms.CustomAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) // Only to apply methods
public @interface FirstCustomAnnotation {
    String value() default "Default Value";
    int number() default 0;
}
