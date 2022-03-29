package com.example.demo.models;

import javax.persistence.*;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;
    private String taskName;
    private String taskDescription;

    public Task(){

    }
    public Task(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }

    public long getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTaskName(String taskName) {
        taskName = taskName;
    }

    public void setTaskDescription(String taskDescription) {
        taskDescription = taskDescription;
    }

    @ManyToOne(fetch = FetchType.LAZY) //Linking several tasks to one project
    private Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return String.format("Task[id=%s, description=%s]", id);
    }
}