package com.sumirrowu.javademo;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by sumirrowu on 18/1/5.
 */

public class ViewUtils {
    public static void setContentView(final Activity activity) {
        final Class clazz = activity.getClass();
        if (clazz.isAnnotationPresent(ContentView.class)) {
            ContentView contentView = (ContentView) clazz.getAnnotation(ContentView.class);
            int layoutId = contentView.value();
            try {
                Method method = clazz.getMethod("setContentView", int.class);
                method.setAccessible(true);
                method.invoke(activity, layoutId);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            //activity.setContentView(layoutId);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(ViewTarget.class)) {
                    ViewTarget viewTarget = field.getAnnotation(ViewTarget.class);
                    int viewId = viewTarget.value();
                    try {
                        Method method = clazz.getMethod("findViewById", int.class);
                        method.setAccessible(true);
                        View view = (View) method.invoke(activity, viewId);
                        field.set(activity, view);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }

            Method[] methods = clazz.getDeclaredMethods();
            for(final Method method : methods) {
                if(method.isAnnotationPresent(OnClick.class)) {
                    OnClick clickAction = method.getAnnotation(OnClick.class);
                    int viewId = clickAction.value();
                    View view = activity.findViewById(viewId);
                    try {
                        Method clickMd = View.class.getMethod("setOnClickListener", View.OnClickListener.class);
                        clickMd.setAccessible(true);
                        clickMd.invoke(view, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    method.invoke(activity);
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}
