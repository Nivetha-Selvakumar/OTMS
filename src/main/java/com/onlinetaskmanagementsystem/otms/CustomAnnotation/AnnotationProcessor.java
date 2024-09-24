package com.onlinetaskmanagementsystem.otms.CustomAnnotation;

import java.lang.reflect.Method;

public class AnnotationProcessor {

    public static void main(String[] args) {
        try {
            // Get the AnnotationImplementation class object
            Class<AnnotationImplementation> obj = AnnotationImplementation.class; //AnnotationImplementation a= new AnnotationImplementation();

            // Iterate through all the methods of AnnotationImplementation
            for (Method method : obj.getDeclaredMethods()) {

                // Check if FirstCustomAnnotation is present on the method
                if (method.isAnnotationPresent(FirstCustomAnnotation.class)) {

                    // Get the annotation
                    FirstCustomAnnotation annotation = method.getAnnotation(FirstCustomAnnotation.class);

                    // Access annotation values
                    System.out.println("Method: " + method.getName());
                    System.out.println("Value: " + annotation.value());
                    System.out.println("Number: " + annotation.number());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
