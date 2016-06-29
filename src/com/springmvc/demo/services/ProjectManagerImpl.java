package com.springmvc.demo.services;

import com.springmvc.demo.dao.ProjectDAO;
import com.springmvc.demo.dto.ProjectDTO;
import com.springmvc.demo.exceptions.EmptyRequiredValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public ProjectDTO getProjectByStory(String story) {
        return projectDAO.getProjectByStory(story);
    }

    @Override
    public ProjectDTO addOrUpdateProject(String id, String story, String description) {
        if(story == null || story.isEmpty()) throw new EmptyRequiredValueException();
        if(id != null && !id.isEmpty() && projectDAO.getProjectById(Long.parseLong(id)) != null){
            return projectDAO.modifyProject(Long.parseLong(id), story, description);
        } else {
            ProjectDTO newProject = new ProjectDTO();
            newProject.set(story, description);
            return projectDAO.addProject(newProject);
        }
    }

    @Override
    public void modifyProject(Long id, String story, String description) {
        projectDAO.modifyProject(id, story, description);
    }

    @Override
    public Collection<ProjectDTO> allProjects() {
        ArrayList<ProjectDTO> projects = (ArrayList<ProjectDTO>)projectDAO.allProjects();
        if (projects == null) projects = new ArrayList<>();
        return projects;
    }
}
