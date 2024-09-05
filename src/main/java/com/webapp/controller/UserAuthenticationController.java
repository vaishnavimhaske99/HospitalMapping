// UserAuthenticationController.java
package com.webapp.controller;

import com.webapp.dto.UserAuthenticationDTO;
import com.webapp.service.UserAuthenticationService;
import com.webapp.exception.InvalidRequestException;
import com.webapp.exception.RequestNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserAuthenticationController {

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @GetMapping
    public ResponseEntity<List<UserAuthenticationDTO>> getAllUsers() {
        List<UserAuthenticationDTO> users = userAuthenticationService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAuthenticationDTO> getUserById(@PathVariable int id) {
        UserAuthenticationDTO user = userAuthenticationService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserAuthenticationDTO> getUserByUsername(@PathVariable String username) {
        UserAuthenticationDTO user = userAuthenticationService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserAuthenticationDTO> createUser(@RequestBody UserAuthenticationDTO userAuthenticationDTO) {
        try {
            UserAuthenticationDTO createdUser = userAuthenticationService.createUser(userAuthenticationDTO);
            return ResponseEntity.ok(createdUser);
        } catch (InvalidRequestException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserAuthenticationDTO> updateUser(@PathVariable int id, @RequestBody UserAuthenticationDTO userAuthenticationDTO) {
        try {
            UserAuthenticationDTO updatedUser = userAuthenticationService.updateUser(id, userAuthenticationDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (InvalidRequestException | RequestNotFoundException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        try {
            userAuthenticationService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (RequestNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
