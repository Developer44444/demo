package com.example.demo.controllers;

import com.example.demo.models.Project;
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
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/projectMain") //Showing project at the main page
    public String projectMain(Model model) {
        Iterable<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);
        return "projectMain";
    }

    @GetMapping("/projectAdd")
    public String projectAdd(Model model) {
        return "projectAdd";
    }

    @PostMapping("/projectAdd") //Adding project to the database
    public String newProjectAdd(@RequestParam String name, Model model) {
        Project project = new Project(name);
        projectRepository.save(project);
        return "redirect:/projectMain";
    }

    @GetMapping("/project/{id}") //View project and tasks
    public String projectDetails(@PathVariable(value = "id") Long id, Model model) {
        Optional<Project> project = projectRepository.findById(id);
        ArrayList<Project> res = new ArrayList<>();
        project.ifPresent(res::add);
        Iterable<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        model.addAttribute("project", res);
        return "projectDetails";
    }

    @GetMapping("/project/{id}/edit")
    public String projectEdit(@PathVariable(value = "id") long id, Model model) {
        if(!projectRepository.existsById(id)) {
            return "redirect:/project";
        }
        Optional<Project> project = projectRepository.findById(id);
        ArrayList<Project> res = new ArrayList<>();
        project.ifPresent(res::add);
        model.addAttribute("project", res);
        return "projectEdit";
    }

    @PostMapping("project/{id}/edit") //Editing project
    public String projectUpdate(@PathVariable(value = "id") long id, @RequestParam String name, Model model) {
        Project project = projectRepository.findById(id).orElseThrow();
        project.setName(name);
        projectRepository.save(project);
        return "redirect:/projectMain";
    }

    @PostMapping("project/{id}/remove") //Removing project from the database
    public String projectDelete(@PathVariable(value = "id") long id, Model model) {
        Project project = projectRepository.findById(id).orElseThrow();
        projectRepository.delete(project);
        return "redirect:/projectMain";
    }
}