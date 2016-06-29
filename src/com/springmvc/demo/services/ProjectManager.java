package com.springmvc.demo.services;

import com.springmvc.demo.dto.ProjectDTO;

import java.util.Collection;

/**
 * Created by Martha on 6/14/2016.
 */
public interface ProjectManager {
    ProjectDTO getProjectById(String id);
    ProjectDTO getProjectByStory(String story);
    ProjectDTO addOrUpdateProject(String id, String story, String description);
    void modifyProject(Long id, String story, String description);
    Collection<ProjectDTO> allProjects();
}
