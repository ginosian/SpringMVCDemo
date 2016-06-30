package com.springmvc.demo.services;

import com.springmvc.demo.dao.ProjectDAO;
import com.springmvc.demo.dto.ProjectDTO;
import com.springmvc.demo.exceptions.EmptyRequiredValueException;
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
    public ProjectDTO getProjectById(String id) {
        if(id == null || id.isEmpty())throw new EmptyRequiredValueException();
        return projectDAO.getProjectById(Long.parseLong(id));
    }

    @Override
    public ProjectDTO addOrUpdateProject(String id, String story, String description) {
        if(story == null || story.isEmpty()) throw new EmptyRequiredValueException();
        if(id != null && !id.isEmpty()){
            return projectDAO.modifyProject(Long.parseLong(id), story, description);
        } else {
            ProjectDTO newProject = new ProjectDTO();
            newProject.set(story, description);
            return projectDAO.addProject(newProject);
        }
    }

    @Override
    public Collection<ProjectDTO> allProjects() {
        return  projectDAO.allProjects();
    }
}
