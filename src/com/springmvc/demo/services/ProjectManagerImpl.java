package com.springmvc.demo.services;

import com.springmvc.demo.dao.ProjectDAO;
import com.springmvc.demo.dto.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by Martha on 6/14/2016.
 */
@Service
@Transactional
public class ProjectManagerImpl implements ProjectManager {

    @Autowired
    ProjectDAO projectDAO;

    @Override
    public ProjectDTO getProjectById(Long id) {
        return projectDAO.getProjectById(id);
    }

    @Override
    public ProjectDTO getProjectByStory(String story) {
        return projectDAO.getProjectByStory(story);
    }

    @Override
    public void addProject(ProjectDTO projectDTO) {
        projectDAO.addProject(projectDTO);
    }

    @Override
    public void modifyProject(Long id, String story, String description) {
        projectDAO.modifyProject(id, story, description);
    }

    @Override
    public Collection<ProjectDTO> allProjects() {
        return projectDAO.allProjects();
    }
}
