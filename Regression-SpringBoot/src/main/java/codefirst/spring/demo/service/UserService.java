package codefirst.spring.demo.service;

import codefirst.spring.demo.entity.User;
import codefirst.spring.demo.exception.CodefirstException;
import codefirst.spring.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User update(long id, User user) {
        user.setId(findById(id).getId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(findById(id).getId());
    }

    public User findById(long id) {
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findById(id).get();
        } else {
            throw new CodefirstException(id + " idli kişi bulunamadı", HttpStatus.BAD_REQUEST);
        }
    }
}
