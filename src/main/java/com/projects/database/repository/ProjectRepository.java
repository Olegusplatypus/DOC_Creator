package com.projects.database.repository;

import com.projects.database.entity.ProjectEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Oleg Cherniak
 * version 1.0
 * 31.08.2016
 */
public interface ProjectRepository extends MongoRepository<ProjectEntity,String> {

}
