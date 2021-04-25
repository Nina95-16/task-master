package am.itspace.taskmaster.controller;

import am.itspace.taskmaster.exception.ResourceNotFoundException;
import am.itspace.taskmaster.model.Project;
import am.itspace.taskmaster.model.User;
import am.itspace.taskmaster.service.impl.ProjectServiceImp;
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
@Slf4j
@Controller
@RequiredArgsConstructor
public class ProjectController {
  private final UserServiceImpl userService;
  private final ProjectServiceImp projectService;
    @GetMapping("/project/add")
    public String addProject(ModelMap modelMap,
                             @RequestParam(value = "id", required = false) Integer id) throws ResourceNotFoundException {
        if (id != null) {
            Project byId = projectService.getById(id);
            modelMap.addAttribute("project", byId);
        } else {
            modelMap.addAttribute("project", new Project());
        }
        List<User> users = userService.allUsers(1,20);
        modelMap.addAttribute("users", users);
        return "addProject";
    }
    @PostMapping("/project/add")
    public String saveProject(@ModelAttribute Project project){
        projectService.saveProject(project);
        log.info("project with {}  name was added ", project.getName());
        return "redirect:/project/all";
    }
    @GetMapping("/project/all")
    public String allProjects(ModelMap modelMap){
        List<Project> projects = projectService.projects();
        modelMap.addAttribute("projects", projects);
        return "allProjects";
    }

    @GetMapping("/project/delete")
    public String deleteProject(@RequestParam("id") int id) {
        projectService.delete(id);
        log.info("project with {} id was deleted", id );
        return "redirect:/project/all";
    }
}
