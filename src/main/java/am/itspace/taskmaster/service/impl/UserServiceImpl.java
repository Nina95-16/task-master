package am.itspace.taskmaster.service.impl;


import am.itspace.taskmaster.exception.ResourceNotFoundException;
import am.itspace.taskmaster.model.User;
import am.itspace.taskmaster.repository.UserRepository;
import am.itspace.taskmaster.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findAllByName(String name) {
        return userRepository.findAllByNameStartingWith(name);
    }

    @Override
    public List<User> allUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "size", defaultValue = "20") int size) {
        PageRequest of = PageRequest.of(page - 1, size);
        Page<User> all = userRepository.findAll(of);
        List<User> content = all.getContent();
//        int totalPage = all.getTotalPages();
//        if (totalPage > 0) {
//            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPage)
//                    .boxed()
//                    .collect(Collectors.toList());
//            modelMap.addAttribute("pageNumbers",pageNumbers);
//        }
        return content;
    }

    @Override
    public User getById(int id) throws ResourceNotFoundException {
        userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with" + id + " does not exist"));
        User one = userRepository.getOne(id);
        return one;
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

}
