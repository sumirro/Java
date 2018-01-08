package com.sumirrowu.javademo;

import java.lang.reflect.Field;

/**
 * Created by sumirrowu on 18/1/4.
 */

public class FUtil {
    public static void getFUtil(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields) {
            if(field.isAnnotationPresent(Name.class)) {
                Name name = field.getAnnotation(Name.class);
                System.out.println(name.value());
            }
            else if(field.isAnnotationPresent(Color.class)) {
                Color color = field.getAnnotation(Color.class);
                System.out.println(color.getFColor());
            }
        }
    }
}
