package com.example.myspringbootapp.service;

import com.example.myspringbootapp.entity.User;
import com.example.myspringbootapp.exception.ResourceNotFoundException;
import com.example.myspringbootapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User insertUser(User user){
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> selectAllUser(){
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User selectUser(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        User existUser = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
        return existUser;
    }

    public User updateUser(Long id, User userDetail){
        User existUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        existUser.setName(userDetail.getName());
        existUser.setEmail(userDetail.getEmail());
        return existUser;
    }

    public ResponseEntity<?> deleteUser(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id + " User Not Found ");
        }
        User existUser = optionalUser.get();
        userRepository.delete(existUser);
        return ResponseEntity.ok("User Delete OK");
    }

}
