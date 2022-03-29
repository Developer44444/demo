package com.example.demo.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public Project() {

    }

    public Project(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project") //Linking project to several tasks
    private List<Task> tasks;

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Project(Long id, String name, List<Task> tasks) {
        this.id = id;
        this.name = name;
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return String.format("Project[id=%s, name=%s]", id, name);
    }
}
