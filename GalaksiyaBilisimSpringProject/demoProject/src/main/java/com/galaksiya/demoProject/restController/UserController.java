package com.galaksiya.demoProject.restController;

import com.galaksiya.demoProject.business.IUserService;
import com.galaksiya.demoProject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        userService.save(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<User> findUserById(@PathVariable int id){
        User user= userService.getById(id);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<List<User>> findAllUser(){
        List<User> userList= userService.getAll();
        return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        userService.update(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @GetMapping("/remove/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable int id){
        userService.delete(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

}
