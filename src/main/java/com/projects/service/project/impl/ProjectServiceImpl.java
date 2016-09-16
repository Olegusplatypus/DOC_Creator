package com.projects.service.project.impl;

import com.projects.database.entity.ProjectEntity;
import com.projects.database.repository.ProjectRepository;
import com.projects.service.utils.converter.DtoEntityConverter;
import com.projects.shared.dto.ProjectDto;
import com.projects.service.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Oleg Cherniak
 * version 1.0
 * 31.08.2016
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
     private DtoEntityConverter dtoEntityConverter;


    @Override
    public void saveOrUpdate(ProjectDto projectDto) {

        ProjectEntity entity = dtoEntityConverter.convertToEntity(projectDto);
        projectRepository.save(entity);

    }

    @Override
    public List<ProjectDto> getAll() {
        List<ProjectEntity> projectEntityList = projectRepository.findAll();
        List<ProjectDto> projectDtos = dtoEntityConverter.convertToDto(projectEntityList);
        return projectDtos;
    }

    @Override
    public void deleteProject(String id) {
        projectRepository.delete(id);
    }

    @Override
    public ProjectDto findById(String id) {
        ProjectEntity projectEntity = projectRepository.findOne(id);
        ProjectDto projectDto = dtoEntityConverter.convertToDtoEntity(projectEntity);
        return projectDto;
    }

}
