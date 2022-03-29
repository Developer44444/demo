package com.example.demo.controllers;

import com.example.demo.models.Task;
import com.example.demo.repo.ProjectRepository;
import com.example.demo.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping("/taskAdd")
    public String taskAdd(Model model) {
        return "taskAdd";
    }

    @PostMapping("/taskAdd") //Adding tasks
    public String newTaskAdd(@RequestParam String taskName, @RequestParam String taskDescription, Model model) {
        Task task = new Task(taskName, taskDescription);
        taskRepository.save(task);
        return "redirect:/projectMain";
    }

    @GetMapping("/task/{id}/edit")
    public String taskEdit(@PathVariable(value = "id") long id, Model model) {
        if(!taskRepository.existsById(id)) {
            return "redirect:/task";
        }
        Optional<Task> task = taskRepository.findById(id);
        ArrayList<Task> res = new ArrayList<>();
        task.ifPresent(res::add);
        model.addAttribute("task", res);
        return "taskEdit";
    }
    @PostMapping("task/{id}/edit") //Editing tasks
    public String taskUpdate(@PathVariable(value = "id") long id, @RequestParam String taskName, @RequestParam String taskDescription, Model model) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setTaskName(taskName);
        task.setTaskDescription(taskDescription);
        taskRepository.save(task);
        return "redirect:/projectMain";
    }

    @PostMapping("task/{id}/remove") //Removing tasks
    public String TaskDelete(@PathVariable(value = "id") long id, Model model) {
        Task task = taskRepository.findById(id).orElseThrow();
        taskRepository.delete(task);
        return "redirect:/projectMain";
    }
}