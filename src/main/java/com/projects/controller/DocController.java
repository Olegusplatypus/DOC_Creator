package com.projects.controller;

import com.projects.service.project.ProjectService;
import com.projects.service.utils.ClassesUtil;
import com.projects.service.utils.DocUtil;
import com.projects.service.utils.MethodsUtil;
import com.projects.shared.dto.ProjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


/**
 * Created by Oleg Cherniak
 * version 1.0
 * 01.09.2016
 */
@RestController
@RequestMapping("doc")
public class DocController {

    @Autowired
    private DocUtil docUtil;

    @Autowired
    private ClassesUtil classesUtil;

    @Autowired
    private MethodsUtil methodsUtil;

    @Autowired
    private ProjectService projectService;


    @RequestMapping(value = "/getdoc", method = RequestMethod.GET)
    public String docClass(@RequestParam("projectId") String id, @RequestParam("class") String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {
        ProjectDto projectDto = projectService.findById(id);
        String doc = docUtil.docClass(className, projectDto.getJarPath());
        return doc;
    }
    @RequestMapping(value = "/getmethods", method = RequestMethod.GET)
    public String docMethods(@RequestParam("projectId") String id, @RequestParam("class") String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException, NoSuchFieldException {
        ProjectDto projectDto = projectService.findById(id);
        String doc = methodsUtil.docMethods(className, projectDto);
        return doc;
    }
    @RequestMapping(value = "/classes", method = RequestMethod.GET)
    public List<String> findClasses(@RequestParam("projectId") String id, Model model) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {
        ProjectDto projectDto = projectService.findById(id);
        List<String> classes = classesUtil.findClasses(projectDto.getRootPath());
        return classes;
    }
}

