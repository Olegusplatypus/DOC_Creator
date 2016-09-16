package com.projects.service.utils.converter;

import com.projects.shared.response.ProjectResponse;
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
public class DtoToResponseConverter {
    public ProjectResponse convertToResponse(ProjectDto projectDto) {
        if (projectDto == null) {
            return null;
        }
        ProjectResponse response = new ProjectResponse();
        response.setId(projectDto.getId());
        response.setName(projectDto.getName());
        response.setRootPath(projectDto.getRootPath());
        response.setJarPath(projectDto.getJarPath());
        response.setDomainPath(projectDto.getDomainPath());
        return response;
    }

    public List<ProjectResponse> convertToResponse(List<ProjectDto> projectDtoList) {
        if (projectDtoList == null) {
            return null;
        }
        List<ProjectResponse> projectResponseList = new ArrayList<>();
        for (ProjectDto dto : projectDtoList) {
            projectResponseList.add(convertToResponse(dto));
        }
        return projectResponseList;
    }

}
