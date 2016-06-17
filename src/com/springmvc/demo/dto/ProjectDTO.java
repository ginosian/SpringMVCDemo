package com.springmvc.demo.dto;

import javax.persistence.*;

/**
 * Created by Martha on 6/14/2016.
 */
@Entity
@Table(name = "project")
public class ProjectDTO {
    @Id @GeneratedValue
//    @Column(name = "id", unique = true,
//            nullable = false, length = 45)
    private Long id;

    private String story;
    private String description;

    public ProjectDTO() {
    }

    public void set(String story, String description) {
        this.story = story;
        this.description = description;
    }

    @GeneratedValue
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
