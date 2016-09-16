package com.projects.service.utils;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Oleg Cherniak
 * version 1.0
 * 01.09.2016
 * Used to get all classes from package
 */
@Component
public class ClassesUtil {
    private List<String> classes;

    public List<String> findClasses(String rootPath) {
        classes = new ArrayList<>();
        fillList(rootPath);
        Collections.sort(classes);
        return classes;
    }

    public void fillList(String path) {

        File folder = new File(path);

        File[] folderEntries = folder.listFiles();

        for (File file : folderEntries) {

            if (file.isDirectory()) {

                fillList(file.getPath());
            }
            else {
                // build string in format: className (rootPackage.package2....packageN)
                if (file.getName().contains(".java")) {
                    StringBuilder rootBuilder = new StringBuilder(file.getName().replace(".java", " ()"));
                    File file1 = file.getParentFile();
                    StringBuilder result = new StringBuilder(rootBuilder);
                    result.insert(rootBuilder.length() - 1, file1.getName());

                    while(!file1.getParentFile().getName().equals("java")){
                        file1 = file1.getParentFile();
                        if (file1.getName().equals("src")){
                            break;
                        }
                        result.insert(rootBuilder.length() - 1, file1.getName() + ".");
                    }
                    classes.add(result.toString());
                }
            }
        }
    }
}

// if (file.getName().contains(".java")) {
//         StringBuilder result = new StringBuilder(file.getName().replace(".java", ""));
//         while (!file.getParentFile().getName().equals("java")) {
//         file = file.getParentFile();
//         if (file.getName().equals("src")){
//         break;
//         }
//         result.insert(0, file.getName() + ".");
//         }
//         classes.add(result.toString());
//         }