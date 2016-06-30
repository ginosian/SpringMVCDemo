package com.springmvc.demo.dao;

import com.springmvc.demo.dto.ProjectDTO;

import java.util.Collection;

/**
 * Created by Martha on 6/14/2016.
 */
public interface ProjectDAO {
    ProjectDTO getProjectById(Long id);
    ProjectDTO addProject(ProjectDTO projectDTO);
    ProjectDTO modifyProject(Long id, String story, String description);
    Collection<ProjectDTO> allProjects();
}
