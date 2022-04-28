package com.example.myspringbootapp.controller;

import com.example.myspringbootapp.controller.form.UserForm;
import com.example.myspringbootapp.entity.User;
import com.example.myspringbootapp.repository.UserRepository;
import com.example.myspringbootapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @GetMapping("/index")
    public String index(Model model) {
        List<User> userList = userService.selectAllUser();
        model.addAttribute("users", userList);
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(UserForm user) {
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid UserForm userForm, BindingResult result, Model model) {
        // 검증 결과가 BindingResult로 들어감
        if (result.hasErrors()) {
            return "add-user";
        }
        //UserForm -> User 로 변환하는 작업
        User user = new User();
        BeanUtils.copyProperties(userForm, user);
        //user 등록
        userService.insertUser(user);

        model.addAttribute("users", userService.selectAllUser());

        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userService.selectUser(id);
//        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        UserForm userForm = new UserForm();
        BeanUtils.copyProperties(user, userForm);
        log.warn("warn log");
        log.debug("debug log");
        model.addAttribute("userForm", userForm);
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid UserForm userForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            userForm.setId(id);
            return "update-user";
        }
        User user=new User();
        BeanUtils.copyProperties(userForm,user);
        userService.updateUser(id, user);
        List<User> userList = userService.selectAllUser();
        model.addAttribute("users", userList);
        return "index";
    }

}
