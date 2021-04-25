package am.itspace.taskmaster.controller;

import am.itspace.taskmaster.model.CurrentUser;
import am.itspace.taskmaster.model.Type;
import am.itspace.taskmaster.model.User;
import am.itspace.taskmaster.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String registerGet() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute @Valid User user, BindingResult result, ModelMap modelMap) {
        if (result.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                stringBuilder.append(fieldError.getDefaultMessage());
            }
            modelMap.addAttribute("errorMessage", stringBuilder.toString());
            return "register";
        }
        Optional<User> byEmail = userService.findByEmail(user.getEmail());
        if (byEmail.isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.saveUser(user);
            log.debug("User with {} email was registered", user.getEmail());
            return "redirect:/signIn";
        }
        return "register";
    }

    @GetMapping("/signIn")
    public String sighIn() {
        return "/signIn";
    }

    @GetMapping("/successLogin")
    public String successLogin(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser == null) {
            return "redirect:/signIn";
        }
        User user = currentUser.getUser();
        if (user.getType() == Type.ADMIN) {
            return "redirect:/admin";
        }
        return "redirect:/user";
    }


    @GetMapping("/user")
    public String userPage(@AuthenticationPrincipal CurrentUser currentUser, ModelMap modelMap) {
        User user = currentUser.getUser();
        if (user.getId() != 0) {
            modelMap.addAttribute("user", user);
        }
        return "user";
    }

    @GetMapping("/allUsers")
    public String allUsers(ModelMap modelMap) {
        List<User> users = userService.allUsers(1, 20);
//        PagedListHolder<User> pagedListHolder = new PagedListHolder<>(users);
//        modelMap.addAttribute("pagedListHolder",pagedListHolder);
//        users.sort(new Comparator<User>() {
//            @Override
//            public int compare(User o1, User o2) {
//                return o1.getName().compareTo(o2.getName());
//            }
//        });
//        System.out.println(users);
        modelMap.addAttribute("users", users);
        return "allUsers";
    }

    @GetMapping("/user/delete")
    public String deleteUser(@RequestParam("id") int id, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser.getUser().getType() == Type.ADMIN) {
            userService.delete(id);
        }
        return "redirect:/allUsers";
    }

}
