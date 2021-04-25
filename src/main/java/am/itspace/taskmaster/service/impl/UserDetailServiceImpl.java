package am.itspace.taskmaster.service.impl;


import am.itspace.taskmaster.model.CurrentUser;
import am.itspace.taskmaster.model.User;
import am.itspace.taskmaster.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> byEmail = userRepository.findByEmail(s);
        if (!byEmail .isPresent()) {
            throw new UsernameNotFoundException("User with " + s + "does not exist");
        }
        return new CurrentUser( byEmail.get());
    }
}
