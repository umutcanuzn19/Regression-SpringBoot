package codefirst.spring.demo.controller;

import codefirst.spring.demo.entity.User;
import codefirst.spring.demo.dto.UserDTO;
import codefirst.spring.demo.mapper.UserMapper;
import codefirst.spring.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    @PostMapping(value = "/user", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public User create(@RequestBody UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        return userService.save(user);
    }

    @PutMapping("/user/{id}")
    public User update(@RequestBody UserDTO userDTO, @PathVariable long id) {
        User user = userMapper.toEntity(userDTO);
        return userService.update(id, user);
    }

    @GetMapping("/user/{id}")
    public User get(@PathVariable long id) {
        return userService.findById(id);
    }

    @DeleteMapping("user/{id}")
    public void delete(@PathVariable long id) {
        userService.delete(id);
    }
}
