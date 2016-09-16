package com.projects.service.project;

import com.projects.shared.dto.ProjectDto;

import java.util.List;

/**
 * Created by Oleg Cherniak
 * version 1.0
 * 31.08.2016
 */
public interface ProjectService {

    void saveOrUpdate(ProjectDto projectDto);

    List<ProjectDto> getAll();

    void deleteProject(String id);

    ProjectDto findById(String id);

}
