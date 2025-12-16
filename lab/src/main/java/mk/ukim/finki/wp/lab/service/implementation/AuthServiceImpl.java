package mk.ukim.finki.wp.lab.service.implementation;

import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.repository.mock.UserRepository;
import mk.ukim.finki.wp.lab.service.AuthService;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new RuntimeException();
        }

        return this.userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(RuntimeException::new);
    }

}
