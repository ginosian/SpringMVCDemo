package com.springmvc.demo.dao;

import com.springmvc.demo.dto.ProjectDTO;

import java.util.Collection;

/**
 * Created by Martha on 6/14/2016.
 */
public interface ProjectDAO {
    ProjectDTO getProjectById(int id);
    ProjectDTO getProjectByStory(String story);
    void addProject(ProjectDTO projectDTO);
    void modifyProjectStory(String story);
    void modifyProjectDescription(String projectDTO);
    Collection<ProjectDTO> allProjects();
}
