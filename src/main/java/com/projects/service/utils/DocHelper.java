package com.projects.service.utils;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by Oleg Cherniak
 * version 1.0
 * 07.09.2016
 * Service class that implements functions for other services
 */
public class DocHelper {

    // changes class format from "className (rootPackage.package2....packageN)" to "rootPackage.package2....packageN.className"
    public String changeClassFormat(String className){
        String [] array = className.split(" ");
        StringBuilder result = new StringBuilder(array[1]);
        result.deleteCharAt(0).deleteCharAt(result.length() - 1).append("." + array[0]);
        return result.toString();
    }

    public String getOffsetByDepth(int depth) {
        String offset = "";
        String baseOffset = " ";
        for (int i = 0; i < depth; i++) {
            offset += baseOffset;
        }
        return offset;
    }
    public void writeComa(Field[] declaredFields, Field pointField, StringBuilder JSONBuilder, StringBuilder docBuilder) {
        for (int i = 0; i < declaredFields.length; i++) {
            if (pointField == declaredFields[i]) {
                if (i < declaredFields.length - 1) {
                    JSONBuilder.insert(JSONBuilder.length() - 1, ",");
                    docBuilder.insert(docBuilder.length() - 1, ",");
                }
                break;
            }
        }
    }
    public Class<?> loadClassViaJar(String path, String className) throws MalformedURLException, ClassNotFoundException {
        URL[] classLoaderUrls = new URL[]{new URL("File:///" + path)};
        // Create a new URLClassLoader
        URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);

        String validName = this.changeClassFormat(className);

        // Load the target class
        Class<?> aClass = urlClassLoader.loadClass(validName);

        return aClass;
    }
}
