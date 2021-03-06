package com.projects.shared.request;

import java.io.Serializable;

/**
 * Created by Oleg Cherniak
 * version 1.0
 * 31.08.2016
 */
public class ProjectRequest implements Serializable{

    private String id;

    private String name;

    private String rootPath;

    private String jarPath;

    private String domainPath;

    public String getDomainPath() {
        return domainPath;
    }

    public void setDomainPath(String domainPath) {
        this.domainPath = domainPath;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getJarPath() {
        return jarPath;
    }

    public void setJarPath(String jarPath) {
        this.jarPath = jarPath;
    }
}
