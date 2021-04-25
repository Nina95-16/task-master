package am.itspace.taskmaster.service;


import am.itspace.taskmaster.exception.ResourceNotFoundException;
import am.itspace.taskmaster.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    void saveUser(User user);

    List<User> allUsers(@RequestParam(value = "page",defaultValue = "1") int page,
                        @RequestParam(value = "size",defaultValue = "20") int size);

    User getById(int id) throws ResourceNotFoundException;

    void delete(int id);

    Optional<User> findByEmail(String email);

    List<User> findAllByName(String name);

}
