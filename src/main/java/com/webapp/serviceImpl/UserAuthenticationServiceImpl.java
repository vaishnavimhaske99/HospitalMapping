// UserAuthenticationServiceImpl.java
package com.webapp.serviceImpl;

import com.webapp.dto.UserAuthenticationDTO;
import com.webapp.model.UserAuthentication;
import com.webapp.repository.UserAuthenticationRepository;
import com.webapp.service.UserAuthenticationService;
import com.webapp.exception.InvalidRequestException;
import com.webapp.exception.RequestNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserAuthenticationDTO> getAllUsers() {
        return userAuthenticationRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserAuthenticationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserAuthenticationDTO getUserById(int id) {
        UserAuthentication user = userAuthenticationRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException("User not found with id " + id));
        return modelMapper.map(user, UserAuthenticationDTO.class);
    }

    @Override
    public UserAuthenticationDTO getUserByUsername(String username) {
        UserAuthentication user = userAuthenticationRepository.findByUsername(username);
        if (user == null) {
            throw new RequestNotFoundException("User not found with username " + username);
        }
        return modelMapper.map(user, UserAuthenticationDTO.class);
    }

    @Override
    public UserAuthenticationDTO createUser(UserAuthenticationDTO userAuthenticationDTO) {
        if (userAuthenticationDTO == null) {
            throw new InvalidRequestException("Invalid User details provided");
        }
        UserAuthentication user = modelMapper.map(userAuthenticationDTO, UserAuthentication.class);
        UserAuthentication createdUser = userAuthenticationRepository.save(user);
        return modelMapper.map(createdUser, UserAuthenticationDTO.class);
    }

    @Override
    public UserAuthenticationDTO updateUser(int id, UserAuthenticationDTO userAuthenticationDTO) {
        if (userAuthenticationDTO == null) {
            throw new InvalidRequestException("Invalid User details provided");
        }
        UserAuthentication existingUser = userAuthenticationRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException("User not found with id " + id));

        existingUser.setUsername(userAuthenticationDTO.getUsername());
        existingUser.setPasswordHash(userAuthenticationDTO.getPasswordHash());
        existingUser.setRole(userAuthenticationDTO.getRole());
        existingUser.setLastLoginTime(userAuthenticationDTO.getLastLoginTime());

        UserAuthentication updatedUser = userAuthenticationRepository.save(existingUser);
        return modelMapper.map(updatedUser, UserAuthenticationDTO.class);
    }

    @Override
    public void deleteUser(int id) {
        if (!userAuthenticationRepository.existsById(id)) {
            throw new RequestNotFoundException("User not found with id " + id);
        }
        userAuthenticationRepository.deleteById(id);
    }
}
