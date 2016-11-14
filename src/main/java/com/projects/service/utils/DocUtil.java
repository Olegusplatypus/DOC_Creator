package com.projects.service.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

import org.joda.time.DateTime;

import java.net.MalformedURLException;
import java.util.*;

/**
 * Created by Oleg Cherniak
 * version 1.0
 * 26.08.2016
 * Used to create JSON form from class
 */
@Component
public class DocUtil {

    @Autowired
    private ClassesUtil classesUtil;


    private Set<String> packages;

    private List<Class> baseClasses;



    @PostConstruct
    public void init() {
        baseClasses = new ArrayList<>();
        baseClasses.add(String.class);
        baseClasses.add(Double.class);
        baseClasses.add(Float.class);
        baseClasses.add(Boolean.class);
        baseClasses.add(Long.class);
        baseClasses.add(Integer.class);
        baseClasses.add(DateTime.class);
    }


    public String docClass(String className, String path) throws ClassNotFoundException, InstantiationException, IllegalAccessException, MalformedURLException {

        packages = classesUtil.getPackages();

        DocHelper docHelper = new DocHelper();

        Class<?> aClass = docHelper.loadClassViaJar(path,className);

        // Execute 2 stringbuilders in JSON and DOC formats
        StringBuilder docBuilder = new StringBuilder();
        StringBuilder JSONBuilder = new StringBuilder();
        printDataWithCaret(JSONBuilder, docBuilder, "<code>\n", 1,docHelper);
        docBuilder.insert(docBuilder.length() - 1, "* ");
        documentClass(JSONBuilder, docBuilder, aClass, 1,docHelper);
        printDataWithCaret(JSONBuilder, docBuilder, "</code>", 1,docHelper);
        String result = "DOC FORMAT\n\n" + docBuilder +
                "**********************************************************************\n" +
                "JSON FORMAT\n\n" + JSONBuilder;
        return result;
    }

    private void documentClass(StringBuilder JSONBuilder, StringBuilder docBuilder, Class<?> aClass, int depth, DocHelper docHelper) throws IllegalAccessException, InstantiationException {

        if (aClass == null) {
            return;
        }
        JSONBuilder.insert(JSONBuilder.length() - 1, "{");
        docBuilder.insert(docBuilder.length() - 1, "{");

        int depth1 = depth + 4;

        Field[] declaredFields = aClass.getDeclaredFields();

        for (Field declaredField : declaredFields) {

            String name;
            // check for @JsonProperty value and use if exists
            if (declaredField.isAnnotationPresent(JsonProperty.class) &&
                    !declaredField.getAnnotation(JsonProperty.class).value().isEmpty()) {
                name = declaredField.getAnnotation(JsonProperty.class).value();
            } else {
                name = declaredField.getName();
            }
            if (!name.equals("serialVersionUID")) {
                Class<?> type = declaredField.getType();
                // case if field is base class
                if (baseClasses.contains(type)) {
                    writeAsBaseObject(JSONBuilder, docBuilder, declaredField, name, type, depth1,docHelper);
                } else {
                    // case if field is collection
                    if (Collection.class.isAssignableFrom(type)) {
                        writeAsCollection(JSONBuilder, docBuilder, name, declaredField, depth1,docHelper);
                        // case if field is map
                    } else if (Map.class.isAssignableFrom(type)) {
                        writeAsMap(JSONBuilder, docBuilder, name, depth1,docHelper);
                        // case if field is entity class
                    } else if (type.isEnum()) {
                        writeAsBaseObject(JSONBuilder, docBuilder, declaredField, name, type, depth1, docHelper);
                    } else if (packages.contains(type.getPackage().toString().split(" ")[1])) {
                        writeAsEntityObject(JSONBuilder, docBuilder, name, type, depth1, docHelper);
                    } else {
                        writeAsBaseObject(JSONBuilder, docBuilder, declaredField, name, type, depth1,docHelper);
                    }
                }
                docHelper.writeComa(declaredFields, declaredField, JSONBuilder, docBuilder);
            }
        }
        printDataWithCaret(JSONBuilder, docBuilder, "}", depth,docHelper);
    }



    private void printDataWithCaret(StringBuilder JSONBuilder, StringBuilder docBuilder, String data, int depth,DocHelper docHelper) {
        JSONBuilder.append(docHelper.getOffsetByDepth(depth) + data + "\n");
        docBuilder.append("*" + docHelper.getOffsetByDepth(depth) + data + "\n");
    }

    private void writeAsBaseObject(StringBuilder JSONBuilder, StringBuilder docBuilder, Field field, String name, Class<?> type, int depth,DocHelper docHelper) {
        String notNull = field.isAnnotationPresent(NotNull.class) ? " (NOT NULL)" : "";
        String simpleName = baseClasses.contains(type) ? type.getSimpleName() :"String" ;

        printDataWithCaret(JSONBuilder, docBuilder, "\"" + name + "\":\"(" + simpleName + ")" + notNull + "\"", depth, docHelper);
    }

    private void writeAsEntityObject(StringBuilder JSONBuilder, StringBuilder docBuilder, String name, Class<?> type, int depth, DocHelper docHelper) throws InstantiationException, IllegalAccessException {
        printDataWithCaret(JSONBuilder, docBuilder, "\"" + name + "\":", depth,docHelper);
        int depth1 = depth + 4;
        depth1 = depth1 + name.length();
        documentClass(JSONBuilder, docBuilder, type, depth1,docHelper);
    }

    private void writeAsCollection(StringBuilder JSONBuilder, StringBuilder docBuilder, String name, Field declaredField, int depth, DocHelper docHelper) throws InstantiationException, IllegalAccessException {
        printDataWithCaret(JSONBuilder, docBuilder, "\"" + name + "\":[", depth,docHelper);
        int depth1 = depth + 4;
        depth1 = depth1 + name.length();
        ParameterizedType colType = (ParameterizedType) declaredField.getGenericType();
        Class<?> parameter = (Class<?>) colType.getActualTypeArguments()[0];
        if (baseClasses.contains(parameter)) {
            printDataWithCaret(JSONBuilder, docBuilder, "\"Some (" + parameter.getSimpleName() + ") data\"", depth1,docHelper);
        } else {
            documentClass(JSONBuilder, docBuilder, parameter, depth1,docHelper);
        }

        printDataWithCaret(JSONBuilder, docBuilder, "]", depth1 - 1,docHelper);
    }

    private void writeAsMap(StringBuilder JSONBuilder, StringBuilder docBuilder, String name, int depth, DocHelper docHelper) throws InstantiationException, IllegalAccessException {
        printDataWithCaret(JSONBuilder, docBuilder, "\"" + name + "\":", depth, docHelper);
        int depth1 = depth + 4;
        depth1 = depth1 + name.length();
        printDataWithCaret(JSONBuilder, docBuilder, "{\"someJSONobject\": \"value\"}", depth1, docHelper);

    }
}
