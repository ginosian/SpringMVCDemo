package com.springmvc.demo.dao;

import com.springmvc.demo.dto.ProjectDTO;

import java.util.Collection;

/**
 * Created by Martha on 6/14/2016.
 */
public interface ProjectDAO {
    ProjectDTO getProjectById(int id);
    void addProject(ProjectDTO projectDTO);
    void modifyProjectStory(String story);
    void modifyProjectDescription(ProjectDTO projectDTO);
    Collection<ProjectDTO> allProjects();
}
