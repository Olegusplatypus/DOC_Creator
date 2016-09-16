package com.projects.service.utils.converter;

import com.projects.shared.request.ProjectRequest;
import com.projects.shared.dto.ProjectDto;
import org.springframework.stereotype.Component;

/**
 * Created by Oleg Cherniak
 * version 1.0
 * 31.08.2016
 */
@Component
public class RequestToDtoConverter {

    public ProjectDto convertToDtoEntity(ProjectRequest request) {
        if (request == null) {
            return null;
        }
        ProjectDto projectDto = new ProjectDto();
        if (!request.getId().equals("")) {
            projectDto.setId(request.getId());
        }
        projectDto.setName(request.getName());
        projectDto.setRootPath(request.getRootPath());
        projectDto.setJarPath(request.getJarPath());
        projectDto.setDomainPath(request.getDomainPath());
        return projectDto;
    }
}
