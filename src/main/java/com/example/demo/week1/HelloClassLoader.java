package com.example.demo.week1;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class clazz = new HelloClassLoader().findClass("Hello");
        clazz.getDeclaredMethod("hello").invoke(clazz.newInstance());
    }

    @Override
    protected Class<?> findClass(String name) {
        try {
            byte[] bytes = FileUtils.readFileToByteArray(
                    new File("/Users/malina/Developer/demo/Hello.xlass"));
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (255 - Byte.toUnsignedInt(bytes[i]));
            }
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}