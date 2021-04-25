package am.itspace.taskmaster.controller;


import am.itspace.taskmaster.model.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String main(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            modelMap.addAttribute("user", currentUser.getUser());
        }
        return "index";
    }
    @GetMapping("/admin")
    public String adminPanel() {
        return "admin";
    }
}


