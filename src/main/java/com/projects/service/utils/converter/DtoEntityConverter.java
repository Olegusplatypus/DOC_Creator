package com.projects.service.utils.converter;

import com.projects.database.entity.ProjectEntity;
import com.projects.shared.dto.ProjectDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleg Cherniak
 * version 1.0
 * 31.08.2016
 */
@Component
public class DtoEntityConverter {
    public ProjectEntity convertToEntity(ProjectDto projectDto){
        if (projectDto == null){
            return null;
        }
        ProjectEntity projectEntity = new ProjectEntity();

        projectEntity.setId(projectDto.getId());
        projectEntity.setRootPath(projectDto.getRootPath());
        projectEntity.setName(projectDto.getName());
        projectEntity.setJarPath(projectDto.getJarPath());
        projectEntity.setDomainPath(projectDto.getDomainPath());
        return projectEntity;
    }
    public ProjectDto convertToDtoEntity(ProjectEntity projectEntity){
        if (projectEntity == null){
            return null;
        }
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(projectEntity.getId());
        projectDto.setName(projectEntity.getName());
        projectDto.setRootPath(projectEntity.getRootPath());
        projectDto.setJarPath(projectEntity.getJarPath());
        projectDto.setDomainPath(projectEntity.getDomainPath());
        return projectDto;
    }
    public List<ProjectDto> convertToDto(List<ProjectEntity> projectEntityList) {
        if (projectEntityList == null) {
            return null;
        }
        List<ProjectDto> projectDtos = new ArrayList<>();
        for (ProjectEntity entity : projectEntityList) {
            projectDtos.add(convertToDtoEntity(entity));
        }
        return projectDtos;
    }

}
