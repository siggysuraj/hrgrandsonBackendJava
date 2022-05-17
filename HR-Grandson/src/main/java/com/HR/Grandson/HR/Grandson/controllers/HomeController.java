package com.HR.Grandson.HR.Grandson.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HR.Grandson.HR.Grandson.exception.ResourceNotFoundException;
import com.HR.Grandson.HR.Grandson.models.User;
import com.HR.Grandson.HR.Grandson.repos.UserRepository;

@RestController @CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping("/api/path1")
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id)
        throws ResourceNotFoundException {
        User user = userRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        return ResponseEntity.ok().body(user);
    }
    
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId)
         throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
       .orElseThrow(() -> new ResourceNotFoundException("User not present for the id :: " + userId));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<User>updateUser(@PathVariable(value = "id") Long userId,
         @RequestBody User userDetails) throws    ResourceNotFoundException {
        User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        user.setEmail(userDetails.getEmail());
        user.setLastname(userDetails.getLastname());
        user.setFirstname(userDetails.getFirstname());
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }
}
