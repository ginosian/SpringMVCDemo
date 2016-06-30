package com.springmvc.demo.dto;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by Martha on 6/14/2016.
 */
@Entity
@Table(name = "project")
public class ProjectDTO {
    @Id
    @GeneratedValue
    private Long id;
    @Type(type = "text")
    private String story;
    @Type(type = "text")
    private String description;

    public ProjectDTO() {
    }

    public ProjectDTO(String story, String description) {
        this.story = story;
        this.description = description;
    }

    public void set(String story, String description) {
        this.story = story;
        this.description = description;
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
}
