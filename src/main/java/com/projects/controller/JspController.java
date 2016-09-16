package com.projects.controller;

import com.projects.service.utils.converter.DtoToResponseConverter;
import com.projects.shared.dto.ProjectDto;
import com.projects.service.project.ProjectService;
import com.projects.service.utils.converter.RequestToDtoConverter;
import com.projects.shared.request.ProjectRequest;
import com.projects.shared.response.ProjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Oleg Cherniak
 * version 1.0
 * 30.08.2016
 */
@Controller
@RequestMapping(value = "project")
public class JspController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private RequestToDtoConverter requestToDtoConverter;

    @Autowired
    private DtoToResponseConverter dtoToResponseConverter;

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String jsp(Model model) {
        model.addAttribute("project", new ProjectRequest());
        return "save";
    }


    @PostMapping("/add")
    public String addProject(ProjectRequest projectRequest) {

        ProjectDto projectDto = requestToDtoConverter.convertToDtoEntity(projectRequest);
        projectService.saveOrUpdate(projectDto);

        return "redirect:/project/getall";
    }

    @GetMapping("/getall")
    public String getAllProjects(Model model) {
        List<ProjectDto> projectDtoList = projectService.getAll();
        List<ProjectResponse> projectResponseList = dtoToResponseConverter.convertToResponse(projectDtoList);
        model.addAttribute("allprojects", projectResponseList);
        return "AllProjects";
    }

    @GetMapping("/generator")
    public String toGenerate(Model model) {
        List<ProjectDto> projectDtoList = projectService.getAll();
        List<ProjectResponse> projectResponseList = dtoToResponseConverter.convertToResponse(projectDtoList);
        model.addAttribute("allprojects", projectResponseList);
        return "generator";
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String deleteProject(Model model, @PathVariable("id") String id) {
        projectService.deleteProject(id);
        List<ProjectDto> projectDtoList = projectService.getAll();
        List<ProjectResponse> projectResponseList = dtoToResponseConverter.convertToResponse(projectDtoList);
        model.addAttribute("allprojects", projectResponseList);
        return "redirect:/project/getall";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String updateProject(Model model, @PathVariable("id") String id) {
        ProjectDto projectDto = projectService.findById(id);
        ProjectResponse response = dtoToResponseConverter.convertToResponse(projectDto);
        model.addAttribute("project", response);
        return "save";
    }


}
