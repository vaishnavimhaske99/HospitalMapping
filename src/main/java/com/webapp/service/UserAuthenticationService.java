// UserAuthenticationService.java
package com.webapp.service;

import com.webapp.dto.UserAuthenticationDTO;

import java.util.List;

public interface UserAuthenticationService {

    List<UserAuthenticationDTO> getAllUsers();

    UserAuthenticationDTO getUserById(int id);

    UserAuthenticationDTO getUserByUsername(String username);

    UserAuthenticationDTO createUser(UserAuthenticationDTO userAuthenticationDTO);

    UserAuthenticationDTO updateUser(int id, UserAuthenticationDTO userAuthenticationDTO);

    void deleteUser(int id);
}
