package am.itspace.taskmaster.controller;

import am.itspace.taskmaster.model.Project;
import am.itspace.taskmaster.model.Task;
import am.itspace.taskmaster.model.User;
import am.itspace.taskmaster.service.impl.ProjectServiceImp;
import am.itspace.taskmaster.service.impl.TaskServiceImpl;
import am.itspace.taskmaster.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j

public class TaskController {
   private final UserServiceImpl userService;
   private final ProjectServiceImp projectService;
   private final TaskServiceImpl taskService;
    @GetMapping("/task/add")
    public String addTask(ModelMap modelMap){
        List<User> users = userService.allUsers(1,20);
        modelMap.addAttribute("users", users);
        List<Project> projects = projectService.projects();
        modelMap.addAttribute("projects", projects);
        return "addTask";
    }
    @PostMapping("/task/add")
    public String saveTask(@ModelAttribute Task task){
       taskService.saveTask(task);
       log.info("Task was added");
       return "redirect:/allTasks";
    }

    @GetMapping("/allTasks")
        public String allTasks(ModelMap modelMap){
        List<Task> tasks = taskService.allTasks();
        modelMap.addAttribute("tasks", tasks);
        return "allTasks";

    }
    @GetMapping("/task/delete")
    public String deleteProject(@RequestParam("id") int id) {
       taskService.delete(id);
        return "redirect:/allTasks";
    }



}
