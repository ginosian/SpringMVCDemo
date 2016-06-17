package com.springmvc.demo.dto;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

/**
 * Created by Martha on 6/14/2016.
 */
@Entity
@Table(name = "task")
public class TaskDTO {
    @Id
    @GeneratedValue
    private Long id;
    private String story;
    private String description;
    private boolean complete;

    @OneToOne
    @JoinColumn(name = "" )
    @ForeignKey(name = "task_project")
    private ProjectDTO projectDTO;


    @OneToOne
    @JoinColumn(name = "" )
    @ForeignKey(name = "task_user")
    private UserDTO userDTO;

    public TaskDTO() {
    }

    public void set(String story, String description, ProjectDTO projectDTO, UserDTO userDTO) {
        this.story = story;
        this.description = description;
        this.projectDTO = projectDTO;
        this.userDTO = userDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProjectDTO getProjectDTO() {
        return projectDTO;
    }

    public void setProjectDTO(ProjectDTO projectDTO) {
        this.projectDTO = projectDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
