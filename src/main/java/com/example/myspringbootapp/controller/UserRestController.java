package com.example.myspringbootapp.controller;

import com.example.myspringbootapp.entity.User;
import com.example.myspringbootapp.exception.ResourceNotFoundException;
import com.example.myspringbootapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        return userService.insertUser(user);
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.selectAllUser();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
//        Optional<User> optionalUser = userService.selectUser(id);
//        User existUser = optionalUser.orElseThrow(()->new ResourceNotFoundException("User","id",id));
        return userService.selectUser(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetail){
        return userService.updateUser(id,userDetail);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

//    @RequestMapping("/users/{id}")
//    public Optional<User> getUser(@PathVariable Long id){
//        return userRepository.findById(id);
//    }


}
