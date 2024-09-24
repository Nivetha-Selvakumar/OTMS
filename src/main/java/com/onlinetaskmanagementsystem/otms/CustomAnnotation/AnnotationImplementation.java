package com.onlinetaskmanagementsystem.otms.CustomAnnotation;

public class AnnotationImplementation {

    @FirstCustomAnnotation(value = "Value",number = 5)
    public void myMethod() {
        System.out.println("This is a method with a custom annotation.");
    }
}
