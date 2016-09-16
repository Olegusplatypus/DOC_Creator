package com.projects.service.utils;

import com.projects.database.entity.ProjectEntity;
import com.projects.shared.dto.ProjectDto;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;

/**
 * Created by Oleg Cherniak
 * version 1.0
 * 07.09.2016
 */
@Component
public class MethodsUtil {

    private List<Class> restAnnotations;

    @PostConstruct
    public void init() {
        restAnnotations = new ArrayList<>();
        restAnnotations.add(GET.class);
        restAnnotations.add(POST.class);
        restAnnotations.add(PUT.class);
        restAnnotations.add(DELETE.class);
    }
    public String docMethods(String className, ProjectDto projectDto) throws MalformedURLException, ClassNotFoundException, NoSuchFieldException {

        DocHelper docHelper = new DocHelper();

        Class<?> aClass = docHelper.loadClassViaJar(projectDto.getJarPath(), className);
        StringBuilder result = new StringBuilder();
        documentMethods(result,aClass,projectDto.getDomainPath());

        return result.toString();
    }
    public void documentMethods(StringBuilder builder,Class<?> aClass, String domain) throws NoSuchFieldException {

        builder.append("Main path: " + domain + "\n* <ul>\n");
        String pathAnnot = aClass.isAnnotationPresent(Path.class) ? aClass.getAnnotation(Path.class).value() : "";

        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            builder.append("* <li>");
            for (Class item:restAnnotations) {
                if (method.isAnnotationPresent(item)){
                    builder.append(item.getSimpleName() + " ");
                    break;
                }
            }
            String methPath = method.isAnnotationPresent(Path.class) ? method.getAnnotation(Path.class).value() : "";
            builder.append(pathAnnot + methPath + " - </li>\n");
        }
        builder.append("* </ul>\n");
    }
}
